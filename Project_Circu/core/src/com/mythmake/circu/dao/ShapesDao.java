package com.mythmake.circu.dao;

import java.util.List;

import com.mythmake.circu.domain.Shapes;

/****
 * 
 * Functionality and explanation for below in WorldOfCircu class
 * 
 * @author Daimen Williams
 *
 */

public interface ShapesDao {
	
	List<Shapes> getShapes();
	
	Shapes idFetchShape(int id);
	
	void swapType(Shapes shape, int type);
	
	void swapBullet(Shapes shape, int bullet);
	
	void update(Shapes shape);
	
	void create(int type);
	
	void create(int type, int bullet);
	
	void destroy(Shapes shape);

}
