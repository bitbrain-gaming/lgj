package de.bitbrain.yolo.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author ksidpen
 */
public class TweenableAnimation {

    private Animation core;

    private float ticks =0;

    private float maxTicks;

    private int x,y, width, height;

    public TweenableAnimation(Animation core ,int x, int y, int width, int height, int maxTicks) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.core =core;
        this.maxTicks = maxTicks;
    }

    public void render(Batch batch){
        ticks += Gdx.graphics.getDeltaTime();
        batch.draw(core.getKeyFrame(ticks, true),x, y, width, height);
    }

    public float getMaxTicks() {
        return maxTicks;
    }

    public float getTicks() {
        return ticks;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
