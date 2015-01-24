package de.bitbrain.yolo.screens;


import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.YoloGame;
import de.bitbrain.yolo.core.GameHandler;
import de.bitbrain.yolo.core.GameState;
import de.bitbrain.yolo.graphics.ParallaxMap;

public class IngameScreen extends AbstractScreen {
	
	private GameState gameState;
	
	private GameHandler gameHandler;
	
	private ParallaxMap backgroundMap, fogMap1, fogMap2;

	IngameScreen(YoloGame game) {
		super(game);
	}

	@Override
	protected void onShow() {
		backgroundMap = new ParallaxMap(Assets.TEX_SPACE, camera, 100f);
		backgroundMap.setColor(new Color(0.2f, 0.3f, 0.4f, 1.0f));
		fogMap1 = new ParallaxMap(Assets.TEX_FOG, camera, 0.4f);
		fogMap1.scale(5.2f);
		fogMap2 = new ParallaxMap(Assets.TEX_FOG, camera, 2f);
		fogMap2.scale(1.2f);
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
		backgroundMap.draw(batch);
		fogMap2.draw(batch);
		gameHandler.updateAndRender(delta, batch);
		fogMap1.draw(batch);
	}
}
