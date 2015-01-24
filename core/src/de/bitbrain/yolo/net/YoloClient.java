package de.bitbrain.yolo.net;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.bitbrain.yolo.YoloGame;
import de.bitbrain.yolo.core.GameObject;
import de.bitbrain.yolo.core.GameState;
import de.bitbrain.yolo.core.GameStateCallback;
import de.bitbrain.yolo.screens.MenuScreen;

import java.io.IOException;
import java.net.InetAddress;

/**
* @author ksidpen
*/
public class YoloClient extends Listener implements Disposable, GameStateCallback{

    private final YoloGame game;
    private final Client client;
    private final GameState gameState;


    public YoloClient(GameState game, YoloGame mainGame) {
        this.game = mainGame;
        this.gameState = game;
        client = new Client();
        KryoConfig.config(client.getKryo());

        client.start();
    }

    public void connect() throws IOException {
        InetAddress target = client.discoverHost(KryoConfig.UDPPort, KryoConfig.timeout);

        client.connect(KryoConfig.timeout, target, KryoConfig.TCPPort, KryoConfig.UDPPort);
        client.addListener(this);
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
        }else if(object instanceof Events.GameOver){
            gameState.setDidWin();
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game.setScreen(new MenuScreen(game));
            }
        });
        this.dispose();
    }

    @Override
    public void dispose() {
        client.removeListener(this);
        client.close();
    }

    @Override
    public void onReady() {
        client.sendTCP(new Events.Join(gameState.getPlayer().getShip()));
    }

    @Override
    public void onMove(GameObject object) {
        client.sendUDP(new Events.Move(object));
    }

    @Override
    public void onCreate(GameObject object) {
        client.sendUDP(new Events.Spawn(object));
    }

    @Override
    public void onGameOver() {
        client.sendTCP(new Events.GameOver());
    }
}
