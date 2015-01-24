package de.bitbrain.yolo.behaviors;

import de.bitbrain.yolo.core.GameObject;

/**
 * @author ksidpen
 */
public abstract class BehaviourWrapper implements Behavior{

    private final Behavior core;

    public BehaviourWrapper(Behavior core) {
        this.core = core;
    }

    public abstract void post(GameObject target);

    @Override
    public void update(GameObject target, float delta) {
        core.update(target,delta);
        post(target);
    }
}
