package com.mythmake.circu.dao;

import java.util.List;

/****
 * 
 * Functionality and explanation for below in WorldOfCircu class
 * 
 * @author Daimen Williams
 *
 */

import com.mythmake.circu.domain.Players;

public interface PlayersDao {
	
	List<Players> getPlayers();
	
	Players idFetchPlayer(int id);
	
	Players nameFetchPlayer(String name);
	
	void changeStats(Players player, int health, int power, int bulk, int speed);
	
	void changePass (Players player, String pass);
	
	void changeName (Players player, String name);
	
	void update(Players player);
	
	void create(String name, String pass);
	
	void destroy(Players player);

}
