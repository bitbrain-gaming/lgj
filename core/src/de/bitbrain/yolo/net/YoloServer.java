package de.bitbrain.yolo.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import de.bitbrain.yolo.core.GameObject;
import de.bitbrain.yolo.core.GameState;

import java.io.IOException;

/**
 * @author ksidpen
 */
public class YoloServer {

    private final int UDPPort = 42068;
    private final int TCPPort = 42069;

    private Server server;
    private GameState game;


    public YoloServer() throws IOException {
        Server server = new Server();
        server.bind(TCPPort, UDPPort);

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {

            }
        });

        server.start();

        this.game = new GameState();

    }
}
