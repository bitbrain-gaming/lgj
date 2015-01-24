package de.bitbrain.yolo.net;

import de.bitbrain.yolo.core.GameObject;

/**
 * @author ksidpen
 */
public class Events {

    public static final class Spawn{
        public final GameObject entity;

        public Spawn(GameObject entity) {
            this.entity = entity;
        }

        @Override
        public String toString() {
            return "Spawn{" +
                    "entity=" + entity +
                    '}';
        }
    }

    public static final class Move{
        public final GameObject entity;

        public Move(GameObject entity) {
            this.entity = entity;
        }

        @Override
        public String toString() {
            return "Move{" +
                    "entity=" + entity +
                    '}';
        }
    }

    public static final class Collision{

        public final GameObject entity1;
        public final GameObject entity2;

        public Collision(GameObject entity1, GameObject entity2) {
            this.entity1 = entity1;
            this.entity2 = entity2;
        }

        @Override
        public String toString() {
            return "Collision{" +
                    "entity1=" + entity1 +
                    ", entity2=" + entity2 +
                    '}';
        }
    }
    public static final class Join {

    }

    public static final class Outcome{
        public enum TYPE {
            WIN, LOOSE, DRAW
        }
        public enum PLAYER{
            PLAYER1, PLAYER2
        }

        public final TYPE state;
        public final Outcome.PLAYER player;

        public Outcome(TYPE state, PLAYER player) {
            this.state = state;
            this.player = player;
        }

        @Override
        public String toString() {
            return "Outcome{" +
                    "state=" + state +
                    ", player=" + player +
                    '}';
        }
    }

}
