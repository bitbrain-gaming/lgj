package de.bitbrain.yolo;

import com.badlogic.gdx.Game;

import de.bitbrain.yolo.screens.MenuScreen;

public class YoloGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}
