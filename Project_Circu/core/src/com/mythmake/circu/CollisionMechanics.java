package com.mythmake.circu;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class CollisionMechanics {
	
	//float leastXRadius;
	float firstXRadius;
	float secondXRadius;
	//float leastYRadius;
	float firstYRadius;
	float secondYRadius;
	Vector2 firstCenter = new Vector2();
	Vector2 secondCenter = new Vector2();
	boolean collision = false;
	
	public boolean hasInteraction(Array<Rectangle> bullets, Array<Rectangle> enemies){
		firstCenter = bullets.get(0).getCenter(firstCenter);
		secondCenter = enemies.get(0).getCenter(secondCenter);
		firstXRadius = bullets.get(0).width/2;
		secondXRadius = enemies.get(0).width/2;
		/*if(firstXRadius <= secondXRadius){
			leastXRadius = firstXRadius;
		}
		else{
			leastXRadius = secondXRadius;
		}
		firstYRadius = Math.abs(bullets.get(0).height/2);
		secondYRadius = Math.abs(enemies.get(0).height/2);
		if(firstYRadius <= secondYRadius){
			leastYRadius = firstYRadius;
		}
		else{
			leastYRadius = secondYRadius;
		} */
		
		if((Math.abs(firstCenter.x - secondCenter.x) <= (firstXRadius + secondXRadius)) ||
				(Math.abs(firstCenter.y - secondCenter.y) <= (firstYRadius + secondYRadius))	){
			collision = true;
			System.out.println("enemy bullet collision occured!");
		} else{
			collision = false;
		}
		return collision;
	}

}
