package gr.uop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;

public class ServerSide implements Runnable{

    private ServerSocket server;
    //private List<Connection> connections;
    private Thread thread;
    private InetAddress host;
    private int port;
    private int backlog;
    ObservableList<UserData> clientList;
    ServerActions serverActions;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    



    public ServerSide(int port, int backlog, ObservableList<UserData> client , ServerActions serverActions) throws Exception{
        this.port = port;
        this.backlog = backlog;
        clientList = client;
        this.serverActions = serverActions;
    }

    public void StartServer() throws Exception{
        try {
            server = new ServerSocket(port, backlog, host);
        } catch (UnknownHostException e) {
            throw new Exception("Host name could not be resolved: " + host, e);
        } catch (IllegalArgumentException e) {
            throw new Exception("Port number needs to be between 0 and 65535 (inclusive): " + port);
        } catch (IOException e) {
            throw new Exception("Server could not be started.", e);
        }
        thread = new Thread(this);
        thread.start();
    }

    public void setIpToCurrentMachine() throws UnknownHostException{
        host = Inet4Address.getLocalHost();
        System.out.println(host.getHostAddress());
    }


    @Override
    public void run() {
        ObjectInputStream inputStream;
        while(true){
            try {
                Socket socketClient = server.accept();
                inputStream = new ObjectInputStream(socketClient.getInputStream());
                UserData userData = (UserData)inputStream.readObject();
                System.out.println("pinakida pou elaba : " + userData.pinakida);

                
                
                userData.timeArival = dtf.format(LocalDateTime.now());
                

                //clientList.add(userData);
                serverActions.clientListAll.add(userData);
                //ObjectOutputStream fileSend = new ObjectOutputStream(new FileOutputStream("Server/cars.data" , true));
                //fileSend.writeObject(userData);

            } catch (Exception e) {
                System.out.println("oooo wooooow something went wrong");
            }
        }
    }



    

    // public void broadcast(Object data) {
    //     if (server.isClosed()) {
    //         throw new IllegalStateException("Data not sent, server is offline.");
    //     }
    //     if (data == null) {
    //         throw new IllegalArgumentException("null data");
    //     }

    //     synchronized (connectionsLock) {
    //         for (Connection connection : connections) {
    //             try {
    //                 connection.send(data);
    //                 System.out.println("Data sent to client successfully.");
    //             } catch (NetworkException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    // }

    // public void disconnect(Connection connection) throws NetworkException {
    //     if (connections.remove(connection)) {
    //         connection.close();
    //     }
    // }

    // public void close() throws NetworkException {
    //     synchronized (connectionsLock) {
    //         for (Connection connection : connections) {
    //             try {
    //                 connection.close();
    //             } catch (NetworkException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    //     connections.clear();

    //     try {
    //         server.close();
    //     } catch (IOException e) {
    //         throw new NetworkException("Error while closing server.");
    //     } finally {
    //         thread.interrupt();
    //     }
    // }

    // public boolean isOnline() {
    //     return !server.isClosed();
    // }

    // public Connection[] getConnections() {
    //     synchronized (connectionsLock) {
    //         return connections.toArray(new Connection[connections.size()]);
    //     }
    // }
    
}
