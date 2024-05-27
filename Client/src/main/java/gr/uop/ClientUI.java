package gr.uop;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientUI 
{
    private Stage stage;
    private BorderPane bp = new BorderPane();
    private VBox centerVbox = new VBox();
    private VBox topVbox = new VBox();
    private VBox bottomVbox = new VBox();

    private Services services;

    private BorderPane bp1 = new BorderPane();
    

    public ClientUI(Stage stage)
    {
        this.stage = stage;
        bp.setCenter(centerVbox);
        bp.setTop(topVbox);
        bp.setBottom(bottomVbox);
        services = null;
    }

    public void setOwnerAndBlockIT(Stage owner){
        this.stage.initOwner(owner);
        this.stage.initModality(Modality.WINDOW_MODAL);
    }

    public void allLaunch(){
        centerVbox.setAlignment(Pos.CENTER);
        centerVbox.setSpacing(10);
        topVbox.setAlignment(Pos.CENTER);
        topVbox.setSpacing(10);
        bottomVbox.setAlignment(Pos.CENTER);
        bottomVbox.setSpacing(10);
    }

    public VBox fnGetVBox(String ok) throws Exception{
        if(ok == "center"){
            return centerVbox;
        }
        else if(ok == "top"){
            return topVbox;
        }
        else if(ok == "bottom"){
            return bottomVbox;
        }
        else{
            throw new Exception("YOU SHOULD ONLY USE CENTER TOP OR BOTTOM");
        }
        
    }

    public Node fnAddNode(String ok,int position, Node node) throws Exception{
        if(ok == "center"){
            if(position == -1){
                centerVbox.getChildren().add(node);
            }
            else if(position > -1 && position <= centerVbox.getChildren().size() ){
                centerVbox.getChildren().add(position, node);
            }
            else{
                throw new Exception("Wrong position");
            }
            return node;
        }
        else if(ok == "top"){
            if(position == -1){
                topVbox.getChildren().add(node);
            }
            else if(position > -1 && position <= topVbox.getChildren().size() ){
                topVbox.getChildren().add(position, node);
            }
            else{
                throw new Exception("Wrong position");
            }
            return node;
        }
        else if(ok == "bottom"){
            if(position == -1){
                bottomVbox.getChildren().add(node);
            }
            else if(position > -1 && position <= bottomVbox.getChildren().size() ){
                bottomVbox.getChildren().add(position, node);
            }
            else{
                throw new Exception("Wrong position");
            }
            return node;
        }
        else{
            throw new Exception("YOU SHOULD ONLY USE CENTER TOP OR BOTTOM");
        }
        
        
    }

    public HBox fnAdd(String ok,int position) throws Exception{
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);


        if(ok == "center"){
            if(position == -1){
                centerVbox.getChildren().add(hb);
            }
            else if(position > -1 && position <= centerVbox.getChildren().size() ){
                centerVbox.getChildren().add(position, hb);
            }
            else{
                throw new Exception("Wrong position");
            }
        }
        else if(ok == "top"){
            if(position == -1){
                topVbox.getChildren().add(hb);
            }
            else if(position > -1 && position <= topVbox.getChildren().size() ){
                topVbox.getChildren().add(position, hb);
            }
            else{
                throw new Exception("Wrong position");
            }
        }
        else if(ok == "bottom"){
            if(position == -1){
                bottomVbox.getChildren().add(hb);
            }
            else if(position > -1 && position <= bottomVbox.getChildren().size() ){
                bottomVbox.getChildren().add(position, hb);
            }
            else{
                throw new Exception("Wrong position");
            }
        }
        else{
            throw new Exception("YOU SHOULD ONLY USE CENTER TOP OR BOTTOM");
        }
        return hb;
    }

    public Node fnGet(String ok,int position) throws Exception{//Βασισμένη στην από πάνω που δημιουργεί πάντα HBox
        Node fn;
        if(ok == "center"){
            fn = centerVbox.getChildren().get(position);
        }
        else if(ok == "top"){
            fn = topVbox.getChildren().get(position);
        }
        else if(ok == "bottom"){
            fn = bottomVbox.getChildren().get(position);
        }
        else{
            throw new Exception("YOU SHOULD ONLY USE CENTER TOP OR BOTTOM");
        }

        if(fn.getClass().isAssignableFrom(HBox.class)){
            return (Node)((HBox)fn).getChildren().get(0); //Η javafx αφήνει μόνο nodes να προσθεθούν οπότε δεν χρειάζεται έλεγχος
        }
        else{
            throw new Exception("PLEASE PROVIDE AN AUTO GENERATED ClientUI HBOX THINGY");
        }
    }

    public Node fnGet(String ok,int position , int position2) throws Exception{//Βασισμένη στην από πάνω που δημιουργεί πάντα HBox
        Node fn;
        if(ok == "center"){
            fn = centerVbox.getChildren().get(position);
        }
        else if(ok == "top"){
            fn = topVbox.getChildren().get(position);
        }
        else if(ok == "bottom"){
            fn = bottomVbox.getChildren().get(position);
        }
        else{
            throw new Exception("YOU SHOULD ONLY USE CENTER TOP OR BOTTOM");
        }

        if(fn.getClass().isAssignableFrom(HBox.class)){
            return (Node)((HBox)fn).getChildren().get(position2); //Η javafx αφήνει μόνο nodes να προσθεθούν οπότε δεν χρειάζεται έλεγχος
        }
        else{
            throw new Exception("PLEASE PROVIDE AN AUTO GENERATED ClientUI HBOX THINGY");
        }
    }

    public void setServices(Services services){
        this.services = services;
    }

    public Parent GetDialogBp()
    {
        return bp1;
    }


    public Parent GetBp()
    {
        return bp;
    }
}