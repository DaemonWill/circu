package com.mythmake.circu.domain;

public class Shapes {
	
	//Shape primary and foreign keys used to later identify specific character 
	//attributes through future database implementation
	private int shapeId;
	private int typeId;
	private int bulletId;
	
	public Shapes(){}
	public Shapes(int type){
		super();
		this.typeId = type;
	}
	public Shapes(int type, int bullet){
		super();
		this.typeId = type;
		this.bulletId = bullet;
	}
	
	
	//defining getters and setters below. 
	public int getShapeId() {
		return shapeId;
	}
	public void setShapeId(int shapeId) {
		this.shapeId = shapeId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getBulletId() {
		return bulletId;
	}
	public void setBulletId(int bulletId) {
		this.bulletId = bulletId;
	}

	
}
