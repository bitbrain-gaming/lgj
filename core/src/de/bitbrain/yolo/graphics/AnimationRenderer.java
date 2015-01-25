package de.bitbrain.yolo.graphics;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import de.bitbrain.yolo.ui.TweenableAnimation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ksidpen
 */
public class AnimationRenderer {

    private TweenManager manager;

    private List<TweenableAnimation> anims = new ArrayList<TweenableAnimation>();

    private Random random = new Random();

    public AnimationRenderer(TweenManager manager) {
        this.manager = manager;
    }

    public void playAnimation(){
        int xMax = Gdx.graphics.getWidth();
        int yMax = Gdx.graphics.getWidth();

    }

    public void updateAndRender(Batch batch) {


    }
}

