package com.mythmake.circu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mythmake.circu.dao.WorldOfCircu;
import com.mythmake.circu.domain.Shapes;

public class WorldEngine {
	
	Texture playerCharImg;
	Rectangle playerChar;
	OrthographicCamera cam;
	SpriteBatch sprites;
	WorldOfCircu world;
	Shapes circu;
	
	public void genesis(){
		
		world = new WorldOfCircu();
		world.aCircuIsBorn();
		circu = world.idFetchShape(1);
		int circuTypeId = circu.getTypeId();
		playerCharImg = new Texture(Gdx.files.internal(world.idFetchType(circuTypeId).getImage()));
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 480);
		sprites = new SpriteBatch();
		
		playerChar = new Rectangle();
		playerChar.width = world.idFetchType(circuTypeId).getWidth();
		playerChar.height = world.idFetchType(circuTypeId).getHeight();
		playerChar.x = (800/2 - playerChar.width/2);
		playerChar.y = (480/2 - playerChar.height/2);
	}
	
	public void revolution(){
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    cam.update();
	    sprites.setProjectionMatrix(cam.combined);
	    sprites.begin();
	    sprites.draw(playerCharImg, playerChar.x, playerChar.y);
	    sprites.end();
	    
	    if(Gdx.input.isKeyPressed(Input.Keys.A)) playerChar.x -= 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.D)) playerChar.x += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.W)) playerChar.y += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.S)) playerChar.y -= 200 * Gdx.graphics.getDeltaTime();
	    
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
