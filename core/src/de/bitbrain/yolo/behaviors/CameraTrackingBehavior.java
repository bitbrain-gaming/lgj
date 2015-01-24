package de.bitbrain.yolo.behaviors;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

import de.bitbrain.yolo.core.GameObject;

public class CameraTrackingBehavior {

	private Camera camera;

	private Vector2 velocity;

	private GameObject player;

	public CameraTrackingBehavior(GameObject player, Camera camera) {
		this.camera = camera;
		this.player = player;
		velocity = new Vector2();
	}

	public void update(float delta) {
		/*velocity.x = (float) (player.getPosition().x
				+ Math.floor(player.getSize().x / 2.0f) - (camera.position.x));
		velocity.y = (float) (player.getPosition().y
				+ Math.floor(player.getSize().y / 2.0f) - (camera.position.y));

		float distance = velocity.len();
		velocity = velocity.nor();

		if (distance <= 0.05f) {
			focus();
		} else {
			double speed = (40.0 + delta) * distance;

			// Round it up to prevent camera shaking
			camera.position.x = (float) (camera.position.x + velocity.x * speed);
			camera.position.y = (float) (camera.position.y + velocity.y * speed);
		}*/
		//focus();
	}

	private void focus() {
		camera.position.x = player.getPosition().x;
		camera.position.y = player.getPosition().y;
	}
}
