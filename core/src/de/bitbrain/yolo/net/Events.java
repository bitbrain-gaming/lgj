package de.bitbrain.yolo.net;

import de.bitbrain.yolo.core.GameObject;

/**
 * @author ksidpen
 */
public class Events {

    public static class Spawn{
        public GameObject entity;

        public Spawn(){};
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

    public static class Move{
        public GameObject entity;

        public Move(){};
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

    public static class Collision{

        public GameObject entity1;
        public GameObject entity2;

        public Collision(){};
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
    public static class Join {
        public final GameObject newPlayer;
        public Join(GameObject newPlayer){
            this.newPlayer = newPlayer;
        }

        @Override
        public String toString() {
            return "Join{" +
                    "newPlayer=" + newPlayer +
                    '}';
        }
    }

    public static class Outcome{
        public enum TYPE {
            WIN, LOOSE, DRAW
        }
        public enum PLAYER{
            PLAYER1, PLAYER2
        }

        public TYPE state;
        public Outcome.PLAYER player;

        public Outcome(){};
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
