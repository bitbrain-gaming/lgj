package de.bitbrain.yolo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import de.bitbrain.yolo.loader.GifLoader;
import de.bitbrain.yolo.ui.SoundGif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author ksidpen
 */
public class FXBattery {

    private static Random rand = new Random();

    private static List<Animation> gifs = new ArrayList<Animation>();
    private static List<Sound> sounds = new ArrayList<Sound>();
    private static List<Music> music = new ArrayList<Music>();
    private static List<Drawable> overlays = new ArrayList<Drawable>();
    private static List<SoundGif> soundGifs = new ArrayList<SoundGif>();

    public static void init(){


        for (FileHandle entry: Gdx.files.internal(Assets.DIR_GIFS).list()) {
            gifs.add(GifLoader.loadGIFAnimation(Animation.PlayMode.LOOP, entry.read()));
        }

        for (FileHandle entry: Gdx.files.internal(Assets.DIR_SOUNDS).list()) {
            sounds.add(Gdx.audio.newSound(entry));
        }

    }

    public static Animation getGif(){
        return gifs.get(rand.nextInt(gifs.size()));
    }

//    public static SoundGif getAnimation(){
//        return null;
//    }

//    public static Music getMusic(){
//        return null;
//    }
//
    public static Sound getSound(){
        return sounds.get(rand.nextInt(sounds.size()));
    }
//
//    public static Drawable getOverlay(){
//        return null;
//    }
}
