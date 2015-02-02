package de.bitbrain.yolo.graphics;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.bitbrain.yolo.FXBattery;
import de.bitbrain.yolo.tweens.ActorTween;
import de.bitbrain.yolo.ui.SoundGif;
import de.bitbrain.yolo.ui.TweenableAnimation;

import java.util.Random;

/**
 * @author ksidpen
 */
public class AnimationRenderer {

    private  Random random = new Random();

    private Stage stage;

    private TweenManager tweenManager;


    public AnimationRenderer(TweenManager manager, Stage stage) {
        this.tweenManager = manager;
        this.stage = stage;
    }

    public void playRandomAnimation(){
        final TweenableAnimation animation = new TweenableAnimation(FXBattery.getGif());

        stage.addActor(animation);
        animation.setZIndex(100);


        float size =150 + random.nextInt((int) stage.getWidth());

        animation.setSize(size,size);
        animation.setX(randomWidth());
        animation.setY(randomHeight());

        Tween.to(animation, ActorTween.SIZE, 2f).target(size * 3, size * 3)
                .ease(TweenEquations.easeOutSine).start(tweenManager);

        Tween.to(animation, ActorTween.XY, 1f).target(
                randomWidth(),
                randomHeight()
        )
                .ease(TweenEquations.easeOutSine).start(tweenManager);

        Tween.to(animation, ActorTween.ALPHA, 2f)
                .target(0f)
                .ease(TweenEquations.easeOutCubic)
                .setCallbackTriggers(TweenCallback.COMPLETE | TweenCallback.ANY)
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        if (type == TweenCallback.COMPLETE) {
                            stage.getActors().removeValue(animation, true);
                        }
                    }
                }).start(tweenManager);
    }

    public void playRandomSoundAnimation(){
        final SoundGif soundGif = FXBattery.getSoundGif();
        final TweenableAnimation animation = new TweenableAnimation(soundGif.getAnim());

        stage.addActor(animation);
        animation.setZIndex(100);

        float size =250 + random.nextInt((int) stage.getWidth());

        animation.setSize(size,size);
        animation.setX(50);
        animation.setY(0);


        Tween.to(animation, ActorTween.ALPHA, soundGif.getAnim().getAnimationDuration())
                .target(0f)
                .ease(TweenEquations.easeOutCubic)
                .setCallbackTriggers(TweenCallback.COMPLETE | TweenCallback.ANY)
                .setCallback(new TweenCallback() {
                                 @Override
                                 public void onEvent(int type, BaseTween<?> source) {

                                     if (type == TweenCallback.BEGIN) {
                                         soundGif.getSound().play();
                                     }

                                     if (type == TweenCallback.COMPLETE) {
                                         stage.getActors().removeValue(animation, true);
                                     }
                                 }
                             }

                    ).

                start(tweenManager);

                }


    private float randomHeight(){
        return stage.getHeight() - (random.nextInt((int) stage.getHeight())) - 250;
    }

    private float randomWidth(){
        return stage.getWidth() - (random.nextInt((int) stage.getWidth()));
    }


}

