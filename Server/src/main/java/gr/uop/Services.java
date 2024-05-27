package gr.uop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Services implements Serializable{
        
    private Map<String , Service> services;
    File file;

    public Services(){
        services = new TreeMap<>();
    }

    public void lets_try() throws Exception{
        Service fn = getServiceByPosition(2);
        System.out.println(fn.name);
    }

    public Map<String , Service> getMap(){
        return services;
    }

    public int size(){
        return services.size();
    }

    public Double getPrice(String service , String type) throws Exception{
        Service fn = services.get(service);
        if(fn != null){
            if(type == "car"){
                return fn.car;
            }
            else if(type == "jeep"){
                return fn.jeep;
            }
            else if(type == "motor"){
                return fn.motor;
            }
            else{
                throw new Exception("WRONG TYPE OF VECHICLE");
            }
        }
        else{
            throw new Exception("COULN'T FIND THE SERVICE");
        }
    }

    public Double getPrice(int position , String type) throws Exception{
        return getPrice(getKeyFromFilePosition(position), type);
    }

    public String getKeyByPosition(int position) throws Exception{
        return getKeyFromFilePosition(position);
    }

    public Service getServiceByPosition(int position) throws Exception{
        return services.get(getKeyFromFilePosition(position));
    }

    public String getKeyFromFilePosition(int position) throws Exception{
        int k = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineValues = line.split(",");
                if(k == position){
                    return lineValues[0];
                }
                k++;
            }
            throw new Exception("Couldn't find that sorryyyy");
        }
    }

    public void getValuesFromFile(File file) throws Exception{
        this.file = file;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineValues = line.split(",");
                Service fn = new Service();
                fn.name = lineValues[1];
                fn.car = Double.parseDouble(lineValues[2]);
                fn.jeep = Double.parseDouble(lineValues[3]);
                fn.motor = Double.parseDouble(lineValues[4]);
                if(fn.car == -1){
                    fn.car = null;
                }
                if(fn.jeep == -1){
                    fn.jeep = null;
                }
                if(fn.motor == -1){
                    fn.motor = null;
                }
                
                services.put(lineValues[0], fn);

            }
        }
    }
}
