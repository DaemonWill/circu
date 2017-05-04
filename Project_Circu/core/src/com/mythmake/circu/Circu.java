package com.mythmake.circu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Circu extends ApplicationAdapter {
	
	private Texture redcharImg;
	//private Texture charbulletImg;
	//private Texture hexaminionImg;
	private Rectangle redchar;
	//private Rectangle charbullet;
	//private Rectangle hexaminion;
	
	 private OrthographicCamera camera;
	 private SpriteBatch batch;
	
	
	@Override
	public void create () {
		
		redcharImg = new Texture(Gdx.files.internal("redchar.png"));
	//	charbulletImg = new Texture(Gdx.files.internal("charbullet.png"));
		//hexaminionImg = new Texture(Gdx.files.internal("hexaminion.png"));
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		batch = new SpriteBatch();
		
		redchar = new Rectangle();
		redchar.width = 60;
		redchar.height = 60;
		redchar.x = (800/2-60/2);
		redchar.y = (480/2-60/2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    camera.update();
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    batch.draw(redcharImg, redchar.x, redchar.y);
	    batch.end();
	    
	    if(Gdx.input.isKeyPressed(Input.Keys.A)) redchar.x -= 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.D)) redchar.x += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.W)) redchar.y += 200 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.S)) redchar.y -= 200 * Gdx.graphics.getDeltaTime();
	    
	    if(redchar.x < 0) redchar.x = 0;
	    if(redchar.x > 800 - 60) redchar.x = 800 - 60;
	    if(redchar.y < 0) redchar.y = 0;
	    if(redchar.y > 480 - 60) redchar.y = 480 - 60;
	}
	
	@Override
	public void dispose () {
		
		redcharImg.dispose();
	    batch.dispose();
	}
}
