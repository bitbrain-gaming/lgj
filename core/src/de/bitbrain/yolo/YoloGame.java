package de.bitbrain.yolo;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.bitbrain.yolo.screens.MenuScreen;
import de.bitbrain.yolo.tweens.ActorTween;
import de.bitbrain.yolo.tweens.SpriteTween;
import de.bitbrain.yolo.util.AssetReflector;

public class YoloGame extends Game {

	@Override
	public void create() {
		FXBattery.init();
		load();
		setScreen(new MenuScreen(this));
	}
	
	private void load() {
		AssetReflector reflector = new AssetReflector(SharedAssetManager.getInstance());
		reflector.load();
		Tween.registerAccessor(Actor.class, new ActorTween());
		Tween.registerAccessor(Sprite.class, new SpriteTween());
	}
}
