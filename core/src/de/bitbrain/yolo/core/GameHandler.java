package de.bitbrain.yolo.core;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.bitbrain.yolo.FXBattery;
import de.bitbrain.yolo.behaviors.Behavior;
import de.bitbrain.yolo.behaviors.BehaviourWrapper;
import de.bitbrain.yolo.behaviors.CameraTrackingBehavior;
import de.bitbrain.yolo.behaviors.PlayerBehavior;
import de.bitbrain.yolo.graphics.*;

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

	private AnimationRenderer animationRenderer;

	private FontAnimator fontAnimator;

	public GameHandler(GameState state, Camera camera,
			GameStateCallback callback, TweenManager tweenManager, Stage stage) {
		this.tweenManager = tweenManager;
		this.random = new Random();
		this.state = state;
		this.fontAnimator = new FontAnimator(tweenManager,stage);
		this.camera = camera;
		this.gameStateCallback = callback;
		collisionDetector = new CollisionDetector(state);
		physics = new Physics();
		behaviors = new HashMap<GameObject, Behavior>();
		this.renderer = new Renderer();
		initGame();
		cameraBehavior = new CameraTrackingBehavior(playerShip, camera);
		radarRenderer = new RadarRenderer();
		this.animationRenderer =new AnimationRenderer(tweenManager,stage);
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

						CameraShaker.shake(90, camera, tweenManager);
						state.getPlayer().damage(25);
						animationRenderer.playRandomAnimation();
						FXBattery.getSound().play();
						fontAnimator.animateRandomString();

					}
					if (state.getPlayer().isDead()) {

						for (int i = 0; i < 10; i++) {
							animationRenderer.playRandomAnimation();
							FXBattery.getSound().play();
						}
						animationRenderer.playRandomSoundAnimation();
						fontAnimator.animateRandomString();

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
		player.getShip().setPosition((float) Math.random() * 4000f,
				(float) Math.random() * 4000f);
		cameraBehavior.focus();
	}

	private void initGame() {
		playerShip = new GameObject();
		playerShip.setSize(44f, 44f);
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
