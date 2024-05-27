package gr.uop;

import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Client extends Application {

    
    Services services;
    Socket s;
    FindAddress address;
    URL url = this.getClass().getResource("styles/dark-theme-try.css");

    @Override
    public void start(Stage stage) throws Exception {
        ClientUI clui = new ClientUI(stage);

        address = new FindAddress();


        //////////////////////////

        services = new Services();

        File file = new File("Client/src/main/java/gr/uop/Services.csv");
        services.getValuesFromFile(file);

        System.out.println(services.getMap().toString());

        services.lets_try();

        Double car_price = services.getPrice("pl_out", "car");
        System.out.println(car_price);


        ////////////-------SERVICES--------////////////////////

        ///fn
        clui.allLaunch();

        clui.fnAddNode("center",-1 , new Label("Enter the licence number:"));
        
        Pane fn;

        fn = clui.fnAdd("center", -1);
        TextField tf = new TextField("");
        tf.setPromptText("Licence number:");
        Button keyboardbttn = new Button("Keyboard");
        fn.getChildren().addAll(tf , keyboardbttn);

        fn = clui.fnAdd("center",-1);
        Button b = new Button("Next");
        b.setDisable(true);
        fn.getChildren().add(b);

        System.out.println(clui.fnGet("center",1).getClass());
        // (TextField)((HBox)(clui.getCenterVBox()).getChildren().get(1)).getChildren().get(0)
        clientActions.nodeActiveFromTextfield( (TextField)clui.fnGet("center",1) , clui.fnGet("center",2) , 2);

        fn = clui.fnAdd("bottom", -1);
        VBox Vkeyboard = new VBox();
        Keyboard keyboard = new Keyboard(Vkeyboard , keyboardbttn , tf);
        keyboard.getClass();
        fn.getChildren().addAll(Vkeyboard);
        VBox.setMargin(fn, new Insets(0 , 0 , 100 , 0));
        System.out.println(Vkeyboard.toString());
        
        /////fn

        // clui.FirstScreen();
        //clui.dialogUI((Button)clui.fnGet("center",2));

        b.setOnAction(e -> {
            try {
                servicesWindow(stage , tf.getText());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        

        var scene = new Scene(clui.GetBp(), 1024, 768);

        
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        scene.getStylesheets().add(url.toExternalForm());


        stage.setScene(scene);
        stage.setTitle("Car Wash Client");
        stage.show();
    }

////////////////////////Second Window

    private void servicesWindow(Stage main, String pinakida) throws Exception{

        Stage window = new Stage();
        window.setTitle("Services");
        ClientUI sclui = new ClientUI(window);
        sclui.allLaunch();
        sclui.fnAddNode("center",-1 , new Label("Select vehicle:"));
        sclui.setOwnerAndBlockIT(main);

        Pane fn;

        fn = sclui.fnAdd("center", -1);
        ComboBox<String> combobox = new ComboBox<>();
        combobox.getItems().addAll(
            "Car",
            "Jeep",
            "Motorbike"
        );
        Button buttonVechSelect = new Button("Submit Selection");
        fn.getChildren().addAll(combobox , buttonVechSelect);

        fn = sclui.fnAdd("center", -1);
        Label label = new Label("Select Something"); // θα αντικατασταθεί
        fn.getChildren().addAll(label);

        fn = sclui.fnAdd("center", -1);
        Label synolo = new Label("Total Value: 0");
        fn.getChildren().addAll(synolo);

        //fn.getChildren().add(sclui.buttonSelect(buttonVechSelect, combobox, label, services));

        fn = sclui.fnAdd("center", -1);
        Label pinakidaLabel = new Label("Your Plate: " + pinakida); // θα αντικατασταθεί
        fn.getChildren().addAll(pinakidaLabel);

        fn = sclui.fnAdd("center", -1);
        Button buttonDone = new Button("DONE");
            
        CheckBoxesType checkBoxesType = new CheckBoxesType(services);
        checkBoxesType.setLabelAndButtonForCheckBoxes(synolo , buttonDone);

        buttonVechSelect.setOnAction( e -> {
            try {
                sclui.fnGetVBox("center").getChildren().remove(2);
                sclui.fnGetVBox("center").getChildren().add(2, checkBoxesType.typeSelect(combobox) /* sclui.buttonSelect(combobox , services) */ );
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        


        //finding the server
        Label connectingLabel = new Label("Connecting with the server please wait...");

        //den douleuei kala akoma isws na mhn to ftiacoume kiolas
        var scene = new Scene(connectingLabel, 1024, 768);
        
        
        

        window.setScene(scene);
        window.show();
        scene.getStylesheets().add(url.toExternalForm());

        while(address.found != true){
            connectingLabel.setText("Connecting with the server please wait.");
            TimeUnit.SECONDS.sleep(1);
            connectingLabel.setText("Connecting with the server please wait..");
            TimeUnit.SECONDS.sleep(1);
            connectingLabel.setText("Connecting with the server please wait...");
            TimeUnit.SECONDS.sleep(1);
        }
        window.close();

        buttonDone.setOnAction( e -> {
            System.out.println("DONE");
            try {
                UserData userData = new UserData();
                userData.list = checkBoxesType.getList();
                userData.pinakida = pinakida;
                userData.totalPrice = checkBoxesType.getTotalPriceFromCheckBoxes();
                userData.timeArival = "";
                userData.timeGone = "";

                //sending at the server
                //Socket s = new Socket("192.168.56.1" , 8787);

                ObjectOutputStream sendServer = new ObjectOutputStream(address.socket.getOutputStream());
                sendServer.writeObject(userData);
                
                System.out.println("\nHERE\n");

                window.close();
                

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        fn.getChildren().add(buttonDone);


        scene = new Scene(sclui.GetBp(), 1024, 768);
        scene.getStylesheets().add(url.toExternalForm());
        window.setScene(scene);

        window.showAndWait();

    }

    

    public static void main(String[] args) {
        launch(args);
    }

}
