package de.bitbrain.yolo.core;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

public class GameObject implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private GameObjectType type = GameObjectType.UNDEFINED;

	private float angle;

	private Vector2 position = new Vector2(), size = new Vector2();
	
	private Vector2 velocity = new Vector2();

	private Vector2 acceleration = new Vector2();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

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

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(float x, float y) {
		this.velocity.x = x;
		this.velocity.y = y;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float aX, float aY) {
		this.acceleration.x = aX;
		this.acceleration.y = aY;
	}
	
	public GameObjectType getType() {
		return type;
	}
	
	public void setType(GameObjectType type) {
		this.type = type;
	}
}
