package de.bitbrain.yolo.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.SharedAssetManager;

public class Renderer {
	
	private Sprite playerSprite, projectileSprite;
	
	public Renderer() {
		playerSprite = new Sprite(SharedAssetManager.get(Assets.TEX_SHIP, Texture.class));
		projectileSprite = new Sprite(SharedAssetManager.get(Assets.TEX_SHOT, Texture.class));
	}

	public void render(GameObject o, Batch batch) {
		
		Sprite sprite = playerSprite;
		
		switch (o.getType()) {
		case PLAYER_2:

			sprite.setColor(Color.RED);
			break;
		case PROJECTILE:
			sprite = projectileSprite;
			break;
		default:
			break;
		
		}
		sprite.setBounds(o.getPosition().x, o.getPosition().y, o.getSize().x, o.getSize().y);
		sprite.setRotation(o.getAngle());
		sprite.setOrigin(o.getSize().x / 2f, o.getSize().y / 2f);
		sprite.draw(batch);
	}
}
