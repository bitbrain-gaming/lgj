package de.bitbrain.yolo.behaviors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

import de.bitbrain.yolo.core.GameObject;

public class PlayerBehavior implements Behavior {
	
	private static final float ACCELERATION_FACTOR = 20.5f;
	
	private Vector2 direction = new Vector2();
	
	private Camera camera;
	
	public PlayerBehavior(Camera camera) {
		this.camera = camera;
	}

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
		direction.x =  target.getPosition().x + camera.viewportWidth / 2f - (Gdx.input.getX() + camera.position.x);
		direction.y =  target.getPosition().y + camera.viewportHeight / 2f - (Gdx.input.getY() + camera.position.y);
		target.setAngle(direction.angle() + 90f);
	}
}
