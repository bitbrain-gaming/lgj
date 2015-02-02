package de.bitbrain.yolo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;

import de.bitbrain.yolo.loader.GifLoader;
import de.bitbrain.yolo.ui.SoundGif;

/**
 * @author ksidpen
 */
public class FXBattery {

	private static Random rand = new Random();

	private static List<SoundGif> soundGifs = new ArrayList<SoundGif>();
	private static List<Animation> gifs = new ArrayList<Animation>();
	private static List<Sound> sounds = new ArrayList<Sound>();
	private static List<Music> musicz = new ArrayList<Music>();

	public static void init() {

		String[] gifFiles = { "humping", "kid", "burger", "dance", "dew", "dance", "frog", "snoop", "weed", "snipars", "timeofdeath", "explosion", "dew_bottle","thumbs" };

		for (String gif : gifFiles) {
			FileHandle entry = Gdx.files.internal(Assets.DIR_GIFS + gif
					+ ".gif");
			gifs.add(GifLoader.loadGIFAnimation(Animation.PlayMode.LOOP,
					entry.read()));
		}

		String[] soundFiles = { "AIRPORN", "noscoped", "SPOOKY", "omg", "watchasay", "weed", "skrillex", "wow", "triple" };

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

		String[] soundGifFiles = { "pewds", "pussy", "wally" };

		for (String gif : soundGifFiles) {

			SoundGif soundGif = new SoundGif(
					GifLoader.loadGIFAnimation(Animation.PlayMode.NORMAL,
							Gdx.files.internal(Assets.DIR_ANIMS + gif
									+ ".gif").read()),
					Gdx.audio.newSound(Gdx.files.internal(Assets.DIR_ANIMS + gif
							+ ".mp3"))
			);

			soundGifs.add(soundGif);
		}

		String[] musicFiles = { "2SED4AIRHORN", "dankstorm",  "guile" };

		for (String music : musicFiles) {
			FileHandle entry = Gdx.files.internal(Assets.DIR_MUSIC + music
					+ ".mp3");
			musicz.add(Gdx.audio.newMusic(entry));
		}

	}

	public static Animation getGif() {
		return gifs.get(rand.nextInt(gifs.size()));
	}

	public static Sound getSound() {
		return sounds.get(rand.nextInt(sounds.size()));
	}

	public static Music getMusic() {
		return musicz.get(rand.nextInt(musicz.size()));
	}

	public static SoundGif getSoundGif() {
		return soundGifs.get(rand.nextInt(soundGifs.size()));
	}
}
