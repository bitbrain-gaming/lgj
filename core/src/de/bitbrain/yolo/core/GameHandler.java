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

	private GameObject playerShip;

	private Player player;

	private CameraTrackingBehavior cameraBehavior;

	private Camera camera;

	private GameStateCallback gameStateCallback;

	public GameHandler(GameState state, Camera camera,
			GameStateCallback callback) {
		this.state = state;
		this.camera = camera;
		this.gameStateCallback = callback;
		physics = new Physics();
		behaviors = new HashMap<GameObject, Behavior>();
		this.renderer = new Renderer();
		initGame();
		cameraBehavior = new CameraTrackingBehavior(playerShip, camera);
		cameraBehavior.focus();
		respawn(player);
	}

	public Player getPlayer() {
		return player;
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

	public void respawn(Player player) {
		player.reset();
		player.getShip().setPosition((float) Math.random() * 3000f,
				(float) Math.random() * 3000f);
	}

	private void initGame() {
		playerShip = new GameObject();
		playerShip.setSize(24f, 24f);
		playerShip.setPosition(0, 0);
		playerShip.setAngle(0f);
		playerShip.setType(GameObjectType.PLAYER_1);
		applyBehavior(playerShip, new BehaviourWrapper(new PlayerBehavior(
				camera, this)) {
			@Override
			public void post(GameObject target) {
				gameStateCallback.onMove(target);
			}
		});
		state.addGameObject(playerShip);
		player = new Player(playerShip, 100);
	}
}
