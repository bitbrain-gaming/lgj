package de.bitbrain.yolo.core;

import java.util.Collection;

public interface GameState {

	Collection<GameObject> getGameObjects();
	
	Collection<Player> getPlayers();
	
	void addGameObject(GameObject gameObject);
	
	void removeGameObject(GameObject gameObject);
	
	void addPlayer(Player player);
	
	void removePlayer(Player player);
}
