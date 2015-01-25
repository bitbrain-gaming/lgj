package de.bitbrain.yolo.screens;

import java.io.IOException;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.YoloGame;
import de.bitbrain.yolo.core.GameHandler;
import de.bitbrain.yolo.core.GameObject;
import de.bitbrain.yolo.core.GameObjectType;
import de.bitbrain.yolo.core.GameState;
import de.bitbrain.yolo.core.GameState.GameStateListener;
import de.bitbrain.yolo.core.GameStateCallback;
import de.bitbrain.yolo.graphics.ParallaxMap;
import de.bitbrain.yolo.graphics.ParticleRenderer;
import de.bitbrain.yolo.graphics.shader.ShadeArea;
import de.bitbrain.yolo.graphics.shader.SimpleShaderManager;
import de.bitbrain.yolo.net.YoloServer;
import de.bitbrain.yolo.ui.PlayerWidget;

public class IngameScreen extends AbstractScreen implements ShadeArea {

	private final GameState gameState;

	private GameHandler gameHandler;

	private ParallaxMap backgroundMap, fogMap1, fogMap2;

	private final GameStateCallback gameStateCallback;

	private ParticleRenderer particleRenderer;
	
	private SimpleShaderManager shaderManager;



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
		shaderManager = new SimpleShaderManager();
		particleRenderer = new ParticleRenderer();

		gameState.setListener(new GameStateListener() {
			@Override
			public void onAddGameObject(GameObject object) {
				if (object.getType().equals(GameObjectType.PLAYER)) {
					particleRenderer.applyParticleEffect(object,
							Assets.PRT_BLUE_FLAME, object.getSize().x / 2,
							object.getSize().y / 2);
				} else if (object.getType().equals(GameObjectType.PROJECTILE)) {
					Sound s = SharedAssetManager.get(Assets.SND_SHOT, Sound.class);
					s.play(0.1f, (float) (Math.random() * 0.5f + 1.2f), 1.0f);
				}
			}

			@Override
			public void onShipDestroyed(GameObject object) {
				particleRenderer.applyParticleEffect(Assets.PRT_EXPLOSION, 
						object.getPosition().x + object.getSize().x / 2f,
						object.getPosition().y + object.getSize().y / 2f);
				Sound explode = SharedAssetManager.get(Assets.SND_EXPLODE, Sound.class);
				explode.play(1.0f - (float)Math.random() * 0.2f, 0.7f + (float)Math.random() * 0.5f, 1.0f);
			}
		});
		gameHandler = new GameHandler(gameState, camera, gameStateCallback, tweenManager);
		backgroundMap = new ParallaxMap(Assets.TEX_SPACE, camera, 100f);
		backgroundMap.setColor(new Color(0.4f, 0.1f, 0.1f, 0.5f));
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

				return super.keyDown(event, keycode);
			}
		});
		stage.addActor(new PlayerWidget(gameState.getPlayer()));
		//shaderManager.add(this, new RainbowShader());
	}

	@Override
	protected void onResize(int width, int height) {
		if (!init) {
			gameHandler.respawn(gameState.getPlayer());
			init = false;
		}
		shaderManager.resize(width, height);
	}

	@Override
	protected void onDraw(Batch batch, float delta) {		
		batch.end();
		shaderManager.updateAndRender(batch, delta);
		batch.begin();
		draw(batch, delta);
	}

	@Override
	public void dispose() {
		gameStateCallback.dispose();
	}

	@Override
	public void draw(Batch batch, float delta) {
		backgroundMap.draw(batch);
		fogMap2.draw(batch);
		fogMap1.draw(batch);
		particleRenderer.updateAndRender(delta, batch);
		gameHandler.updateAndRender(delta, batch);
	}
}
