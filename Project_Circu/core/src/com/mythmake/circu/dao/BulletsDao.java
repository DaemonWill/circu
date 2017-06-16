package com.mythmake.circu.dao;

import java.util.List;

import com.mythmake.circu.domain.Bullets;

/****
 * 
 * Functionality and explanation for below in WorldOfCircu class
 * 
 * @author Daimen Williams
 *
 */

public interface BulletsDao {
	
	List<Bullets> getBullets();
	
	Bullets nameFetchBullet(String name);
	
	Bullets idFetchBullet(int id);
	
	void changeName(Bullets bullet, String name);
	
	void changeImage(Bullets bullet, String path);
	
	void changeDimensions(Bullets bullet, int height, int width);
	
	void changeStats(Bullets bullet, int health, int bulk, int speed);
	
	void update(Bullets bullet);
	
	void create(String name, String image, int health, int bulk, int speed,
					int height, int width);
	
	void destroy(Bullets bullet);
}
