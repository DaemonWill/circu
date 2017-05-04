package com.mythmake.circu.domain;

public class Types {

	//specific character attributes for each shape type
	private int typeId;
	private String name;
	private String image;
	private int width;
	private int height;
	private int health;
	private int power;
	private int bulk;
	private int speed;
	
	public Types(){}
	public Types(String name, String image, int width, int height, int health,
					int power, int bulk, int speed){
		this.name = name;
		this.image = image;
		this.width = width;
		this.height = height;
		this.health = health;
		this.power = power;
		this.bulk = bulk;
		this.speed = speed;
	}
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
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

	
}
