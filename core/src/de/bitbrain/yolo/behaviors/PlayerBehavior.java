package de.bitbrain.yolo.behaviors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.bitbrain.yolo.core.GameObject;

public class PlayerBehavior implements Behavior {
	
	private static final float ACCELERATION_FACTOR = 10.5f;

	@Override
	public void update(GameObject target, float delta) {
		if (Gdx.input.isKeyPressed(Keys.W)) {
			target.getAcceleration().y -= ACCELERATION_FACTOR;
		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			target.getAcceleration().x -= ACCELERATION_FACTOR;
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			target.getAcceleration().y += ACCELERATION_FACTOR;
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			target.getAcceleration().x += ACCELERATION_FACTOR;
		}
	}
}
