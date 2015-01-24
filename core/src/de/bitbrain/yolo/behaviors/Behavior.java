package de.bitbrain.yolo.behaviors;

import de.bitbrain.yolo.core.GameObject;

public interface Behavior {

	void update(GameObject target, float delta);
}
