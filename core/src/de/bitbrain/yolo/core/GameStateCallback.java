package de.bitbrain.yolo.core;

/**
 * @author ksidpen
 */
public interface GameStateCallback {
    void onMove(GameObject object);
    void onCreate(GameObject object);
}
