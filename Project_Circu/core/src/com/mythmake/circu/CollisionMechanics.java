package com.mythmake.circu;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class CollisionMechanics {
	
	float firstXRadius;
	float secondXRadius;
	float firstYRadius;
	float secondYRadius;
	Vector2 firstCenter = new Vector2();
	Vector2 secondCenter = new Vector2();
	boolean collision = false;
	double dam;
	
	public boolean hasInteraction(Array<Rectangle> friendly, Array<Rectangle> hostile){
		firstCenter = friendly.get(0).getCenter(firstCenter);
		secondCenter = hostile.get(0).getCenter(secondCenter);
		firstXRadius = friendly.get(0).width/2;
		secondXRadius = hostile.get(0).width/2;

		if((Math.abs(firstCenter.x - secondCenter.x) <= (firstXRadius + secondXRadius)) ||
				(Math.abs(firstCenter.y - secondCenter.y) <= (firstYRadius + secondYRadius))	){
			collision = true;
			//System.out.println("enemy bullet collision occured!");
		} else{
			collision = false;
		}
		return collision;
	}
	
	public double calcDamage(Rectangle source){
		//y is bulk, x is health
		dam = .25 * source.y + 2;
		return dam;
	}
	
	public void damage(Rectangle friendly, Rectangle hostile){ //these are the stats rectangles for the element
		friendly.x -= calcDamage(hostile);
		hostile.x -= calcDamage(friendly);
	}

}
