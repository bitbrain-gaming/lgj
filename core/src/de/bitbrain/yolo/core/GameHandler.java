package de.bitbrain.yolo.core;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.bitbrain.yolo.behaviors.Behavior;
import de.bitbrain.yolo.behaviors.CameraTrackingBehavior;
import de.bitbrain.yolo.behaviors.PlayerBehavior;

public class GameHandler {
	
	private GameState state;
	
	private Renderer renderer;
	
	private Physics physics;
	
	private Map<GameObject, Behavior> behaviors;
	
	private GameObject player;
	
	private CameraTrackingBehavior cameraBehavior;
	
	public GameHandler(GameState state, Camera camera) {
		this.state = state;
		physics = new Physics();
		behaviors = new HashMap<GameObject, Behavior>();
		this.renderer = new Renderer();
		initGame();
		cameraBehavior = new CameraTrackingBehavior(player, camera);
	}

	public void updateAndRender(float delta, Batch batch) {
		for (GameObject object : state) {
			Behavior behavior = behaviors.get(object);
			if (behavior != null) {
				behavior.update(object, delta);
			}
			physics.apply(object, delta);
			renderer.render(object, batch);
		}
		cameraBehavior.update(delta);
	}
	
	public void applyBehavior(GameObject object, Behavior behavior) {
		behaviors.put(object, behavior);
	}
	
	private void initGame() {
		player = new GameObject();
		player.setSize(24f, 24f);
		player.setPosition(250, 250);
		player.setAngle(35f);
		applyBehavior(player, new PlayerBehavior());
		state.addGameObject(player);
	}
}
