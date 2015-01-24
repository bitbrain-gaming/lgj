package de.bitbrain.yolo.core;

import com.badlogic.gdx.math.Vector2;

public class GameObject {

	private float angle;

	private Vector2 position = new Vector2(), size = new Vector2();

	private float velocity, acceleration;

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(float x, float y) {
		this.position.x = x;
		this.position.y = y;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(float width, float height) {
		this.size.x = width;
		this.size.y = height;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

}
