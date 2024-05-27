package gr.uop;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;

public class FindAddress implements Runnable{

    boolean found;
    Socket socket;
    private Thread thread;


    public FindAddress(){
        found = false;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(found == false){
            try{
                Enumeration ef = NetworkInterface.getNetworkInterfaces();
                while(ef.hasMoreElements() && found == false)
                {
                    NetworkInterface n = (NetworkInterface) ef.nextElement();
                    Enumeration ee = n.getInetAddresses();
                    while (ee.hasMoreElements() && found == false)
                    {
                        InetAddress i = (InetAddress) ee.nextElement();
                        System.out.println(i.getHostAddress());
                        try {
                            Socket ts = new Socket(i.getHostAddress() , 8787);
                            System.out.println("found it it's: " + i.getHostAddress());
                            socket = ts;
                            found = true;
                        } catch (Exception wow) {
                            System.out.println("nope not the correct address");
                        }
                    }
                }
            }
            catch (Exception e) {
                System.out.println("SYSTEM ERROR CAN'T DETECT ANY ADDRESSES");
            }
        }
    }
    
}
