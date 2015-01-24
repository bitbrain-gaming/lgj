package de.bitbrain.yolo.core;

import com.badlogic.gdx.math.Vector2;

public class Physics {

	public static final float MIN_SPEED = 30f;

	public static final float MAX_SPEED = 250f;

	public void apply(GameObject gameObject, float delta) {
		gameObject.setVelocity(
				gameObject.getVelocity().x + gameObject.getAcceleration().x,
				gameObject.getVelocity().y + gameObject.getAcceleration().y);
		if (gameObject.getVelocity().len() > MAX_SPEED) {
			Vector2 corrected = gameObject.getVelocity().nor();
			corrected.scl(MAX_SPEED, MAX_SPEED);
		}
		gameObject.setPosition(
				gameObject.getPosition().x
						+ (gameObject.getVelocity().x * delta),
				gameObject.getPosition().y
						+ (gameObject.getVelocity().y * delta));
	}
}
