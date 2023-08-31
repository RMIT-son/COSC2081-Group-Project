import java.util.*;
import java.io.*;

public class Member extends Customer{
    public Member(String id_, String name_, String address_, String phone_, String customerType_, String username_, String password_) {
        this.setid(id_);
        this.setName(name_);
        this.setAddress(address_);
        this.setPhone(phone_);
        this.setCustomerType(customerType_);
        this.setUsername(username_);
        this.setPassword(password_);
    }

    HashMap<String,Order> myOrders = new HashMap<>();

    public Order createOrder(String wantedItemTitle, Integer itemAmount, Integer ordersSize, HashMap<String,Item> items) throws Exception{
        FileOutputStream fos = new FileOutputStream("OrdersData.txt",true);
        String data = "Hello World!";

        String[] mold = String.valueOf(Double.valueOf(ordersSize+1)/1000).split("");
        String id = "O";
        for (int i = 2; i < mold.length; i++){
            id = id.concat(mold[i]);
        }
        if (ordersSize==0){
            data = id + "," + items.get(wantedItemTitle).getTitle() + "," + itemAmount + "," + "UNPAID" + "," + this.getid();
        } else {
            data = "\n"+ id + "," + items.get(wantedItemTitle).getTitle() + "," + itemAmount + "," + "UNPAID" + "," + this.getid();
        }
        fos.write(data.getBytes());
        fos.close();
        Order order = new Order(id, wantedItemTitle, itemAmount, "UNPAID",this.getid());
        this.myOrders.put(wantedItemTitle, order);
        return order;
    }  

    public void listMyOrders(){
        if (this.myOrders.size()==0){
            System.out.println("No Order Found!!");
        } 
        else {
            System.out.println("\n");
            System.out.printf("%-10s %-20s %-15s %-10s\n","ID","Title of Item","Quantity","Status");
            for (String o : this.myOrders.keySet()){
                Order order = myOrders.get(o);
                System.out.printf("%-10s %-20s %-15s %-10s\n",order.id,order.itemTitle,String.valueOf(order.itemAmount),order.status);
            }
        }
    }
}
