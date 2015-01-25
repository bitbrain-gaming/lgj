package de.bitbrain.yolo.graphics;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Camera;

import de.bitbrain.yolo.tweens.CameraTween;

public final class CameraShaker {

	public static void shake(float strength, Camera camera,
			TweenManager tweenManager) {
		tweenManager.killTarget(camera);
		Tween.to(camera, CameraTween.X_POS, 0.05f)
				.target(camera.position.x + strength).repeatYoyo(20, 0)
				.ease(TweenEquations.easeInOutElastic).start(tweenManager);
		Tween.to(camera, CameraTween.Y_POS, 0.05f)
				.target(camera.position.y + strength).repeatYoyo(20, 0)
				.ease(TweenEquations.easeInOutElastic).start(tweenManager);
	}
}
