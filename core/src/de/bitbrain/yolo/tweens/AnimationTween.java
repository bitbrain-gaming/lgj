package de.bitbrain.yolo.tweens;

import aurelienribon.tweenengine.TweenAccessor;
import de.bitbrain.yolo.ui.TweenableAnimation;

/**
 * @author ksidpen
 */
public class AnimationTween implements TweenAccessor<TweenableAnimation> {

    public static final int X = 1;
    public static final int Y = 2;
    public static final int WIDTH = 3;
    public static final int HEIGHT = 4;
    public static final int TICKS = 5;

    @Override
    public int getValues(TweenableAnimation target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case X:
                returnValues[0] = target.getX();
                return 1;
            case Y:
                returnValues[0] = target.getY();
                return 1;
            case WIDTH:
                returnValues[0] = target.getWidth();
                return 1;
            case HEIGHT:
                returnValues[0] = target.getHeight();
                return 1;
            case TICKS:
                returnValues[0] = target.getTicks();
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public void setValues(TweenableAnimation target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case X:
                target.setX((int) newValues[0]);
                break;
            case Y:
                target.setY((int) newValues[0]);
                break;
            case WIDTH:
                target.setWidth((int) newValues[0]);
                break;
            case HEIGHT:
                target.setHeight((int) newValues[0]);
                break;
        }
    }
}
