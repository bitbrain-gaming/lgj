package de.bitbrain.yolo.core;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.bitbrain.yolo.behaviors.Behavior;
import de.bitbrain.yolo.behaviors.CameraTrackingBehavior;
import de.bitbrain.yolo.behaviors.PlayerBehavior;
import de.bitbrain.yolo.graphics.Renderer;

public class GameHandler {
	
	private GameState state;
	
	private Renderer renderer;
	
	private Physics physics;
	
	private Map<GameObject, Behavior> behaviors;
	
	private GameObject player;
	
	private CameraTrackingBehavior cameraBehavior;
	
	private Camera camera;
	
	public GameHandler(GameState state, Camera camera) {
		this.state = state;
		this.camera = camera;
		physics = new Physics();
		behaviors = new HashMap<GameObject, Behavior>();
		this.renderer = new Renderer();
		initGame();
		cameraBehavior = new CameraTrackingBehavior(player, camera);
		cameraBehavior.focus();
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
		player.setPosition(0, 0);
		player.setAngle(0f);
		applyBehavior(player, new PlayerBehavior(camera));
		state.addGameObject(player);
	}
}
