package com.mythmake.circu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.mythmake.circu.dao.WorldOfCircu;
import com.mythmake.circu.domain.Shapes;

public class WorldEngine {
	
	/*
	 * 
	 * Will have to sweep through and comment in the coming days.
	 * 
	 */
	
	Texture playerCharImg;
	Texture playerBulletImg;
	Rectangle playerChar;
	OrthographicCamera cam;
	SpriteBatch sprites;
	WorldOfCircu world;
	Shapes circu;
	BulletNature bulletNature;
	
	long playerRechargeTimer;
	boolean playerRecharge = false;
	
	public void genesis(){
		
		world = new WorldOfCircu();
		bulletNature = new BulletNature();
		world.aCircuIsBorn();
		circu = world.idFetchShape(1);
		int circuTypeId = circu.getTypeId();
		int circuBulletId = circu.getBulletId();
		playerBulletImg = new Texture(Gdx.files.internal(world.idFetchBullet(circuBulletId).getImage()));
		playerCharImg = new Texture(Gdx.files.internal(world.idFetchType(circuTypeId).getImage()));
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 480);
		sprites = new SpriteBatch();
		
		playerChar = new Rectangle();
		playerChar.width = world.idFetchType(circuTypeId).getWidth();
		playerChar.height = world.idFetchType(circuTypeId).getHeight();
		playerChar.x = (800/2 - playerChar.width/2);
		playerChar.y = (480/2 - playerChar.height/2);
		
		playerRechargeTimer = TimeUtils.nanoTime();
	}
	
	public void revolution(){
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    cam.update();
	    sprites.setProjectionMatrix(cam.combined);
	    sprites.begin();
	    sprites.draw(playerCharImg, playerChar.x, playerChar.y);
	    for(Rectangle bullRec : bulletNature.getBulletTrajectories().keySet()){
	    	sprites.draw(playerBulletImg, bullRec.x, bullRec.y);
	    }
	    sprites.end();
	    
	    if(Gdx.input.isKeyPressed(Input.Keys.A)) playerChar.x -= 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.D)) playerChar.x += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.W)) playerChar.y += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.S)) playerChar.y -= 200 * Gdx.graphics.getDeltaTime();
	    
	    if(playerRecharge == true){
	    	if(TimeUtils.nanoTime() - playerRechargeTimer > 800000000){
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
	    
	    if(playerChar.x < 0) playerChar.x = 0;
	    if(playerChar.x > 800 - playerChar.width) playerChar.x = 800 - playerChar.width;
	    if(playerChar.y < 0) playerChar.y = 0;
	    if(playerChar.y > 480 - playerChar.height) playerChar.y = 480 - playerChar.height;
	}
	
	public void end(){
		
		playerCharImg.dispose();
		sprites.dispose();
	}
}
