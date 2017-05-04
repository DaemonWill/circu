package com.mythmake.circu.dao;

import java.util.List;

import com.mythmake.circu.domain.Types;

public interface TypesDao {

	List<Types> getTypes();
	
	Types nameFetchType(String name);
	
	Types idFetchType(int id);
	
	void changeName(Types type, String name);
	
	void changeImage(Types type, String path);
	
	void changeDimensions(Types type, int height, int width);
	
	void changeStats(Types type, int health, int power, int bulk, int speed);
	
	void update(Types type);
	
	void create(String name, String image, int width, int height, int health,
			int power, int bulk, int speed);
	
	void destroy(Types type);
}
