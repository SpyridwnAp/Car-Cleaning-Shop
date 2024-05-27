package gr.uop;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Keyboard {
    
    private VBox vbCloseAlphSpace = new VBox();
    private HBox hbnumbers1 = new HBox();
    private HBox hbnumbers2 = new HBox();
    private HBox hbnumbers3 = new HBox();
    private HBox hbletters1 = new HBox();
    private HBox hbletters2 = new HBox();
    private Button keyboardbttn = new Button("Keyboard");
    Button closeKeyboard = new Button("Close");
    private HBox hbKeyboard = new HBox();
    private VBox numberVb = new VBox();
    private HBox hbCloseDelete = new HBox();

    private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Button[] numberbttn = new Button[10];
    private Button[] lettersbttn = new Button[26];
    String strl;

    TextField tf1 = new TextField();
    //ta panw ta dilwneis global gia na mhn ta pernas kathe fora mesa se shnarthseis h na ta kanei return


    public void setKeyboardButton(Button button){
        keyboardbttn = button;
    }


    public Keyboard(VBox vb1 , Button button ,TextField textfield)
        {
            keyboardbttn = button;
            tf1 = textfield;

            hbnumbers1.setAlignment(Pos.CENTER);
            hbnumbers1.setSpacing(5);

            hbnumbers2.setAlignment(Pos.CENTER);
            hbnumbers2.setSpacing(5);

            hbnumbers3.setAlignment(Pos.CENTER);
            hbnumbers3.setSpacing(5);

            numberVb.setAlignment(Pos.CENTER);

            hbletters1.setAlignment(Pos.CENTER);
            hbletters1.setSpacing(5);

            hbletters2.setAlignment(Pos.CENTER);
            hbletters2.setSpacing(5);

            Button Delete = new Button("Backspace");
            DeleteAction(Delete);

            Button Space = new Button("Space");
            SpaceAction(Space);
            Space.setPrefWidth(710);

            numberbttn[0] = new Button("0");
            KeyboardnumAction(0);
            numberbttn[0].setPrefWidth(64.9);
            
            for(int i=1 ; i< 4 ; i++){
                numberbttn[i] = new Button(""+ i +"");
                hbnumbers1.getChildren().addAll(numberbttn[i]);
                numberbttn[i].setPrefWidth(64.9);
                KeyboardnumAction(i);
            }

            for(int i=4 ; i< 7 ; i++){
                numberbttn[i] = new Button(""+ i +"");
                hbnumbers2.getChildren().addAll(numberbttn[i]);
                numberbttn[i].setPrefWidth(64.9);
                KeyboardnumAction(i);
            }
            
            for(int i=7 ; i< 10 ; i++){
                numberbttn[i] = new Button(""+ i +"");
                hbnumbers3.getChildren().addAll(numberbttn[i]);
                numberbttn[i].setPrefWidth(64.9);
                KeyboardnumAction(i);
            }
            
            
            for(int i=0 ; i< 13 ; i++){
                lettersbttn[i] = new Button(alphabet[i]);
                hbletters1.getChildren().addAll(lettersbttn[i]);
                lettersbttn[i].setPrefWidth(50);
                KeyboardletterAction(i);
            }
            for(int i=13 ; i< 26 ; i++){
                lettersbttn[i] = new Button(alphabet[i]);
                hbletters2.getChildren().addAll(lettersbttn[i]);
                lettersbttn[i].setPrefWidth(50);
                KeyboardletterAction(i);
            }

            hbCloseDelete.getChildren().addAll(closeKeyboard, Delete);
            hbCloseDelete.setSpacing(595);

            numberVb.getChildren().addAll(hbnumbers3,hbnumbers2,hbnumbers1,numberbttn[0]);
            numberVb.setSpacing(5);

            vbCloseAlphSpace.getChildren().addAll(hbCloseDelete, hbletters1, hbletters2, Space);
            vbCloseAlphSpace.setAlignment(Pos.CENTER_LEFT);
            vbCloseAlphSpace.setSpacing(5);

            hbKeyboard.getChildren().addAll(vbCloseAlphSpace, numberVb);
            hbKeyboard.setAlignment(Pos.TOP_CENTER);
            hbKeyboard.setSpacing(10);

            hbKeyboard.setVisible(true);
            keyboardbttn.setDisable(true);

            vb1.getChildren().addAll(hbKeyboard);

            keyboardbttn.setOnAction((e) -> {
                strl = "";
                hbKeyboard.setVisible(true);
                keyboardbttn.setDisable(true);
            });
            closeKeyboard.setOnAction((e) -> {
                hbKeyboard.setVisible(false);
                keyboardbttn.setDisable(false);
            });
    }

    public void KeyboardnumAction(int i)
    {
        String s=String.valueOf(i);
        numberbttn[i].setOnAction((e) -> {
            tf1.setText(tf1.getText() + s);
        });
    }

    public void KeyboardletterAction(int i)
    {
        String s=alphabet[i];
        lettersbttn[i].setOnAction((e) -> {
            tf1.setText(tf1.getText() + s);
        });
    }

    public void DeleteAction(Button Delete)
    {
        
        Delete.setOnAction((e) -> {
            tf1.setText( tf1.getText().substring(0, tf1.getText().length() -1) );
        });
    }

    public void SpaceAction(Button Space)
    {
        Space.setOnAction((e) -> {
            String s = " ";
            tf1.setText(tf1.getText() + s);
        });
    }

}
