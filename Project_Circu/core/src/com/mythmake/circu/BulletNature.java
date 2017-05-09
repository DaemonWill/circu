package com.mythmake.circu;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;
import com.mythmake.circu.domain.Bullets;

public class BulletNature {
	
	private Map <Rectangle, Pathing<Bullets, Double>> bulletTrajectories = 
			new HashMap<Rectangle, Pathing<Bullets, Double>>();
	
	public Map<Rectangle, Pathing<Bullets, Double>> getBulletTrajectories(){
		return bulletTrajectories;
	}
	
	public class Pathing<bullets, angle> {
	    bullets bull = null;
	    angle theta = null;

	    Pathing(bullets bull, angle theta) {
	        this.bull = bull;
	        this.theta = theta;
	    }

	    public bullets getBullet() {
	        return bull;
	    }

	    public void setBullet(bullets bull) {
	        this.bull = bull;
	    }

	    public angle getTheta() {
	        return theta;
	    }

	    public void setTheta(angle theta) {
	        this.theta = theta;
	    }

	};
	
	public void spawnBullet(Bullets bullet,Rectangle srcRec, Vector3 inputCoord){
		Rectangle bulletRec = new Rectangle();
		Vector2 srcCoord = new Vector2();
		bulletRec.setCenter(srcRec.getCenter(srcCoord));
		double deltaY = inputCoord.y - srcCoord.y;
		double deltaX = inputCoord.x - srcCoord.x;
		Double theta = Math.atan(deltaY/deltaX);
		System.out.println(""+theta);
		Pathing<Bullets, Double> path = new Pathing<Bullets, Double>(bullet,theta);
		bulletTrajectories.put(bulletRec,path);
	}
	
	public void openFire(){
		for(Rectangle key : bulletTrajectories.keySet()){
			//key.x += 240*Math.cos(bulletTrajectories.get(key).getTheta()) * Gdx.graphics.getDeltaTime();
			//key.y += 240*Math.sin(bulletTrajectories.get(key).getTheta()) * Gdx.graphics.getDeltaTime();
			//if(key.x < 0) bulletTrajectories.remove(key);
//			if(key.y > 480) bulletTrajectories.remove(key);
//			if(key.x > 830) bulletTrajectories.remove(key); //adding 30 on as a generic number it won't be
//			if(key.y < (0-30)) bulletTrajectories.remove(key); //greater than
		}
	}
}
