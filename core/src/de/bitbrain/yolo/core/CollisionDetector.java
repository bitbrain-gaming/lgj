package de.bitbrain.yolo.core;


public class CollisionDetector {
	
	private Iterable<GameObject> objects;
	
	public CollisionDetector(Iterable<GameObject> objects) {
		this.objects = objects;
	}

	public GameObject getCollision(GameObject object) {
		for (GameObject other : objects) {
			if (!other.equals(object) && checkCollision(other, object)) {
				return other;
			}
		}
		return null;
	}
	
	private boolean checkCollision(GameObject o1, GameObject o2) {
		boolean xCollision = Math.abs(o1.getPosition().x - o2.getPosition().x) * 2 < (o1.getSize().x + o2.getSize().x);
		boolean yCollision = Math.abs(o1.getPosition().y - o2.getPosition().y) * 2 < (o1.getSize().y + o2.getSize().y);
		return xCollision && yCollision;
	}
}
