package de.bitbrain.yolo.behaviors;


import de.bitbrain.yolo.core.GameHandler;
import de.bitbrain.yolo.core.GameObject;
import de.bitbrain.yolo.util.Timer;

public class ProjectileBehavior implements Behavior {
	
	public static final float LIFE = 0.8f;
	
	private GameHandler handler;
	
	private Timer timer;
	
	public ProjectileBehavior(GameHandler handler) {
		this.handler = handler;
		timer = new Timer();
	}

	@Override
	public void update(GameObject target, float delta) {
		timer.update(delta);
		if (timer.reached(LIFE)) {
			handler.removeGameObject(target);
		}
	}

}
