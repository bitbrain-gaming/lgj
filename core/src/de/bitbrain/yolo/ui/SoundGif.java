package de.bitbrain.yolo.ui;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * @author ksidpen
 */
public class SoundGif {

    private Animation anim;
    private Sound sound;

    public SoundGif(Animation anim, Sound sound) {
        this.anim = anim;
        this.sound = sound;
    }

    public Animation getAnim() {
        return anim;
    }

    public Sound getSound() {
        return sound;
    }

}
