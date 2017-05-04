package com.mythmake.circu.dao;

import java.util.List;

import com.mythmake.circu.domain.Shapes;

public interface ShapesDao {
	
	List<Shapes> getShapes();
	
	Shapes idFetchShape(int id);
	
	void swapType(Shapes shape, int type);
	
	void swapBullet(Shapes shape, int bullet);
	
	void update(Shapes shape);
	
	void create(int type, int bullet);
	
	void destroy(Shapes shape);

}
