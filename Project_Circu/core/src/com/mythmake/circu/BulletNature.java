package com.mythmake.circu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;
import com.mythmake.circu.domain.Bullets;

/****
 * Class contains encapsulated map of all bullet instances containing their position coordinates, 
 * initial path vector, and stats (bullets can deal varying damage and have their own health stats) 
 * 
 * Methods regarding accessing this map and editing the overall current state
 * of each bullet instance are included.
 * 
 * @author Daimen Williams
 *
 */

public class BulletNature {
	
	private Map <Integer, Array<Rectangle>> bulletTrajectories = 
			new HashMap<Integer, Array<Rectangle>>();
	
	public Map<Integer, Array<Rectangle>> getBulletTrajectories(){
		return bulletTrajectories;
	}
	
	private Iterator<Map.Entry<Integer,Array<Rectangle>>>  iterate;
	private Map.Entry<Integer,Array<Rectangle>> instance;
	private Integer instanceId = 0;
	float xMultiplier;
	float yMultiplier;
	float xCoord;
	float yCoord;
	float height;
	float width;
	double health;
	
	public void spawnBullet(Bullets bullet,Rectangle srcRec, Vector3 inputCoord){
		Rectangle bulletRec = new Rectangle();
		Vector2 srcCoord = new Vector2();
		Rectangle speedVector = new Rectangle();
		Rectangle stats = new Rectangle();
		stats.set(bullet.getHealth(),bullet.getBulk(),0,0);
		Array<Rectangle> vectors = new Array<Rectangle>();
		bulletRec.setCenter(srcRec.getCenter(srcCoord));
		bulletRec.height = bullet.getHeight();
		bulletRec.width = bullet.getwidth();
		double deltaY = inputCoord.y - srcCoord.y;
		double deltaX = inputCoord.x - srcCoord.x;
		double theta = Math.atan(deltaY/deltaX);
		if(deltaX < 0) theta += Math.PI;
		//System.out.println(""+theta);
		speedVector.set((float)Math.cos(theta), (float)Math.sin(theta),0,0);
		vectors.add(bulletRec);
		vectors.add(speedVector);
		vectors.add(stats);
		//System.out.println("size = "+ vectors.size);
		bulletTrajectories.put(instanceId, vectors);
		instanceId++;
	}
	
	/*
	 * logic regarding how bullets travel (given their initial path vectors) and at what speed
	 * If bullet exits a certain distance off screen it will be destroyed (removed from map)
	 */
	public void openFire(){
		
		iterate = bulletTrajectories.entrySet().iterator();
		while (iterate.hasNext()){
			instance = iterate.next();
			xMultiplier = (instance.getValue().get(1).x);
			yMultiplier = (instance.getValue().get(1).y);
			Array<Rectangle> newProperties = new Array<Rectangle>();
			Rectangle newRec = new Rectangle();
			xCoord = instance.getValue().get(0).x+300*xMultiplier*Gdx.graphics.getDeltaTime();
			yCoord = instance.getValue().get(0).y+300*yMultiplier*Gdx.graphics.getDeltaTime();
			height = instance.getValue().get(0).height;
			width = instance.getValue().get(0).width;
			
			newProperties.add(newRec.set(xCoord, yCoord, width, height));
			newProperties.add(instance.getValue().get(1));
			newProperties.add(instance.getValue().get(2));
			instance.setValue(newProperties);
		
			if(instance.getValue().get(0).x < 0 || 
					instance.getValue().get(0).y > 480 || 
					instance.getValue().get(0).x > (800-width/2) || 
					instance.getValue().get(0).y < (0-height/2)){
				iterate.remove(); 
			}
		}
		
	}
	
	/*
	 * Persist new information for all entries in the map. If a bullet's health is updated
	 * to be zero or below, the bullet is destroyed.
	 */
	public void update(){
		iterate = bulletTrajectories.entrySet().iterator();
		while (iterate.hasNext()){
			instance = iterate.next();
			//System.out.println("size = "+ instance.getValue().size);
			health = (instance.getValue().get(2).x);
			if(health <= 0){
				iterate.remove(); 
			}
		}
		
	}
}
