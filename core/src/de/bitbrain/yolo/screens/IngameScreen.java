package de.bitbrain.yolo.screens;


import com.badlogic.gdx.Input.Keys;
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

import java.io.IOException;

public class IngameScreen extends AbstractScreen {
	
	private final GameState gameState;
	
	private GameHandler gameHandler;
	
	private ParallaxMap backgroundMap;

	private final GameStateCallback gameStateCallback;

	IngameScreen(YoloGame game) throws IOException {
		super(game);
		gameState = new GameState();
		gameStateCallback = new YoloServer(gameState);
	}

	IngameScreen(YoloGame game, GameState state, GameStateCallback callback) {
		super(game);
		gameState = state;
		gameStateCallback = callback;
	}

	@Override
	protected void onShow() {
		backgroundMap = new ParallaxMap(Assets.TEX_SPACE, camera);

		gameHandler = new GameHandler(gameState, camera, gameStateCallback);
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
	protected void onDraw(Batch batch, float delta) {
		backgroundMap.draw(batch);
		gameHandler.updateAndRender(delta, batch);
	}
}
