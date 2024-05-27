package gr.uop;

import java.net.URL;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Server extends Application {

    ServerActions serverActions;
    ObservableList<UserData> clientList;
    URL url = this.getClass().getResource("styles/dark-theme-try.css");

    @Override
    public void start(Stage stage) throws Exception {

        clientList = FXCollections.observableArrayList();

        ServerActions serverActions = new ServerActions(clientList);
        serverActions.setAllLists();
        
        ServerSide server = new ServerSide(8787, 10 , clientList , serverActions); //το 10 αντιπροσοπεύει πόσα request αντέχει
        server.setIpToCurrentMachine();
        server.StartServer();

        Label label = new Label("hi hello");

        ServerUI serverUI = new ServerUI(stage, clientList, serverActions);

        serverUI.setUrl(url);
        serverUI.cashdeskUI();

        var scene = new Scene(serverUI.GetBpServer() ,1024, 768);
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        scene.getStylesheets().add(url.toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Car Wash Server");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
