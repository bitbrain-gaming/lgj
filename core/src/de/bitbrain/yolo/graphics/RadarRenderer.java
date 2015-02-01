package de.bitbrain.yolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.core.GameObject;
import de.bitbrain.yolo.core.GameObjectType;

public class RadarRenderer {
	
	private Vector2 distance = new Vector2();
	
	private Sprite sprite;
	
	public RadarRenderer() {
		sprite = new Sprite(SharedAssetManager.get(Assets.TEX_OPPONENT_BATCH, Texture.class));
		sprite.setColor(new Color(1f, 1f, 1f, 0.4f));
	}

	public void render(GameObject target, GameObject player, Camera camera, Batch batch) {
		final float RADIUS = 200f;
		if (target.getType().equals(GameObjectType.PLAYER) && player != target) {
			distance.x = target.getPosition().x - (camera.position.x);
			distance.y = target.getPosition().y - (camera.position.y);
			if (distance.len() > 400f) {
				distance.nor();
				float x = camera.position.x + distance.x * RADIUS;
				float y = camera.position.y + distance.y * RADIUS;
				sprite.setRotation(distance.angle() - 90f);
				sprite.setPosition(x, y);
				sprite.setSize(50f, 50f);
				sprite.draw(batch);
			}
		}
	}
}
