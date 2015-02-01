package de.bitbrain.yolo.graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.bitbrain.yolo.FXBattery;
import de.bitbrain.yolo.tweens.AnimationTween;
import de.bitbrain.yolo.ui.TweenableAnimation;

/**
 * @author ksidpen
 */
public class AnimationRenderer {

    private TweenManager manager;

    private List<TweenableAnimation> anims = new ArrayList<TweenableAnimation>();

    private Random random = new Random();

    private Camera camera;

    public AnimationRenderer(TweenManager manager,Camera camera) {
        this.manager = manager;
        this.camera = camera;
    }

    public void addRandomAnimation(){
        int xMax = (int) camera.position.x;
        int yMax = (int) camera.position.y;

        TweenableAnimation nextAnim =
                new TweenableAnimation(FXBattery.getGif(), xMax - random.nextInt(250), yMax - random.nextInt(250), 300, 300, 5 + random.nextInt(10));

        Tween.to(nextAnim,
                AnimationTween.X, 2f)
                .target(xMax + random.nextInt(350))
                .ease(TweenEquations.easeInCubic)
                .repeatYoyo(Tween.INFINITY, 0f)
                .start(manager);
        Tween.to(nextAnim,
                AnimationTween.Y, 2f)
                .target(yMax + random.nextInt(350))
        .ease(TweenEquations.easeInCubic)
                .repeatYoyo(Tween.INFINITY, 0f)
                .start(manager);

        anims.add(nextAnim);
    }

    public void updateAndRender(Batch batch) {

        for (Iterator<TweenableAnimation> iter = anims.listIterator(); iter.hasNext(); ) {
            TweenableAnimation next = iter.next();
            if (next.getTicks() > next.getMaxTicks()) {
                iter.remove();
            }else{
                next.render(batch);
            }
        }
    }
}

