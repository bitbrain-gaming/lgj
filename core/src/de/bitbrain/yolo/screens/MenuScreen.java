package de.bitbrain.yolo.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.YoloGame;
import de.bitbrain.yolo.net.YoloServer;
import de.bitbrain.yolo.tweens.ActorTween;
import de.bitbrain.yolo.ui.UIFactory;

import java.io.IOException;

public class MenuScreen extends AbstractScreen {

	public MenuScreen(YoloGame game) {
		super(game);
	}

	@Override
	protected void onDraw(Batch batch, float delta) {
		
	}

	@Override
	protected void onShow() {
		Table layout = new Table();
		layout.setFillParent(true);
		Texture logoTexture = SharedAssetManager.get(Assets.TEX_LOGO, Texture.class);
		Drawable logoDrawable = new SpriteDrawable(new Sprite(logoTexture));
		Image image = new Image(logoDrawable);
		layout.add(image).padBottom(30f).row();
		
		Tween.to(image, ActorTween.ALPHA, 1f)
		     .target(0.6f)
			 .repeatYoyo(Tween.INFINITY, 0f)
			 .ease(TweenEquations.easeInCubic)
			 .start(tweenManager);
		
		layout.add(UIFactory.generatePrimaryButton("Host game", new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					game.setScreen(new IngameScreen(game));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		})).pad(20f).row();
		layout.add(UIFactory.generatePrimaryButton("Join game", new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new LoadingScreen(game));
			}
		})).row();
		
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = SharedAssetManager.get(Assets.FNT_REGULAR, BitmapFont.class);
		Label label = new Label("Created by Dennis Pawlik and Miguel Gonzalez. YOLO!", labelStyle);
		label.setFontScale(0.5f);
		label.setColor(1f, 1f, 1f, 0.3f);
		layout.add(label).padTop(70f);
		stage.addActor(layout);
	}

	@Override
	protected void onResize(int width, int height) {
		
	}

}
