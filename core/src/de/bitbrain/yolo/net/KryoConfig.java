package de.bitbrain.yolo.net;

import com.esotericsoftware.kryo.Kryo;
import de.bitbrain.yolo.core.GameState;

/**
* @author ksidpen
*/
public class KryoConfig {

    public static final int UDPPort = 54555;
    public static final int TCPPort = 54777;

    public static final int timeout = 5000;

    public static void config(Kryo kryo){
        kryo.register(Events.Spawn.class);
        kryo.register(Events.Move.class);
        kryo.register(Events.Collision.class);
        kryo.register(Events.Join.class);
        kryo.register(Events.Outcome.class);

        kryo.register(GameState.class);
    }


}
