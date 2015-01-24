package de.bitbrain.yolo.net;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import de.bitbrain.yolo.core.GameObject;
import de.bitbrain.yolo.core.GameObjectType;
import de.bitbrain.yolo.core.GameState;
import de.bitbrain.yolo.core.Player;

/**
* @author ksidpen
*/
public class KryoConfig {

    public static final int UDPPort = 6474;
    public static final int TCPPort = 6476;

    public static final int timeout = 5000;

    public static void config(Kryo kryo){
        kryo.register(Events.Spawn.class);
        kryo.register(Events.Move.class);
        kryo.register(Events.GameOver.class);

        //kryo.register(Events.Collision.class);
        kryo.register(Events.Join.class);
        kryo.register(GameObject.class);
        kryo.register(GameObjectType.class);
        kryo.register(Vector2.class);
        kryo.register(Player.class);
    }


}
