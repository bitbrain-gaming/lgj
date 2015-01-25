package de.bitbrain.yolo.graphics.shader;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public interface Shader<Type extends Shader<Type>> {
	ShaderProgram getProgram();
	void update(float delta);
	void setBehavior(ShaderBehavior<Type> behavior);
}