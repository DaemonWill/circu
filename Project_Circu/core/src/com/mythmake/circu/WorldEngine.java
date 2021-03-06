package com.mythmake.circu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mythmake.circu.dao.WorldOfCircu;
import com.mythmake.circu.domain.Shapes;

/****
 * Methods for the main game loop
 * 
 * Main tasks covered are asset creation upon game start
 * 			, event handling, 
 * 				and asset deletion upon game exit
 * 
 * @author Daimen Williams
 *
 */


public class WorldEngine {
	
	Texture playerCharImg;
	Texture playerBulletImg;
	Texture hexaminionImg;
	Rectangle playerChar;
	Rectangle hexaminion;
	Rectangle stats;
	OrthographicCamera cam;
	SpriteBatch sprites;
	WorldOfCircu world;
	Shapes circu;
	Shapes basicHex;
	BulletNature bulletNature;
	EnemyNature enemyNature;
	CollisionMechanics collisionMech;
	Array<Rectangle> characterCondition = new Array<Rectangle>();
	
	long playerRechargeTimer;
	boolean playerRecharge = false;
	long hexaminionTimer;
	int hexTypeId;
	
	
	/**
	 * Instantiating main DAO class, in-game physics and mechanics classes. 
	 * Loading assets like images from in game path settings
	 * Instantiating main characters and objects/ assigning initial values to their fields
	 * Creating coordinate plane, window, and sprite batch for rendering
	 * 
	 */
	public void genesis(){
		
		world = new WorldOfCircu();
		bulletNature = new BulletNature();
		enemyNature = new EnemyNature();
		collisionMech = new CollisionMechanics();
		world.aCircuIsBorn();
		world.hexaminionIsBorn();
		circu = world.idFetchShape(1);
		int circuTypeId = circu.getTypeId();
		int circuBulletId = circu.getBulletId();
		basicHex = world.idFetchShape(2);
		hexTypeId = basicHex.getTypeId();
		
		playerBulletImg = new Texture(Gdx.files.internal(world.idFetchBullet(circuBulletId).getImage()));
		playerCharImg = new Texture(Gdx.files.internal(world.idFetchType(circuTypeId).getImage()));
		hexaminionImg = new Texture(Gdx.files.internal(world.idFetchType(hexTypeId).getImage()));
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 480);
		sprites = new SpriteBatch();
		
		playerChar = new Rectangle();
		playerChar.width = world.idFetchType(circuTypeId).getWidth();
		playerChar.height = world.idFetchType(circuTypeId).getHeight();
		playerChar.x = (800/2 - playerChar.width/2);
		playerChar.y = (480/2 - playerChar.height/2);
		
		playerRechargeTimer = TimeUtils.nanoTime();
		hexaminionTimer = TimeUtils.nanoTime();
		enemyNature.spawnHexaminion(world.idFetchType(hexTypeId), playerChar);
		stats = new Rectangle();
		stats.set(10,10,10,10); //health, bulk, power, speed
		characterCondition.add(playerChar);
		characterCondition.add(stats);
	}
	
	/**
	 * --- In-game event handling ---
	 * 
	 * Track player decisions
	 * 
	 * set minion and bullet spawn/refresh times, establish when collisions are being made and
	 * calculate damage 
	 * 
	 * Update visuals after events and outcomes are determined for the instance
	 */
	public void revolution(){
		//every 2 seconds make an instance of hexaminion an appropriate distance from player
		if(TimeUtils.nanoTime() - hexaminionTimer >= 2000000000){
			enemyNature.spawnHexaminion(world.idFetchType(hexTypeId), playerChar);
			hexaminionTimer = TimeUtils.nanoTime();
		}
		
		//A list of all bullet and enemy instances are maintained. As an enemy or bullet is spawned
		//or destroyed the list reflects the change. This also tracks health and individual stats
		// and positions of course.
		bulletNature.update();
		enemyNature.update();
		
		//Generating background
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    cam.update();
	    
	    //setting images to be mounted and tracked in visual environment
	    sprites.setProjectionMatrix(cam.combined);
	    sprites.begin();
	    
	    //assign images to be updated with their respective game object dimensions and positions
	    sprites.draw(playerCharImg, playerChar.x, playerChar.y);
	    for(Integer enemy : enemyNature.getEnemies().keySet()){
	    	sprites.draw(hexaminionImg, enemyNature.getEnemies().get(enemy).get(0).x,
	    			enemyNature.getEnemies().get(enemy).get(0).y);
	    }
	    for(Integer key : bulletNature.getBulletTrajectories().keySet()){
	    	sprites.draw(playerBulletImg, bulletNature.getBulletTrajectories().get(key).get(0).x,
	    			bulletNature.getBulletTrajectories().get(key).get(0).y);
	    }
	    
	    //calling upon logic to determine if collisions happen with any in game object and
	    //calculating resulting changes
	    for(Integer enemy : enemyNature.getEnemies().keySet()){
	    	for(Integer bullet : bulletNature.getBulletTrajectories().keySet()){
	    		if(collisionMech.hasInteraction(bulletNature.getBulletTrajectories().get(bullet), 
	    				enemyNature.getEnemies().get(enemy))){
	    			collisionMech.damage(bulletNature.getBulletTrajectories().get(bullet).get(2), 
	    					enemyNature.getEnemies().get(enemy).get(1));
	    		}
	    	}
	    	if(collisionMech.hasInteraction(characterCondition, enemyNature.getEnemies().get(enemy))){
	    		collisionMech.damage(characterCondition.get(1), enemyNature.getEnemies().get(enemy).get(1));
	    		System.out.println("player collided!");
	    	}
	    }
	    sprites.end();
	    
	    //player movement event handling
	    if(Gdx.input.isKeyPressed(Input.Keys.A)) playerChar.x -= 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.D)) playerChar.x += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.W)) playerChar.y += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.S)) playerChar.y -= 200 * Gdx.graphics.getDeltaTime();
	    
	    //stall the player from spamming bullets for ~2/5ths of a second? 
	    //if player is allowed to shoot, logic is done to render bullet with trajectory vector
	    if(playerRecharge == true){
	    	if(TimeUtils.nanoTime() - playerRechargeTimer > 400000000){
	    		playerRecharge = false;
	    	}
	    }
	    if(Gdx.input.isTouched() && playerRecharge==false ) {
	        Vector3 touchCoor = new Vector3();
	        touchCoor.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	        cam.unproject(touchCoor);
	        bulletNature.spawnBullet(world.idFetchBullet(circu.getBulletId()), playerChar, touchCoor);
	        playerRechargeTimer = TimeUtils.nanoTime();
	        playerRecharge = true;
	     }
	    
	    bulletNature.openFire();
	    enemyNature.seekAndDestroy(playerChar);
	    
	    //Set game coordinate bounds for the player
	    if(playerChar.x < 0) playerChar.x = 0;
	    if(playerChar.x > 800 - playerChar.width) playerChar.x = 800 - playerChar.width;
	    if(playerChar.y < 0) playerChar.y = 0;
	    if(playerChar.y > 480 - playerChar.height) playerChar.y = 480 - playerChar.height;
	    
	    //if player health reaches zero, remove player image from game to signify player is dead
	    if(characterCondition.get(1).x <= 0){
	    	playerCharImg.dispose();
	    	Gdx.app.exit();
	    }
	   
	}
	
	
	/**
	 * When game terminates, all assets are removed from game
	 */
	public void end(){
		playerCharImg.dispose();
		sprites.dispose();
	}
}
