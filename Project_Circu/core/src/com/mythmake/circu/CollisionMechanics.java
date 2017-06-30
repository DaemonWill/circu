package com.mythmake.circu;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/****
 * 
 * Class contains logic regarding when to tell objects collide with each other and how much damage to 
 * remove from both party's health stats if applicable. (collision is mutual destruction)
 * 
 * @author Daimen Williams
 *
 */

public class CollisionMechanics {
	
	float firstXRadius;
	float secondXRadius;
	float firstYRadius;
	float secondYRadius;
	float deltaX;
	float deltaY;
	float distance;
	Vector2 firstCenter = new Vector2();
	Vector2 secondCenter = new Vector2();
	boolean collision;
	double dam;
	
	public boolean hasInteraction(Array<Rectangle> friendly, Array<Rectangle> hostile){
		firstCenter = friendly.get(0).getCenter(firstCenter);
		secondCenter = hostile.get(0).getCenter(secondCenter);
		deltaX = Math.abs(firstCenter.x - secondCenter.x);
		deltaY = Math.abs(firstCenter.y - secondCenter.y);
		distance = (float) Math.pow(Math.pow(deltaX, 2) + Math.pow(deltaY, 2), 0.5);
		firstXRadius = friendly.get(0).width/2;
		secondXRadius = hostile.get(0).width/2;
		
		//assuming circular hitboxes, the distance between midpoints upon contact will have to be
		//equal to or less than the sum of both object's radii 
		/*if((Math.abs(firstCenter.x - secondCenter.x) <= (firstXRadius + secondXRadius)) ||
				(Math.abs(firstCenter.y - secondCenter.y) <= (firstYRadius + secondYRadius))	){
			collision = true;
			System.out.println("enemy collision occured!");
		} */
		if(distance <= (firstXRadius + secondXRadius)){
			collision = true;
			System.out.println("collision has occurred");
		}
		else{
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
