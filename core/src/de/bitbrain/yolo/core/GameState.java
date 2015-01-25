package de.bitbrain.yolo.core;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameState implements Iterable<GameObject> {
	
	private Map<String, GameObject> objects;
	
	private Player player;

	private boolean didWin = false;
	
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


	public boolean didWin() {
		return didWin;
	}

	public void setDidWin() {
		this.didWin = true;
	}
	
	public static interface GameStateListener {
		void onAddGameObject(GameObject object);
	}
}
