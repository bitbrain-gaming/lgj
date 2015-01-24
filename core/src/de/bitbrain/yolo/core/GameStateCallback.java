package de.bitbrain.yolo.core;

import com.badlogic.gdx.utils.Disposable;

/**
 * @author ksidpen
 */
public interface GameStateCallback extends Disposable{

    /**
     * called when an object wants to move
     * @param object game object with new velocity, position etc. applied
     */
    void onMove(GameObject object);

    /**
     * called when a projectile is spawned
     * @param object new projectile
     */
    void onCreate(GameObject object);
}
