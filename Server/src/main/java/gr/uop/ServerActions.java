package gr.uop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

import javafx.collections.FXCollections;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class ServerActions {
 
    ObservableList<UserData> clientList;
    ObservableList<UserData> clientListAll;
    File file , txtfile;
    boolean canBeUpdated;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    Services services;
    
    public ServerActions(ObservableList<UserData> clientList){
        this.clientList = clientList;
        clientListAll = FXCollections.observableArrayList();
        file = new File("Server/cars.data");
        txtfile = new File("Server/cars.csv");
        canBeUpdated = true;
        

        clientListAll.addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable arg0) {
                update();
            }
            
        });
    }

    public void update(){
        System.out.println("changed");
        boolean setFileFromlist;
        if(canBeUpdated == true){
            try {
                setFileFromlist = setFileFromlist(clientListAll);
                setListFromUncheckedCars(clientList);
                writeFullListAtTxt();
                System.out.println("should be written");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("something went wrong at writing");
                e.printStackTrace();
            }
        }
    }

    public void setAllLists() throws Exception{
        setListFromFile(clientListAll);
        setListFromUncheckedCars(clientList);
        services = new Services();
        services.getValuesFromFile(new File("Server/src/main/java/gr/uop/Services.csv"));
        System.out.println("this should appear...");
        System.out.println("ClientList size: " + clientList.size());
        System.out.println("ClientList ALL size: " + clientListAll.size());
    }

    public boolean setFileFromlist(ObservableList<UserData> list) throws FileNotFoundException, IOException{
        ObjectOutputStream fileSend = new ObjectOutputStream(new FileOutputStream(file));
        if((list != null) && list.size() != 0){
            int i = 0;
            for(UserData userData2 : list) {
                fileSend.writeObject(userData2);
                i++;
            }
            System.out.println("Written lines should be: " + (i+1));
            fileSend.flush();
            return true;
        }
        else{
            return false;
        }
    }

    public void setListFromFile(ObservableList<UserData> list) throws FileNotFoundException, IOException{
        canBeUpdated = false;
        list.clear();
        ObjectInputStream fileGet = new ObjectInputStream(new FileInputStream(file));
        int i = 0;
        try {
            while (true) {
                UserData userData = (UserData)fileGet.readObject();
                list.add(userData);
                i++;
            }
        } catch (Exception e) {
            System.out.println("The List should have: " + (i+1));
            System.out.println("End of list... : " + e.toString());
        }
        canBeUpdated = true;
            
    }

    public void setListFromUncheckedCars(ObservableList<UserData> list) throws FileNotFoundException, IOException{
        canBeUpdated = false;
        list.clear();
        ObjectInputStream fileGet = new ObjectInputStream(new FileInputStream(file));
        int i = 0;
        try {
            while (true) {
                UserData userData = (UserData)fileGet.readObject();
                if(userData.timeGone == ""){
                    list.add(userData);
                }
                else{
                    System.out.println("Found a done car");
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println("The list from unchecked should have: " + (i+1));
            System.out.println("End of list... : " + e.toString());
        }
        canBeUpdated = true;
    }

    public void removeFromBigList(int number){
        System.out.println("The number in small list: " + number);
        int k = getNumberInFullList(number);
        System.out.println("The number in all list: " + k);
        if(k != -1){
            clientListAll.remove(k);
            System.out.println("done the remove");
        }
        else{
            System.out.println("Something went really wrong in remove");
        }
    }

    public void makeDoneFromBigList(int number){
        int k = getNumberInFullList(number);
        if(k != -1){
            clientListAll.get(k).timeGone = dtf.format(LocalDateTime.now());
            System.out.println("Before time done let's check: " + clientListAll.get(k).timeArival);
            update();
            //clientList.remove(number);
            System.out.println("done but actually done ?");
        }
        else{
            System.out.println("Something went really wrong in done");
        }
    }

    public int getNumberInFullList(int number){
        if(clientList.size() > number && number > -1){
            int i = 0;
            int k = 0;
            boolean check = false;
            for (UserData userData : clientListAll) {
                if(userData.timeGone == ""){
                    if(k == number){
                        check = true;
                        System.out.println("it should break");
                        break;
                    }
                    k++;
                }
                System.out.println("--K--");
                i++;
            }
            if(check == true){
                System.out.println("the i is: " + i);
                System.out.println("the k is: " + k);
                return i;
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }
        
    }

    public void writeFullListAtTxt() throws Exception{
        FileWriter fw = new FileWriter(txtfile);
        String bt = " , ";
        String bt2 = " | ";
        fw.write("License Plate" + bt + "Services" + bt + "Total Price" + bt + "Time Arival" + bt + "Time Gone\n");
        if((clientListAll != null) && clientListAll.size() != 0){
            int i = 0;
            for(UserData userData2 : clientListAll) {
                fw.write(userData2.pinakida + bt + "{");
                int j = 0;
                for (String service: userData2.list) {
                    if(j != 0){
                        fw.write(bt2);
                    }
                    Service fn = services.getServiceByPosition(Integer.parseInt(service));
                    fw.write(fn.name);
                    j++;
                }
                fw.write("}" + bt);
                fw.write(userData2.totalPrice + bt + userData2.timeArival + bt + userData2.timeGone + "\n");
                i++;
            }
            fw.flush();
        }
        else{
            System.out.println("nothing to write...");
        }
    }

    
    
}
