package de.bitbrain.yolo.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import de.bitbrain.yolo.SharedAssetManager;

public class GraphicsFactory {

	public static NinePatchDrawable getNinePatchDrawable(String name, int border) {
		return getNinePatchDrawable(name, border, Color.WHITE);
	}

	public static NinePatchDrawable getNinePatchDrawable(String name, int border,
			Color color) {
		Texture texture = SharedAssetManager.get(name, Texture.class);
		NinePatch ninePatch = new NinePatch(texture, border, border, border, border);
		ninePatch.setColor(color);
		return new NinePatchDrawable(ninePatch);
	}

	public static Drawable getDrawable(String path){
		return new SpriteDrawable(new Sprite( SharedAssetManager.get(path, Texture.class)));
	}
}
