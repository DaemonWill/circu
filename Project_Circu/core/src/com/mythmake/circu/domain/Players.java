package com.mythmake.circu.domain;

public class Players {
	
	//statLvls will be used as state multipliers for the player's shape
	private int playerId;
	private String name;
	private int healthLvl;
	private int powerLvl;
	private int bulkLvl;
	private int speedLvl;
	private String pass;
	
	public Players(){}
	public Players(String name, String pass){
		this.name = name;
		this.pass = pass;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealthLvl() {
		return healthLvl;
	}

	public void setHealthLvl(int healthLvl) {
		this.healthLvl = healthLvl;
	}

	public int getPowerLvl() {
		return powerLvl;
	}

	public void setPowerLvl(int powerLvl) {
		this.powerLvl = powerLvl;
	}

	public int getBulkLvl() {
		return bulkLvl;
	}

	public void setBulkLvl(int bulkLvl) {
		this.bulkLvl = bulkLvl;
	}

	public int getSpeedLvl() {
		return speedLvl;
	}

	public void setSpeedLvl(int speedLvl) {
		this.speedLvl = speedLvl;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
