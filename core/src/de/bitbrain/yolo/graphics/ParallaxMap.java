package de.bitbrain.yolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.yolo.SharedAssetManager;

public class ParallaxMap {
	
	private Sprite sprite;
	
	private Camera camera;
	
	public ParallaxMap(String assetId, Camera camera) {
		this.camera = camera;
		this.sprite = new Sprite(SharedAssetManager.get(assetId, Texture.class));
	}

	public void draw(Batch batch) {
		sprite.setBounds(0f, 0f, camera.viewportWidth, camera.viewportHeight);
		sprite.draw(batch);
	}
}
