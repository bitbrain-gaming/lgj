package de.bitbrain.yolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.yolo.SharedAssetManager;

public class ParallaxMap {
	
	private Sprite sprite;
	
	private Camera camera;
	
	private float parallaxity;
	
	public ParallaxMap(String assetId, Camera camera, float parallaxity) {
		this.camera = camera;
		this.sprite = new Sprite(SharedAssetManager.get(assetId, Texture.class));
		this.parallaxity = parallaxity;
	}
	
	public void setColor(Color color) {
		sprite.setColor(color);
	}

	public void draw(Batch batch) {
		float x = camera.position.x - camera.viewportWidth / 2f;
		float y = camera.position.y - camera.viewportHeight / 2f;
		float width = camera.viewportWidth;
		float height = camera.viewportHeight;
		int boundableX = (int) Math.floor(x);
		int boundableY = (int) Math.floor(y);
		for (float tmpX = getStartX(x) + boundableX; tmpX < width
				+ sprite.getWidth() + boundableX; tmpX += sprite.getWidth()) {
			for (float tmpY = getStartY(y) + boundableY; tmpY < height
					+ sprite.getHeight() + boundableY; tmpY += sprite.getHeight()) {
				sprite.setBounds(tmpX + getXClip(x), tmpY + getYClip(y),
						sprite.getWidth(), sprite.getHeight());
				sprite.draw(batch);
			}
		}
	}
	
	private float getTargetX(float focusX) {
		return Math.round(Math.floor(-focusX / parallaxity));
	}

	private float getTargetY(float focusY) {
		return Math.round(Math.floor(-focusY / parallaxity));
	}

	private int getStartX(float focusX) {
		int startX = 0;
		if (getTargetX(focusX) > 0) {
			startX = Math.round(-sprite.getWidth());
		}
		return startX;
	}

	private int getStartY(float focusY) {
		int startY = 0;
		if (getTargetY(focusY) > 0) {
			startY = Math.round(-sprite.getHeight());
		}
		return startY;
	}

	private int getXClip(float focusX) {
		return Math.round(getTargetX(focusX) % sprite.getWidth());
	}

	private int getYClip(float focusY) {
		return Math.round(getTargetY(focusY) % sprite.getHeight());
	}
}
