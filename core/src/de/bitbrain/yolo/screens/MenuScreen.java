package de.bitbrain.yolo.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.YoloGame;

public class MenuScreen extends AbstractScreen {

	public MenuScreen(YoloGame game) {
		super(game);
	}

	@Override
	protected void onDraw(Batch batch) {
		
	}

	@Override
	protected void onShow() {
		Table layout = new Table();
		layout.setFillParent(true);
		Texture logoTexture = SharedAssetManager.get(Assets.TEX_LOGO, Texture.class);
		Drawable logoDrawable = new SpriteDrawable(new Sprite(logoTexture));
		Image image = new Image(logoDrawable);
		layout.add(image).row();
		stage.addActor(layout);
	}

	@Override
	protected void onResize(int width, int height) {
		
	}

}
