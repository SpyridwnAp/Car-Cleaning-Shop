package gr.uop;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDateTime;

public class ServerUI {

    TableView<UserData> table;
    private BorderPane bpserver = new BorderPane();
    Button CancelUser = new Button("Cancel Client");
    Button EntryUser = new Button("Client Entry");
    HBox hbΕntryCancelClient = new HBox();
    Stage stage;
    ServerActions serverActions;
    URL url;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  

    ObservableList<UserData> clientList;

    String s= "";

    public ServerUI(Stage stage , ObservableList<UserData> list , ServerActions serverActions)
    {
        this.stage = stage;
        clientList = list;
        this.serverActions = serverActions;
        table = new TableView<>(list);
    }

    public void setUrl(URL url){
        this.url = url;
    }

    public void cashdeskUI(){

        Label l1 = new Label("Cash desk");
        

        TableColumn<UserData, String> colLP = new TableColumn<>("License Plate");
        colLP.setPrefWidth(240);
        //colLP.setMinWidth(200);
        colLP.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserData,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<UserData, String> ok) {
                return new ReadOnlyObjectWrapper(ok.getValue().pinakida);
            }
            
        });

        TableColumn<UserData, String> colPrice = new TableColumn<>("Total Price");
        colPrice.setPrefWidth(240);
        //colPrice.setMinWidth(200);
        colPrice.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserData,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<UserData, String> ok) {
                return new ReadOnlyObjectWrapper(ok.getValue().totalPrice);
            }
            
        });


        TableColumn<UserData, String> colArrivalTime = new TableColumn<>("Αrrival Τime");
        colArrivalTime.setPrefWidth(240);
        //colArrivalTime.setMinWidth(200);
        colArrivalTime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserData,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<UserData, String> ok) {
                return new ReadOnlyObjectWrapper(ok.getValue().timeArival);
            }
            
        });


        
        table.getColumns().addAll(colLP, colPrice, colArrivalTime);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        
        // ObservableList<UserData> client = FXCollections.observableArrayList();

        table.setItems(clientList);
        table.setMaxSize(750, 400);
        table.setPrefSize(750, 400);
        
    
        hbΕntryCancelClient.getChildren().addAll(EntryUser, CancelUser);
        hbΕntryCancelClient.setAlignment(Pos.CENTER);
        hbΕntryCancelClient.setSpacing(20);

        VBox vbox1 = new VBox();
        vbox1.setSpacing(5);
        vbox1.setPadding(new Insets(10, 0, 0, 10));
        vbox1.setAlignment(Pos.CENTER);
        vbox1.getChildren().addAll(l1, table ,hbΕntryCancelClient);

        
        EntryClientWithEnter();
        DeleteItemFromTable();
        EntryUser();
        CloseERRORifnotempty();

        bpserver.setCenter(vbox1);
 

    }
    
    public void DeleteItemFromTable()
    {
        CancelUser.setOnAction(e -> {
            UserData selectedItem = table.getSelectionModel().getSelectedItem();
            if(selectedItem != null){
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure that you want to delete the Client?");
                alert.initOwner(stage);
                alert.setTitle("Confirm Cancel");
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    
                    serverActions.removeFromBigList(clientList.indexOf(selectedItem));
                    //table.getItems().remove(selectedItem);
                } 
            }
        });
        
    }

    public void EntryUser()
    {
        
        EntryUser.setOnAction(e -> {
            addEntry();
        });
        
    }

    public void CloseERRORifnotempty(){
        stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                ObservableList<UserData> items = table.getItems();


               if(items == null || items.isEmpty())
               {
                   System.exit(0);
               }
               else
               {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "There are still clients to serve!\nDo you still wanna go?\n(The data will be saved)");
                    alert.initOwner(stage);
                    alert.setTitle("Confirm Cancel");
                    alert.getDialogPane().getStylesheets().add(url.toExternalForm());
                    Optional<ButtonType> result = alert.showAndWait();
                    ButtonType button = result.orElse(ButtonType.CANCEL);
                    if (button == ButtonType.OK) {
                        System.exit(0);
                    }
                    else{
                        event.consume();
                    }
               }
            }
        });
    }

    public void EntryClientWithEnter(){
        table.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                addEntry();
            }
        });

    }

    public void addEntry(){
        UserData selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            Alert alert = new Alert(AlertType.CONFIRMATION, selectedItem.toString());
            alert.initOwner(stage);
            alert.setTitle("Confirm");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {
                selectedItem.timeGone = dtf.format(LocalDateTime.now());
                System.out.println("Got it");
                
                serverActions.makeDoneFromBigList(clientList.indexOf(selectedItem));
                //table.getItems().remove(selectedItem);
            } 
        }
    }

    

    public Parent GetBpServer()
    {
        return bpserver;
    }
    
}