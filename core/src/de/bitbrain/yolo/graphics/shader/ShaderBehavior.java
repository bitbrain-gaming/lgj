package de.bitbrain.yolo.graphics.shader;

public interface ShaderBehavior<Type extends Shader<Type> > {
	
	void update(float delta, Type shader);

}