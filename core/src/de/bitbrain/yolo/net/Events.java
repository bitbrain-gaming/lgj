package de.bitbrain.yolo.net;

import de.bitbrain.yolo.core.GameObject;

/**
 * @author ksidpen
 */
public class Events {

    public static final class Move{
        public final GameObject entity;

        public Move(GameObject entity) {
            this.entity = entity;
        }
    }

    public static final class Collision{

        public final GameObject entity1;
        public final GameObject entity2;

        public Collision(GameObject entity1, GameObject entity2) {
            this.entity1 = entity1;
            this.entity2 = entity2;
        }
    }
    public static final class Player{
        public  enum TYPE {
            JOIN, LEAVE
        }

        public final TYPE state;

        public Player(TYPE state) {
            this.state = state;
        }
    }

    public static final class Outcome{
        public enum TYPE {
            WIN, LOOSE, DRAW
        }

        public final TYPE state;

        public Outcome(TYPE state) {
            this.state = state;
        }
    }

}
