package de.bitbrain.yolo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.bitbrain.yolo.YoloGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "YOLO! Wars";
		config.useHDPI = true;
		config.width = 1100;
		config.height = 700;
		new LwjglApplication(new YoloGame(), config);
	}
}
