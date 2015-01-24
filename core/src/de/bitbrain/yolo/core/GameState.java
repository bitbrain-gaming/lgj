package de.bitbrain.yolo.core;

import java.util.Map;

public interface GameState {

	Map<Integer, GameObject> getGameObjects();

	void addGameObject(GameObject gameObject);

	void removeGameObject(GameObject gameObject);

}
