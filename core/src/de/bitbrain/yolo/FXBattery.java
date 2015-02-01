package de.bitbrain.yolo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;

import de.bitbrain.yolo.loader.GifLoader;

/**
 * @author ksidpen
 */
public class FXBattery {

	private static Random rand = new Random();

	private static List<Animation> gifs = new ArrayList<Animation>();
	private static List<Sound> sounds = new ArrayList<Sound>();

	public static void init() {

		String[] gifFiles = { "burger", "dance", "dew", "dance", "frog", "snoop", "weed" };

		for (String gif : gifFiles) {
			FileHandle entry = Gdx.files.internal(Assets.DIR_GIFS + gif
					+ ".gif");
			gifs.add(GifLoader.loadGIFAnimation(Animation.PlayMode.LOOP,
					entry.read()));
		}

		String[] soundFiles = { "omg", "watchasay", "weed", "skrillex", "wow", "triple" };

		for (String sound : soundFiles) {
			FileHandle entry = Gdx.files.internal(Assets.DIR_SOUNDS + sound
					+ ".mp3");
			sounds.add(Gdx.audio.newSound(entry));
		}

		if (gifs.isEmpty()) {
			System.out.println("WARNING! NO GIFS WERE FOUND!");
		}

		if (sounds.isEmpty()) {
			System.out.println("WARNING! NO SOUNDS WERE FOUND!");
		}

	}

	public static Animation getGif() {
		return gifs.get(rand.nextInt(gifs.size()));
	}

	public static Sound getSound() {
		return sounds.get(rand.nextInt(sounds.size()));
	}
}
