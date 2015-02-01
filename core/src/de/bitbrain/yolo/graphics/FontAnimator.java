package de.bitbrain.yolo.graphics;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.tweens.ActorTween;

public class FontAnimator {

	private Stage stage;

	private TweenManager tweenManager;

	public FontAnimator(TweenManager tweenManager, Stage stage) {
		this.stage = stage;
		this.tweenManager = tweenManager;
	}

	public void animate(String text) {
		LabelStyle style = new LabelStyle();
		style.font = SharedAssetManager.get(Assets.FNT_REGULAR,
				BitmapFont.class);
		style.fontColor = Color.WHITE;
		final Label label = new Label(text, style) {
			@Override
			public void act(float delta) {
				super.act(delta);
				setX(stage.getWidth() / 2f - getPrefWidth() / 2f);
				setY(stage.getHeight() / 2f - getPrefHeight() / 2f);
			}
		};
		stage.addActor(label);
		label.setZIndex(100);
		label.setX(stage.getWidth() / 2f - label.getPrefWidth() / 2f);
		label.setY(stage.getHeight() / 2f - label.getPrefHeight() / 2f);
		Tween.to(label, ActorTween.SCALE, 2f).target(5f)
				.ease(TweenEquations.easeOutCubic).start(tweenManager);
		Tween.to(label, ActorTween.ALPHA, 2f)
				.target(0f)
				.ease(TweenEquations.easeOutCubic)
				.setCallbackTriggers(TweenCallback.COMPLETE | TweenCallback.ANY)
				.setCallback(new TweenCallback() {
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						if (type == TweenCallback.COMPLETE) {
							stage.getActors().removeValue(label, true);
						}
					}
				}).start(tweenManager);
	}
}
