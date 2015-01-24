package de.bitbrain.yolo.core;

import com.badlogic.gdx.math.Vector2;

public class Physics {

	public static final float MIN_SPEED = 20f;

	public static final float MAX_SPEED = 230f;

	public static final float MAX_ACCEL = 25f;

	public void apply(GameObject gameObject, float delta) {

		if (!gameObject.getType().equals(GameObjectType.PROJECTILE)) {
			if (gameObject.getAcceleration().x > MAX_ACCEL) {
				gameObject.getAcceleration().x = MAX_ACCEL;
			}
			if (gameObject.getAcceleration().y > MAX_ACCEL) {
				gameObject.getAcceleration().y = MAX_ACCEL;
			}
			gameObject.getAcceleration().scl(0.4f);

			if (gameObject.getAcceleration().len() < 5f
					&& gameObject.getVelocity().len() > MIN_SPEED) {
				gameObject.getVelocity().scl(0.995f);
			} else if (gameObject.getVelocity().len() < MIN_SPEED) {
				Vector2 corrected = gameObject.getVelocity().nor();
				corrected.scl(MIN_SPEED, MIN_SPEED);
			}

			gameObject
					.setVelocity(
							gameObject.getVelocity().x
									+ gameObject.getAcceleration().x,
							gameObject.getVelocity().y
									+ gameObject.getAcceleration().y);
			if (gameObject.getVelocity().len() > MAX_SPEED) {
				Vector2 corrected = gameObject.getVelocity().nor();
				corrected.scl(MAX_SPEED, MAX_SPEED);
			}
		}
		gameObject.setPosition(
				gameObject.getPosition().x
						+ (gameObject.getVelocity().x * delta),
				gameObject.getPosition().y
						+ (gameObject.getVelocity().y * delta));
	}
}
