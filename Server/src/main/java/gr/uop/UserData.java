package gr.uop;

import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable{
    public List<String> list;
    public String pinakida;
    public String timeArival;
    public String timeGone;
    public Double totalPrice;
}
