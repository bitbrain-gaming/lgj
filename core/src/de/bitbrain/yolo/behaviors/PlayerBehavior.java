package de.bitbrain.yolo.behaviors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

import de.bitbrain.yolo.core.GameHandler;
import de.bitbrain.yolo.core.GameObject;
import de.bitbrain.yolo.core.GameObjectType;
import de.bitbrain.yolo.util.Timer;

public class PlayerBehavior implements Behavior {

	private static final float ACCELERATION_FACTOR = 20.5f;

	private static final float SHOOT_INTERVAL = 0.1f;

	private static final float SHOOT_SPEED = 800f;

	private Vector2 direction = new Vector2();

	private Camera camera;

	private GameHandler gameHandler;

	private Timer shootTimer = new Timer();

	public PlayerBehavior(Camera camera, GameHandler handler) {
		this.camera = camera;
		this.gameHandler = handler;
	}

	@Override
	public void update(GameObject target, float delta) {
		shootTimer.update(delta);
		if (Gdx.input.isKeyPressed(Keys.W)) {
			target.getAcceleration().y -= ACCELERATION_FACTOR;
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			target.getAcceleration().y += ACCELERATION_FACTOR;
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			target.getAcceleration().x -= ACCELERATION_FACTOR;
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			target.getAcceleration().x += ACCELERATION_FACTOR;
		}

		direction.x = target.getPosition().x + camera.viewportWidth / 2f
				- (Gdx.input.getX() + camera.position.x);
		direction.y = target.getPosition().y + camera.viewportHeight / 2f
				- (Gdx.input.getY() + camera.position.y);
		target.setAngle(direction.angle() + 90f);
		if (Gdx.input.isButtonPressed(Buttons.LEFT)
				&& shootTimer.reached(SHOOT_INTERVAL)) {
			shoot(target);
			shootTimer.reset();
		}
	}

	private void shoot(GameObject ship) {
		GameObject projectile = new GameObject();
		projectile.setType(GameObjectType.PROJECTILE);
		projectile.setPosition(ship.getPosition().x + ship.getSize().x / 2f,
				ship.getPosition().y + ship.getSize().y / 2f);
		gameHandler.applyBehavior(projectile, new ProjectileBehavior(
				gameHandler));
		projectile.setAngle(direction.angle());
		projectile.setSize(12f, 12f);
		final float speed = SHOOT_SPEED + ship.getVelocity().len();
		direction.nor();
		direction.setAngle(direction.angle() - 180f);
		projectile.setVelocity(direction.x * speed, direction.y * speed);
		gameHandler.addGameObject(projectile);
	}
}
