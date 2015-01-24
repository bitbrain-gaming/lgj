package de.bitbrain.yolo.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.YoloGame;
import de.bitbrain.yolo.tweens.ActorTween;

public class LoadingScreen extends AbstractScreen {
	
	private Label infoLabel;

	LoadingScreen(YoloGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onShow() {
		Table layout = new Table();
		layout.setFillParent(true);
		LabelStyle style = new LabelStyle();
		style.font = SharedAssetManager.get(Assets.FNT_REGULAR, BitmapFont.class);
		infoLabel = new Label("Searching for open games..", style);
		Tween.to(infoLabel, ActorTween.ALPHA, 0.7f)
			 .target(0.2f)
			 .ease(TweenEquations.easeInCubic)
			 .repeatYoyo(Tween.INFINITY, 0f)
			 .start(tweenManager);
		layout.add(infoLabel);
		stage.addActor(layout);
		
		stage.addCaptureListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.ESCAPE) {
					game.setScreen(new MenuScreen(game));
					return true;
				}
				return super.keyDown(event, keycode);
			}
		});
	}

	@Override
	protected void onResize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDraw(Batch batch) {
		// TODO Auto-generated method stub
		
	}

}
