package de.bitbrain.yolo.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameState implements Iterable<GameObject> {
	
	private Map<Integer, GameObject> objects;
	
	public GameState() {
		objects = new HashMap<Integer, GameObject>();
	}
	
	public GameObject getGameObject(int id) {
		return objects.get(id);
	}

	void addGameObject(GameObject gameObject) {
		objects.put(gameObject.getId(), gameObject);
	}

	void removeGameObject(GameObject gameObject) {
		objects.remove(gameObject.getId());
	}

	@Override
	public Iterator<GameObject> iterator() {
		return objects.values().iterator();
	}

}
