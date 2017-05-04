package com.mythmake.circu.domain;

public class Bullets {
	
	/*
	Bullets will have similar attributes to shape types; however, bulk is used 
	directly as damage calculation. Bullets' bulk will be affected by shape type's 
	power - all damage done will be based off a collision damage calculation with
	both objects' bulk stat in mind.
	*/
	private int bulletId;
	private String name;
	private String image;
	private int health;
	private int bulk;
	private int speed;
	private int height;
	private int width;
	
	public Bullets(){}
	public Bullets(String name, String image, int health, int bulk, int speed,
					int height, int width){
		this.name = name;
		this.image = image;
		this.health = health;
		this.bulk = bulk;
		this.speed = speed;
		this.height = height;
		this.width = width;
	}

	public int getBulletId() {
		return bulletId;
	}

	public void setBulletId(int bulletId) {
		this.bulletId = bulletId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getBulk() {
		return bulk;
	}

	public void setBulk(int bulk) {
		this.bulk = bulk;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getwidth() {
		return width;
	}

	public void setwidth(int width) {
		this.width = width;
	}

}
