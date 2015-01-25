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

    public static class Join {
        public GameObject newPlayer;

        public Join(){};
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

    public static class GameOver{

        public GameObject entity;

        public GameOver(){};
        public GameOver(GameObject entity){
            this.entity = entity;
        };
    }

}
