package de.bitbrain.yolo.net;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import de.bitbrain.yolo.core.GameObject;
import de.bitbrain.yolo.core.GameState;
import de.bitbrain.yolo.core.GameStateCallback;

import java.io.IOException;

/**
 * @author ksidpen
 */

public class YoloServer extends Listener implements Disposable, GameStateCallback {

    private final Server server;
    private final GameState game;


    public YoloServer(GameState game) throws IOException {
        this.game = game;

        server = new Server();
        KryoConfig.config(server.getKryo());
        server.addListener(this);
        server.start();
        server.bind(KryoConfig.TCPPort, KryoConfig.UDPPort);
    }


    @Override
    public void received(Connection connection, Object object){
        if (object instanceof Events.Move) {
            //update entity
            Events.Move response = (Events.Move)object;
            GameObject target = game.getGameObject(response.entity.getId());
            if(target!=null)target.replace(response.entity);

        } else if(object instanceof Events.Spawn) {
            Events.Spawn response = (Events.Spawn)object;
            game.addGameObject(response.entity);
        }
    }

    @Override
    public void dispose() {
        try {
            server.stop();
            server.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onMove(GameObject object) {
        server.sendToAllUDP(new Events.Move(object));
    }

    @Override
    public void onCreate(GameObject object) {
        server.sendToAllUDP(new Events.Spawn(object));
    }
}
