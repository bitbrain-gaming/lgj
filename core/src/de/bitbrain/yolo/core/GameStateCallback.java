package de.bitbrain.yolo.core;

/**
 * @author ksidpen
 */
public interface GameStateCallback {

    /**
     * called when an object wants to move
     * @param object
     */
    void onMove(GameObject object);

    /**
     * called when a projectile is spawned
     * @param object
     */
    void onCreate(GameObject object);
}
