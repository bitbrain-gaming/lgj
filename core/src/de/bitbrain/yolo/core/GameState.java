package de.bitbrain.yolo.core;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameState implements Iterable<GameObject> {
	
	private Map<String, GameObject> objects;
	
	private Player player;
	
	public GameState() {
		objects = new ConcurrentHashMap<String, GameObject>();
	}
	
	public GameObject getGameObject(String id) {
		return objects.get(id);
	}

	public void addGameObject(GameObject gameObject) {
		objects.put(gameObject.getId(), gameObject);
	}

	public void removeGameObject(GameObject gameObject) {
		objects.remove(gameObject.getId());
	}

	@Override
	public Iterator<GameObject> iterator() {
		return objects.values().iterator();
	}

	public int size() {
		return objects.size();
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

}
