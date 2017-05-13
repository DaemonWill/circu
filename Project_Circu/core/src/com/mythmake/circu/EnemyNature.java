package com.mythmake.circu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mythmake.circu.domain.Types;

public class EnemyNature {
	
	private Map<Integer,Array<Rectangle>> enemies = new HashMap<Integer, Array<Rectangle>>();
	
	public Map<Integer,Array<Rectangle>> getEnemies(){
		return enemies;
	}
	
	private Integer instanceId = 0;
	private Iterator<Map.Entry<Integer,Array<Rectangle>>>  iterate;
	private Map.Entry<Integer,Array<Rectangle>> instance;
	float height;
	float width;
	
	public void spawnHexaminion(Types type, Rectangle playerRec){
		//System.out.println("making hexaminion "+(enemies.size()+1));
		float health = type.getHealth();
		float bulk = type.getBulk();
		Rectangle stats = new Rectangle(health,bulk,0,0);
	/*	Array<Integer> stats = new Array<Integer>();
		stats.add(health);
		stats.add(bulk); */
		Rectangle hexaminion = new Rectangle();
		hexaminion.height = type.getHeight();
		hexaminion.width = type.getWidth();
		
		//setting the spawn location of enemy a random distance away from character within screen border
		int yTop = 480 + type.getHeight();
		int yBot = 0 - type.getHeight();
		int xRight = 800 + type.getWidth();
		int xLeft = 0 - type.getWidth();
		int xCoord = 0;
		int yCoord = 0;
		int rand = ThreadLocalRandom.current().nextInt(1,5); //giving the choice of 4 possible spawn points
		switch(rand){
			case 1: xCoord = xLeft;
					yCoord = yBot;
					break;
			case 2: xCoord = xLeft;
					yCoord = yTop;
					break;
			case 3: xCoord = xRight;
					yCoord = yTop;
					break;
			case 4: xCoord = xRight;
					yCoord = yBot;
					break;
		}
		hexaminion.setCenter(xCoord, yCoord);
		Array<Rectangle> hexProperties = new Array<Rectangle>();
		hexProperties.add(hexaminion);
		hexProperties.add(stats);
		enemies.put(instanceId, hexProperties);
		instanceId++;
	}
	
	public void seekAndDestroy(Rectangle playerRec){
		System.out.println("enemies maintained = "+enemies.size());
		iterate = enemies.entrySet().iterator();
		while (iterate.hasNext()){
			Vector2 playerCoord = new Vector2();
			int playerCenterX = (int) playerRec.getCenter(playerCoord).x;
			int playerCenterY = (int) playerCoord.y;
			float newX;
			float newY;
	
			instance = iterate.next();
			if(playerCenterX > instance.getValue().get(0).x){
				newX = instance.getValue().get(0).x + 140*Gdx.graphics.getDeltaTime();
			}else{
				newX = instance.getValue().get(0).x - 140*Gdx.graphics.getDeltaTime();
			}
			if(playerCenterY > instance.getValue().get(0).y){
				newY = instance.getValue().get(0).y + 140*Gdx.graphics.getDeltaTime();
			}else{
				newY = instance.getValue().get(0).y - 140*Gdx.graphics.getDeltaTime();
			}
			Array<Rectangle> newProperties = new Array<Rectangle>();
			Rectangle newRec = new Rectangle();
			height = instance.getValue().get(0).height;
			width = instance.getValue().get(0).width;
			
			newProperties.add(newRec.set(newX, newY, width, height));
			newProperties.add(instance.getValue().get(1));
			instance.setValue(newProperties);
		}
		
	}
	

}
