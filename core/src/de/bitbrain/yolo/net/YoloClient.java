package de.bitbrain.yolo.net;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.bitbrain.yolo.core.GameState;

import java.io.IOException;
import java.net.InetAddress;

/**
* @author ksidpen
*/
public class YoloClient extends Listener implements Disposable {

    private final Client client;
    private final GameState game;


    public YoloClient() {
        this.game = new GameState();
        client = new Client();
        KryoConfig.config(client.getKryo());
        client.start();
    }

    public void connect() throws IOException {
        InetAddress target = client.discoverHost(KryoConfig.UDPPort, KryoConfig.timeout);

        client.connect(KryoConfig.timeout, target, KryoConfig.TCPPort, KryoConfig.UDPPort);
        client.addListener(this);

        client.sendTCP(new Events.Join());
    }

    @Override
    public void received(Connection connection, Object object){
        if (object instanceof Events.Move) {
            Events.Move response = (Events.Move)object;
            System.out.println(response.entity);
        }
    }

    @Override
    public void dispose() {
        client.removeListener(this);
        client.close();
    }
}
