package de.bitbrain.yolo.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

/**
 * @author ksidpen
 */
public class TweenableAnimation extends Widget {

    private float frames;
    private Animation core;

    public TweenableAnimation(Animation core) {
        this.core = core;
    }


    @Override
    public void act (float delta) {
        super.act(delta);
        this.frames += delta;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(core.getKeyFrame(frames, true),getX(), getY(), getWidth(), getHeight());
    }
}
