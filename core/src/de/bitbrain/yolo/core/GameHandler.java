package de.bitbrain.yolo.core;

import com.badlogic.gdx.graphics.g2d.Batch;

public class GameHandler {
	
	private GameState state;
	
	private Renderer renderer;
	
	public GameHandler(GameState state) {
		this.state = state;
		this.renderer = new Renderer();
	}

	public void updateAndRender(float delta, Batch batch) {
		for (GameObject object : state) {
			renderer.render(object, batch);
		}
	}
}
