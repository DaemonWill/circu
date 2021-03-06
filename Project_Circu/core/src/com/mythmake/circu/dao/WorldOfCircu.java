package com.mythmake.circu.dao;

import java.util.ArrayList;
import java.util.List;

import com.mythmake.circu.domain.Bullets;
import com.mythmake.circu.domain.Players;
import com.mythmake.circu.domain.Shapes;
import com.mythmake.circu.domain.Types;


/****
 * 
 * Concrete methods for persisting, and interacting with domain entities as
 *  game operates.
 * 
 * 
 * @author Daimen Williams
 *
 */
public class WorldOfCircu implements BulletsDao, PlayersDao, ShapesDao, TypesDao {
	
	//Instantiated lists to hold in game domain objects and store their relative generic data
	ArrayList<Bullets> bullets = (ArrayList<Bullets>) getBullets();
	ArrayList<Types> types = (ArrayList<Types>) getTypes();
	ArrayList<Shapes> shapes = (ArrayList<Shapes>) getShapes();
	
	//A create method for each major domain entity. Adds a new object to appropriate
	//list upon creation. Objects are tracked with a unique primary key (int id)
	@Override
	public void create(int type) {
		// create basic shape method (no bullets associated, just type)
		Shapes shape = new Shapes(type);
		if(shapes.size()==0){
			shape.setShapeId(1);
			shapes.add(shape);
		}
		else{
			shape.setShapeId(shapes.size()+1);
			shapes.add(shape);
		}
		//update(shape);
	}
	@Override
	public void create(int type, int bullet) {
		// create shapes method overloaded to include bullets
		Shapes shape = new Shapes(type,bullet);
		if(shapes.size()==0){
			shape.setShapeId(1);
			shapes.add(shape);
		}
		else{
			shape.setShapeId(shapes.size()+1);
			shapes.add(shape);
		}
		//update(shape);
	}
	@Override
	public void create(String name, String image, int width, int height, int health, 
						int power, int bulk, int speed) {
		// create types method
		Types type = new Types(name,image,width,height,health,power,bulk,speed);
		if(types.size()==0){
			type.setTypeId(1);
			types.add(type);
		}
		else{
			type.setTypeId(types.size()+1);
			types.add(type);
		}
		//update(type);
	}
	@Override
	public void create(String name, String image, int health, int bulk, int speed, int height, int width) {
		//Create Bullets Method
		Bullets bullet = new Bullets(name,image,health,bulk,speed,height,width);
		if(bullets.size()==0){
			bullet.setBulletId(1);
			bullets.add(bullet);
		}
		else{
			bullet.setBulletId(bullets.size()+1);
			bullets.add(bullet);
		}
		//update(bullet);
	}
	//not currently implemented
	@Override
	public void create(String name, String pass) {
		// create players method
	}
	
	
	
	
	//temporary method to set up player shape this process can be optimized
	public void aCircuIsBorn(){
		//instantiate and (sort of) persist shape type
		create("circu","redchar.png",60,60,10,10,10,10); 
		//same for basic bullet properties
		create("playerBulletBasic","charbullet.png",2,2,8,15,15);
		//same for shape - a shape has type and bullets (id,id) 
		create(1,1);
	}
	//temporary method to set the properties of a basic enemy type
	public void hexaminionIsBorn(){
		//instantiate and (sort of) persist shape type
		create("hexaminion","hexaminion.png",70,70,4,0,1,7); 
		//same for shape - basic enemy shape just has type, no bullets 
		create(2);
		//hexaminion shape and type should now be persisted as game runs, later should 
		//just make a simple data base with these attributes already stored.
	}
	
	
	
	//get methods returning an instance of the respective domain entity list
	@Override
	public List<Types> getTypes() {
		//Will Later include functionality of fetching types from database, but for right now we
		//can just pass a new list
		List<Types> types = new ArrayList<Types>();
		return types;
	}
	@Override
	public List<Shapes> getShapes() {
		//Will Later include functionality of fetching shapes from database, but for right now we
				//can just pass a new list
		List<Shapes> shapes = new ArrayList<Shapes>();
		return shapes;
	}
	@Override
	public List<Bullets> getBullets() {
		//Will Later include functionality of fetching bullets from database, but for right now we
				//can just pass a new list
		List<Bullets> bullets = new ArrayList<Bullets>();
		return bullets;
	}
	//not currently implemented
	@Override
	public List<Players> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//return the requested entity in a given list by its "name" field
	//none are currently implemented
	@Override
	public Types nameFetchType(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Bullets nameFetchBullet(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Players nameFetchPlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//return the requested entity in a given list by its unique key in the list (id)
	@Override
	public Shapes idFetchShape(int id) {
		Shapes shape = shapes.get(id-1);
		return shape;
	}
	@Override
	public Types idFetchType(int id) {
		Types type = types.get(id-1);
		return type;
	}
	@Override
	public Bullets idFetchBullet(int id) {
		Bullets bullet = bullets.get(id-1);
		return bullet;
	}
	//not currently implemented
	@Override
	public Players idFetchPlayer(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//methods to change certain fields of a requested entity
	@Override
	public void changeName(Types type, String name) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void changeName(Players player, String name) {
		// TODO Auto-generated method stub
	}
	@Override
	public void changeName(Bullets bullet, String name) {
		// TODO Auto-generated method stub
	}
	@Override
	public void changeImage(Types type, String path) {
		// TODO Auto-generated method stub
	}
	@Override
	public void changeImage(Bullets bullet, String path) {
		// TODO Auto-generated method stub
	}
	@Override
	public void changeDimensions(Types type, int height, int width) {
		// TODO Auto-generated method stub
	}
	@Override
	public void changeDimensions(Bullets bullet, int height, int width) {
		// TODO Auto-generated method stub
	}
	@Override
	public void changeStats(Types type, int health, int power, int bulk, int speed) {
		// TODO Auto-generated method stub
	}
	@Override
	public void changeStats(Players player, int health, int power, int bulk, int speed) {
		// TODO Auto-generated method stub
	}
	@Override
	public void changeStats(Bullets bullet, int health, int bulk, int speed) {
		// TODO Auto-generated method stub
	}
	@Override
	public void changePass(Players player, String pass) {
		// TODO Auto-generated method stub
	}
	@Override
	public void swapType(Shapes shape, int type) {
		// TODO Auto-generated method stub
	}
	@Override
	public void swapBullet(Shapes shape, int bullet) {
		// TODO Auto-generated method stub
	}
	
	
	
	//methods to update the respective list with new changes for a given element
	@Override
	public void update(Types type) {
		// TODO Auto-generated method stub
	}
	@Override
	public void update(Shapes shape) {
		// TODO Auto-generated method stub
	}
	@Override
	public void update(Players player) {
		// TODO Auto-generated method stub
	}
	@Override
	public void update(Bullets bullet) {
		//should save changes to list to database
	}
	
	
	
	//methods meant to erase/destroy a given element and all its data
	@Override
	public void destroy(Types type) {
		// TODO Auto-generated method stub
	}
	@Override
	public void destroy(Shapes shape) {
		// TODO Auto-generated method stub
	}
	@Override
	public void destroy(Players player) {
		// TODO Auto-generated method stub
	}
	@Override
	public void destroy(Bullets bullet) {
		// TODO Auto-generated method stub
	}

}
