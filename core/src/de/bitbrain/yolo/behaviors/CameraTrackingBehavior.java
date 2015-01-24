package de.bitbrain.yolo.behaviors;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

import de.bitbrain.yolo.core.GameObject;

public class CameraTrackingBehavior {
	
	private Camera camera;
	
	private Vector2 distance;

	public CameraTrackingBehavior(GameObject player, Camera camera) {
		this.camera = camera;
	}
	
	public void update(float delta) {
		
	}
}
