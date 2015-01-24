package de.bitbrain.yolo.core;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.bitbrain.yolo.behaviors.Behavior;
import de.bitbrain.yolo.behaviors.BehaviourWrapper;
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

	private GameStateCallback gameStateCallback;

	public GameHandler(GameState state, Camera camera, GameStateCallback callback) {
		this.state = state;
		this.camera = camera;
		this.gameStateCallback = callback;
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
	
	public void removeGameObject(GameObject object) {
		state.removeGameObject(object);
		behaviors.remove(object);
	}
	
	public void addGameObject(GameObject object) {
		state.addGameObject(object);
	}
	
	public void applyBehavior(GameObject object, Behavior behavior) {
		behaviors.put(object, behavior);
	}
	
	private void initGame() {
		player = new GameObject();
		player.setSize(24f, 24f);
		player.setPosition(0, 0);
		player.setAngle(0f);
		player.setType(GameObjectType.PLAYER_1);
		applyBehavior(player, new BehaviourWrapper(new PlayerBehavior(camera, this)) {
			@Override
			public void post(GameObject target) {
				gameStateCallback.onMove(target);
			}
		} );
		state.addGameObject(player);
	}
}
