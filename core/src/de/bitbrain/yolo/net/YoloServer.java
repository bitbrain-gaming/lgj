package de.bitbrain.yolo.net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import de.bitbrain.yolo.YoloGame;
import de.bitbrain.yolo.core.GameObject;
import de.bitbrain.yolo.core.GameState;
import de.bitbrain.yolo.core.GameStateCallback;
import de.bitbrain.yolo.screens.MenuScreen;

import java.io.IOException;

/**
 * @author ksidpen
 */

public class YoloServer extends Listener implements Disposable, GameStateCallback {

    private final YoloGame game;
    private final Server server;
    private final GameState gameState;


    public YoloServer(GameState gameState,  YoloGame game) throws IOException {
        this.gameState = gameState;
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
            GameObject target = gameState.getGameObject(response.entity.getId());
            if(target!=null)target.replace(response.entity);
        } else if(object instanceof Events.Spawn) {
            Events.Spawn response = (Events.Spawn)object;
            gameState.addGameObject(response.entity);
        }else if(object instanceof Events.Join){
            Events.Join response = (Events.Join)object;
            gameState.addGameObject(response.newPlayer);
        }
    }

    @Override
    public void connected(Connection connection) {
        server.sendToAllTCP(new Events.Join(gameState.getPlayer().getShip()));
    }

    @Override
    public void disconnected(Connection connection) {
        dispose();
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game.setScreen(new MenuScreen(game));
            }
        });
    }

    @Override
    public void dispose() {
            server.close();
            server.stop();
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
