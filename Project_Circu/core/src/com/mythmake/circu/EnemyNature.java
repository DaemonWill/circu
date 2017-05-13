package com.mythmake.circu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mythmake.circu.domain.Types;

public class EnemyNature {
	
	private Map<Rectangle,Array<Integer>> enemies = new HashMap<Rectangle, Array<Integer>>();
	
	public Map<Rectangle,Array<Integer>> getEnemies(){
		return enemies;
	}
	
	//private Iterator<Map.Entry<Rectangle,Array<Integer>>>  iterate;
	//private Map.Entry<Rectangle,Array<Integer>> instance;
	
	public void spawnHexaminion(Types type, Rectangle playerRec){
		Integer health = type.getHealth();
		Integer bulk = type.getBulk();
		Array<Integer> stats = new Array<Integer>();
		stats.add(health);
		stats.add(bulk);
		Vector2 playerCoord = new Vector2();
		Rectangle hexaminion = new Rectangle();
		hexaminion.height = type.getHeight();
		hexaminion.width = type.getWidth();
		int playerCenterX = (int) playerRec.getCenter(playerCoord).x;
		int playerCenterY = (int) playerCoord.y;
		
		//setting the spawn location of enemy a random distance away from character within screen border
		int minMagnitudeX = (int) (2.5*playerRec.getWidth());
		int maxMagnitudeX = 800-(type.getWidth()/2)-minMagnitudeX;
		int minMagnitudeY = (int) (2.5*playerRec.getWidth());
		int maxMagnitudeY = 480-(type.getHeight()/2)-minMagnitudeY;
		int xMag = ThreadLocalRandom.current().nextInt(minMagnitudeX, maxMagnitudeX+1);
		int yMag = ThreadLocalRandom.current().nextInt(minMagnitudeY, maxMagnitudeY+1);
		boolean xNeg = ThreadLocalRandom.current().nextBoolean();
		boolean yNeg = ThreadLocalRandom.current().nextBoolean();
		int xCoord;
		int yCoord;
		if(xNeg==true){
			xCoord = playerCenterX-xMag;
		}else{
			xCoord = playerCenterX+xMag;
		}
		if(yNeg==true){
			yCoord = playerCenterY-yMag;
		}else{
			yCoord = playerCenterY+yMag;
		}
		hexaminion.setCenter(xCoord, yCoord);
		enemies.put(hexaminion,stats);
	}
	

}
