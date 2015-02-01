package de.bitbrain.yolo.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.bitbrain.yolo.behaviors.Behavior;
import de.bitbrain.yolo.behaviors.BehaviourWrapper;
import de.bitbrain.yolo.behaviors.CameraTrackingBehavior;
import de.bitbrain.yolo.behaviors.PlayerBehavior;
import de.bitbrain.yolo.graphics.CameraShaker;
import de.bitbrain.yolo.graphics.RadarRenderer;
import de.bitbrain.yolo.graphics.Renderer;

public class GameHandler {

	private Random random;

	private GameState state;

	private Renderer renderer;

	private Physics physics;

	private Map<GameObject, Behavior> behaviors;

	private GameObject playerShip;

	private CameraTrackingBehavior cameraBehavior;

	private Camera camera;

	private GameStateCallback gameStateCallback;

	private CollisionDetector collisionDetector;

	private RadarRenderer radarRenderer;

	private TweenManager tweenManager;

	public GameHandler(GameState state, Camera camera,
			GameStateCallback callback, TweenManager tweenManager) {
		this.tweenManager = tweenManager;
		this.random = new Random();
		this.state = state;
		this.camera = camera;
		this.gameStateCallback = callback;
		collisionDetector = new CollisionDetector(state);
		physics = new Physics();
		behaviors = new HashMap<GameObject, Behavior>();
		this.renderer = new Renderer();
		initGame();
		cameraBehavior = new CameraTrackingBehavior(playerShip, camera);
		radarRenderer = new RadarRenderer();
	}

	public void updateAndRender(float delta, Batch batch) {
		GameObject playerShip = state.getPlayer().getShip();
		for (GameObject object : state) {
			Behavior behavior = behaviors.get(object);
			if (behavior != null) {
				behavior.update(object, delta);
			}
			physics.apply(object, delta);
			if (object.getType().equals(GameObjectType.PLAYER)) {
				GameObject target = collisionDetector.getCollision(object);

				if (target != null
						&& target.getType().equals(GameObjectType.PROJECTILE)) {
					if (playerShip.equals(object)) {
						CameraShaker.shake(10, camera, tweenManager);
						state.getPlayer().damage(25);
					}
					if (state.getPlayer().isDead()) {
						gameStateCallback.onGameOver(playerShip);
						respawn(state.getPlayer());
					}
					removeGameObject(target);
				}
			}
			renderer.render(object, batch);
			radarRenderer.render(object, playerShip, camera, batch);
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
		player.getShip().setPosition((float) Math.random() * 2000f,
				(float) Math.random() * 2000f);
		cameraBehavior.focus();
	}

	private void initGame() {
		playerShip = new GameObject();
		playerShip.setSize(24f, 24f);
		playerShip.setAngle(0f);
		playerShip.setType(GameObjectType.PLAYER);
		applyBehavior(playerShip, new BehaviourWrapper(new PlayerBehavior(
				camera, this, gameStateCallback)) {
			@Override
			public void post(GameObject target) {
				gameStateCallback.onMove(target);
			}
		});
		state.addGameObject(playerShip);
		state.setPlayer(new Player(playerShip, 100));

		gameStateCallback.onReady();
	}
}
