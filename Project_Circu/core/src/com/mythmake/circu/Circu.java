package com.mythmake.circu;

import com.badlogic.gdx.ApplicationAdapter;

public class Circu extends ApplicationAdapter {
	
	WorldEngine worldEngine = new WorldEngine();
	
	@Override
	public void create () {
		
		worldEngine.genesis();
		
	}

	@Override
	public void render () {
		
		worldEngine.revolution();
	}
	
	@Override
	public void dispose () {
		
		worldEngine.end();
	}
}
