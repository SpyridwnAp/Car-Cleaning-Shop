package gr.uop;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public class clientActions {

    public static void nodeActiveFromTextfield(TextField tf1, Node node , int minSize)
    {
        tf1.textProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("textfield changed from " + oldValue + " to " + newValue);
            if((newValue.trim().equals("")) || (newValue.trim().length() < minSize))
            {
                node.setDisable(true);
                //System.out.println("lalala");
            }
            else 
            {
                node.setDisable(false);
                //System.out.println("lololo");
            }
            System.out.println(newValue);
        });
    }

}
