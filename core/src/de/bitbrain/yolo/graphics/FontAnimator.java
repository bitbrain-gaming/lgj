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

import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.FXBattery;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.tweens.ActorTween;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FontAnimator {

	private Random random = new Random();

	private Stage stage;

	private TweenManager tweenManager;

	public FontAnimator(TweenManager tweenManager, Stage stage) {
		this.stage = stage;
		this.tweenManager = tweenManager;
	}

	public void animateRandomString(){

		List<String> strings = Arrays.asList(
				"#YOLO",
				"MFW KILL :(((",
				"420 BLAZE IT, FGT",
				"DAT SHOT!!11!!",
				"xxxxxxxDDDDDDDDDDDD",
				"GET #REKT",
				"#SHREKT",
				"#WASTED",
				"GT SHANKD, FGT",
				"#2EDGY4ME",
				"GR8 SHOT, m90",
				"#EUPHORIA",
				"1v1 me, m8",
				"DANK n0SC0pp3",
				"#360n0sc0p3");

		String target = strings.get(random.nextInt(strings.size()));

		LabelStyle style = new LabelStyle();
		style.font = SharedAssetManager.get(Assets.FNT_REGULAR,
				BitmapFont.class);
		style.fontColor = Color.GREEN;
		final Label label = new Label(target, style) {
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
		Tween.to(label, ActorTween.ALPHA, 3f)
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
