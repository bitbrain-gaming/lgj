package de.bitbrain.yolo.screens;


import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import de.bitbrain.yolo.YoloGame;
import de.bitbrain.yolo.core.GameHandler;
import de.bitbrain.yolo.core.GameState;

public class IngameScreen extends AbstractScreen {
	
	private GameState gameState;
	
	private GameHandler gameHandler;

	IngameScreen(YoloGame game) {
		super(game);
	}

	@Override
	protected void onShow() {
		gameState = new GameState();
		gameHandler = new GameHandler(gameState, camera);
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
		gameHandler.updateAndRender(delta, batch);
	}
}
