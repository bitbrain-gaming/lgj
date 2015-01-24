package de.bitbrain.yolo.core;

public class Player {

	private int minShield, maxShield;
	
	private GameObject ship;
	
	public Player(GameObject ship, int shield) {
		this.ship = ship;
		maxShield = shield;
		minShield = shield;
	}
	
	public GameObject getShip() {
		return ship;
	}
	
	public int getMinShield() {
		return minShield;
	}
	
	public int getMaxShield() {
		return maxShield;
	}
	
	public void reset() {
		minShield = maxShield;
	}
	
	/**
	 * Returns true if dead 
	 * 
	 * @param damage
	 * @return
	 */
	public boolean damage(int damage) {
		if (minShield < 0) {
			return true;
		} else {
			minShield -= damage;
		}
		return minShield < 0;
	}
}
