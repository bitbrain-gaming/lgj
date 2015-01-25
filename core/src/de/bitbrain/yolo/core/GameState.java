package de.bitbrain.yolo.core;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameState implements Iterable<GameObject> {

	private Map<String, GameObject> objects;
	
	private Player player;

	private GameStateListener listener;

	public GameState() {
		objects = new ConcurrentHashMap<String, GameObject>();
	}
	
	public GameObject getGameObject(String id) {
		return objects.get(id);
	}
	
	public void setListener(GameStateListener l) {
		listener = l;
	}

	public void addGameObject(GameObject gameObject) {
		objects.put(gameObject.getId(), gameObject);
		if (listener != null) {
			listener.onAddGameObject(gameObject);
		}
	}

	public void onShipDestroyed(GameObject object){
		if (listener != null) listener.onShipDestroyed(object);
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


	public static interface GameStateListener {
		void onAddGameObject(GameObject object);
		void onShipDestroyed(GameObject object);
	}
}
