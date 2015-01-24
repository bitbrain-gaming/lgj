package de.bitbrain.yolo.screens;


import java.io.IOException;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.YoloGame;
import de.bitbrain.yolo.core.GameHandler;
import de.bitbrain.yolo.core.GameState;
import de.bitbrain.yolo.core.GameStateCallback;
import de.bitbrain.yolo.graphics.ParallaxMap;
import de.bitbrain.yolo.net.YoloServer;
import de.bitbrain.yolo.ui.PlayerWidget;

public class IngameScreen extends AbstractScreen {
	
	private final GameState gameState;
	
	private GameHandler gameHandler;
	
	private ParallaxMap backgroundMap, fogMap1, fogMap2;

	private final GameStateCallback gameStateCallback;
	
	private boolean init = false;

	IngameScreen(YoloGame game) throws IOException {
		super(game);
		gameState = new GameState();
		gameStateCallback = new YoloServer(gameState, game);
	}

	IngameScreen(YoloGame game, GameState state, GameStateCallback callback) {
		super(game);
		gameState = state;
		gameStateCallback = callback;
	}

	@Override
	protected void onShow() {

		gameHandler = new GameHandler(gameState, camera, gameStateCallback);
		backgroundMap = new ParallaxMap(Assets.TEX_SPACE, camera, 100f);
		backgroundMap.setColor(new Color(0.2f, 0.3f, 0.4f, 1.0f));
		backgroundMap.scale(1.2f);
		fogMap1 = new ParallaxMap(Assets.TEX_FOG, camera, 0.4f);
		fogMap1.scale(5.2f);
		fogMap2 = new ParallaxMap(Assets.TEX_FOG, camera, 2f);
		fogMap2.scale(1.2f);

		stage.addCaptureListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.ESCAPE) {
					gameStateCallback.dispose();
					game.setScreen(new MenuScreen(game));
					return true;
				}
				if (keycode == Keys.F1) {
					boolean destroyed = gameState.getPlayer().damage(1);
					if(destroyed)gameStateCallback.onGameOver();

					return true;
				}
				return super.keyDown(event, keycode);
			}
		});
		
		stage.addActor(new PlayerWidget(gameState.getPlayer()));
	}

	@Override
	protected void onResize(int width, int height) {
		if (!init) {
			gameHandler.respawn(gameState.getPlayer());
			init = false;
		}
	}

	@Override
	protected void onDraw(Batch batch, float delta) {
		backgroundMap.draw(batch);
		fogMap2.draw(batch);
		fogMap1.draw(batch);
		gameHandler.updateAndRender(delta, batch);
	}

	@Override
	public void dispose() {
		gameStateCallback.dispose();
	}
}
