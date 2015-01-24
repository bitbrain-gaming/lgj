package de.bitbrain.yolo.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
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
import de.bitbrain.yolo.core.GameState;
import de.bitbrain.yolo.core.GameStateCallback;
import de.bitbrain.yolo.net.YoloClient;
import de.bitbrain.yolo.tweens.ActorTween;

import java.io.IOException;

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

		new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					final GameState gameState = new GameState();
					final YoloClient client = new YoloClient(gameState, game);
					client.connect();

					Gdx.app.postRunnable(new Runnable() {
						public void run() {
								game.setScreen(new IngameScreen(game, gameState, client));
						}});
				} catch (IOException e) {
					Gdx.app.postRunnable(new Runnable() {
						public void run() {
							game.setScreen(new MenuScreen(game));
						}});
				} catch (IllegalArgumentException ex){
					Gdx.app.postRunnable(new Runnable() {
						public void run() {
							game.setScreen(new MenuScreen(game));
						}});
				}
			}
		})
		.start();
	}


	@Override
	protected void onResize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDraw(Batch batch, float delta) {
		// TODO Auto-generated method stub
		
	}

}
