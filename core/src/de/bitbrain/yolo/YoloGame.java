package de.bitbrain.yolo;

import com.badlogic.gdx.Game;

import de.bitbrain.yolo.screens.MenuScreen;
import de.bitbrain.yolo.util.AssetReflector;

public class YoloGame extends Game {

	@Override
	public void create() {
		load();
		setScreen(new MenuScreen(this));
	}
	
	private void load() {
		AssetReflector reflector = new AssetReflector(SharedAssetManager.getInstance());
		reflector.load();
	}
}
