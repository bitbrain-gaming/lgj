package de.bitbrain.yolo.graphics.shader;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface ShaderManager {

	void add(ShadeArea shaderTarget, Shader<?>... shaders);

	void clear();

	int size();

	boolean isEmpty();

	void dispose();

	void resize(int width, int height);

	void updateAndRender(Batch batch, float delta);

}