package de.bitbrain.yolo.core;

/**
 * @author ksidpen
 */
public interface GameStateCallback {

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
