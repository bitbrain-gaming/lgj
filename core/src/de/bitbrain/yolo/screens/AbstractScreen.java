package de.bitbrain.yolo.screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import de.bitbrain.yolo.YoloGame;

public abstract class AbstractScreen implements Screen {
	
	protected Stage stage;
	
	protected OrthographicCamera camera;
	
	private Batch batch;
	
	protected TweenManager tweenManager;
	
	protected YoloGame game;
	
	AbstractScreen(YoloGame game) {
		this.game = game;
	}

	@Override
	public final void show() {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		stage = new Stage(new FillViewport(800, 600), batch);
		tweenManager = new TweenManager();
		Gdx.input.setInputProcessor(stage);
		onShow();
	}

	@Override
	public final void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tweenManager.update(delta);
		camera.update();
		stage.act(delta);
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
			onDraw(batch, delta);
		batch.end();
		
		stage.draw();
	}

	@Override
	public final void resize(int width, int height) {
		camera.setToOrtho(true, width, height);
		stage.getViewport().update(width, height);
		onResize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	protected abstract void onShow();
	protected abstract void onResize(int width, int height);
	protected abstract void onDraw(Batch batch, float delta);
}
