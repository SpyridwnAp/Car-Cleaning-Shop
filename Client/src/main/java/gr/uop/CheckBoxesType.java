package gr.uop;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class CheckBoxesType {

    private String vehicle;
    private List<String> checkedCheckboxes;
    private Services services;
    Button sendButton;

    public CheckBoxesType(Services services){
        this.services = services;
    }

    public List<String> getList(){
        return checkedCheckboxes;
    }

    private Label checkboxesTotalPriceLabel;
    
    public void setLabelAndButtonForCheckBoxes(Label label, Button sendButton){
        checkboxesTotalPriceLabel = label;
        this.sendButton = sendButton;
        sendButton.setDisable(true);
    }

    public void changeCheckboxesLabel() throws NumberFormatException, Exception{
        checkboxesTotalPriceLabel.setText("Total Value: " + getTotalPriceFromCheckBoxes());
    }
    
    public Double getTotalPriceFromCheckBoxes() throws NumberFormatException, Exception{
        Double fn = 0.0;
        for (String i : checkedCheckboxes) {
            fn = fn + services.getPrice(Integer.parseInt(i) , vehicle);
        }
        return fn;
    }

    private void checkboxesChange(String action , int number){
        System.out.println("Action: " + action + " number: " + number);
        if(action == "add"){
            checkedCheckboxes.add(String.valueOf(number));
        }
        else if(action == "remove"){
            checkedCheckboxes.remove(String.valueOf(number));
        }
        else{
            System.out.println("Wrong Type");
        }

        try {
            changeCheckboxesLabel();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(checkedCheckboxes.size() > 0){
            sendButton.setDisable(false);
        }
        else{
            sendButton.setDisable(true);
        }
    }


    public VBox typeSelect(ComboBox combobox) throws Exception{
        checkedCheckboxes = new ArrayList<>();
        Label l2 = new Label("Select vehicle:");
        VBox vb2 = new VBox();
        if (combobox.getValue() == null)
        {
            vehicle = null;
        }
        else if(combobox.getValue() == "Car")
        {
            vehicle = "car";
            String str1= "Services\n";
            String str2= "Prices\n";
            Label l3 =new Label("Select services:");
            
            for(int i = 0 ; i < services.size() ; i++)
            {
                Service fn;
                try {
                    fn = services.getServiceByPosition(i);
                    if(fn.car != null){
                        str1 = str1 + fn.name + "\n";
                        str2 = str2 + fn.car + "\n";
                    }   
                } catch (Exception e1) {
                    //e1.printStackTrace();
                }
            }
            Label l4 = new Label(str1);
            Label l5 = new Label(str2);

            VBox vb3 =new VBox();
            
            Label l6 = new Label("Checked");
            vb3.getChildren().addAll(l6);

            CheckBox cb1 = new CheckBox();
            CheckBox cb2 = new CheckBox();
            CheckBox cb3 = new CheckBox();
            CheckBox cb4 = new CheckBox();
            CheckBox cb5 = new CheckBox();
            CheckBox cb6 = new CheckBox();
            CheckBox cb7 = new CheckBox();
            CheckBox cb8 = new CheckBox();
            CheckBox cb9 = new CheckBox();
            CheckBox cb10 = new CheckBox();
            
            vb3.getChildren().addAll(cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10);

            groupCheck(cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10);

            HBox hb3 = new HBox();
            hb3.getChildren().addAll(l4,l5,vb3);
            hb3.setAlignment(Pos.CENTER);
            hb3.setSpacing(10);

            vb2.getChildren().addAll(l3,hb3);
            vb2.setAlignment(Pos.CENTER);
            vb2.setSpacing(10);
        }
        else if(combobox.getValue() == "Jeep")
        {
            vehicle = "jeep";
            String str1= "Services\n";
            String str2= "Prices\n";
            Label l3 =new Label("Select services:");
            for(int i = 0 ; i < services.size() ; i++)
            {
                Service fn;
                try {
                    fn = services.getServiceByPosition(i);
                    if(fn.jeep != null){
                        str1 = str1 + fn.name + "\n";
                        str2 = str2 + fn.jeep + "\n";
                    }
                } catch (Exception e1) {
                    //e1.printStackTrace();
                }
                    
            }
            Label l4 = new Label(str1);
            Label l5 = new Label(str2);

            VBox vb3 =new VBox();

            Label l6 = new Label("Checked");
            vb3.getChildren().addAll(l6);
                
            CheckBox cb1 = new CheckBox();
            CheckBox cb2 = new CheckBox();
            CheckBox cb3 = new CheckBox();
            CheckBox cb4 = new CheckBox();
            CheckBox cb5 = new CheckBox();
            CheckBox cb6 = new CheckBox();
            CheckBox cb7 = new CheckBox();
            CheckBox cb8 = new CheckBox();
            CheckBox cb9 = new CheckBox();
            CheckBox cb10 = new CheckBox();
            
            vb3.getChildren().addAll(cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10);

            groupCheck(cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10);

            HBox hb3 = new HBox();
            hb3.getChildren().addAll(l4,l5,vb3);
            hb3.setAlignment(Pos.CENTER);
            hb3.setSpacing(10);

            vb2.getChildren().addAll(l3,hb3);
            vb2.setAlignment(Pos.CENTER);
            vb2.setSpacing(10);
        }
        else if(combobox.getValue() == "Motorbike")
        {
            vehicle = "motor";
            String str1= "Services\n";
            String str2= "Prices\n";
            Label l3 =new Label("Select services:");
            for(int i = 0 ; i < services.size() ; i++)
            {
                Service fn;
                try {
                    fn = services.getServiceByPosition(i);
                    if(fn.motor != null){
                        str1 = str1 + fn.name + "\n";
                        str2 = str2 + fn.motor + "\n";
                    }
                } catch (Exception e1) {
                    //e1.printStackTrace();
                }
                    
            }
            Label l4 = new Label(str1);
            Label l5 = new Label(str2);

            VBox vb3 =new VBox();

            Label l6 = new Label("Checked");
            vb3.getChildren().addAll(l6);
            
            CheckBox cb1 = new CheckBox();
            CheckBox cb2 = new CheckBox();
            CheckBox cb3 = new CheckBox();
            CheckBox cb4 = new CheckBox();

            vb3.getChildren().addAll(cb1,cb2,cb3,cb4);

            groupCheckMotor(cb1, cb2, cb3, cb4);


            HBox hb3 = new HBox();
            hb3.getChildren().addAll(l4,l5,vb3);
            hb3.setAlignment(Pos.CENTER);
            hb3.setSpacing(10);

            vb2.getChildren().addAll(l3,hb3);
            vb2.setAlignment(Pos.CENTER);
            vb2.setSpacing(10);
        }
        else{
            System.out.println("Wrong Type of vehicle");
        }
        return vb2;
    }

    public void groupCheck(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4, CheckBox cb5, CheckBox cb6, CheckBox cb7, CheckBox cb8, CheckBox cb9, CheckBox cb10)
    {
        cb1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    cb2.setSelected(false);
                    cb3.setSelected(false);
                    cb4.setSelected(false);
                    cb6.setSelected(false);
                    checkboxesChange("add", 0);
                }
                else{
                    checkboxesChange("remove", 0);
                }
            }
        });
        cb2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    cb1.setSelected(false);
                    cb3.setSelected(false);
                    cb5.setSelected(false);
                    cb6.setSelected(false);
                    checkboxesChange("add", 1);
                }
                else{
                    checkboxesChange("remove", 1);
                }
            }
        });
        cb3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                   cb1.setSelected(false);
                   cb2.setSelected(false);
                   cb4.setSelected(false);
                   cb5.setSelected(false);
                   cb6.setSelected(false);
                   checkboxesChange("add", 2);
                }
                else{
                    checkboxesChange("remove", 2);
                }
            }
        });
        cb4.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    cb1.setSelected(false);
                    cb3.setSelected(false);
                    cb5.setSelected(false);
                    cb6.setSelected(false);
                    checkboxesChange("add", 3);
                }
                else{
                    checkboxesChange("remove", 3);
                }
            }
        });
        cb5.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    cb2.setSelected(false);
                    cb3.setSelected(false);
                    cb4.setSelected(false);
                    cb6.setSelected(false);
                    checkboxesChange("add", 4);
                }
                else{
                    checkboxesChange("remove", 4);
                }
            }
        });
        cb6.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue){
                    cb1.setSelected(false);
                    cb2.setSelected(false);
                    cb3.setSelected(false);
                    cb4.setSelected(false);
                    cb5.setSelected(false);
                    checkboxesChange("add", 5);
                }
                else{
                    checkboxesChange("remove", 5);
                }
            }
        });
        cb7.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    checkboxesChange("add", 6);
                }
                else{
                    checkboxesChange("remove", 6);
                }
            }
        });
        cb8.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    checkboxesChange("add", 7);
                }
                else{
                    checkboxesChange("remove", 7);
                }
            }
        });
        cb9.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    checkboxesChange("add", 8);
                }
                else{
                    checkboxesChange("remove", 8);
                }
            }
        });
        cb10.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    checkboxesChange("add", 9);
                }
                else{
                    checkboxesChange("remove", 9);
                }
            }
        });
    }

    public void groupCheckMotor(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4)
    {
        cb1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    cb2.setSelected(false);
                    checkboxesChange("add", 0);
                }
                else{
                    checkboxesChange("remove", 0);
                }
            }
        });
        cb2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    cb1.setSelected(false);
                    checkboxesChange("add", 3);
                }
                else{
                    checkboxesChange("remove", 3);
                }
            }
        });
        cb3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    checkboxesChange("add", 7);
                }
                else{
                    checkboxesChange("remove", 7);
                }
            }
        });
        cb4.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(newValue == true){
                    checkboxesChange("add", 8);
                }
                else{
                    checkboxesChange("remove", 8);
                }
            }
        });
    }

}
