import java.util.*;
import java.io.*;
public class Admin extends Member{   
    
    Double revenue = 0.0;
    
    public Admin(String id_, String name_, String address_, String phone_, String customerType_, String username_, String password_) {
        super(id_,name_,address_,phone_,customerType_,username_,password_);
    }

    public void addToRevenue(Order order, HashMap<String,Item> items){
        this.revenue += items.get(order.itemTitle).getPrice() * order.itemAmount;
    }

    public void listMembers(HashMap<String, Member> members){
        System.out.println("\n");
        System.out.printf("%-10s %-20s %-30s %-15s %-20s\n","ID","Name","Address","Phone","Username");
        for (String m : members.keySet()){
            Member mem = members.get(m);
            System.out.printf("%-10s %-20s %-30s %-15s %-20s\n", mem.getid(),
                                                        mem.getName(),
                                                        mem.getAddress(),
                                                        mem.getPhone(),
                                                        mem.getUsername());
        }
    }

    public void listItems(HashMap<String, Item> items){
        System.out.println("\n");
        System.out.printf("%-10s %-30s %-10s %-15s\n","ID","Title","Price","Category");
        for (String i : items.keySet()){
            Item item = items.get(i);
            System.out.printf("%-10s %-30s %-10s %-15s\n",item.getID(),item.getTitle(),String.valueOf(item.getPrice()), item.getCategory());
        }
    }

    public void listOrders(HashMap<String, Order> orders){
        System.out.println("\n");
        System.out.printf("%-10s %-20s %-10s %-10s %-10s\n","ID","Title of Product","Quantity","Status","BuyerID");
        for (String o : orders.keySet()){
            Order order = orders.get(o);
            System.out.printf("%-10s %-20s %-10s %-10s %-10s\n",order.id, order.itemTitle,String.valueOf(order.itemAmount),order.status,order.buyerID);
        }
    }

    public void addItemToStore(String newItemTitle, Double newItemPrice, String newItemCategory, Integer itemsSize) throws Exception{
        FileOutputStream fos = new FileOutputStream("itemsData.txt",true);
        String data = "Hello World!";

        String[] mold = String.valueOf(Double.valueOf(itemsSize+1)/1000).split("");
        String id = "I";
        for (int i = 2; i < mold.length; i++){
            id = id.concat(mold[i]);
        }
        id = id.concat("-2022");
        if (itemsSize==0){
            data = id + "," + newItemTitle + "," + String.valueOf(newItemPrice) + "," + newItemCategory;
        } else {
            data = "\n"+ id + "," + newItemTitle + "," + String.valueOf(newItemPrice) + "," + newItemCategory;
        }
        fos.write(data.getBytes());
        fos.close();
    }

    public void printMemberOrders(String wantedMemberUsername, HashMap<String,Member> members, HashMap<String,Order> orders){
        System.out.println(wantedMemberUsername+ "\'s orders: ");
        System.out.printf("%-10s %-20s %-15s %-10s\n","ID","Title of Item","Quantity","Status");
        for (String o: orders.keySet()){
            if (orders.get(o).buyerID.equals(members.get(wantedMemberUsername).getid())){
                System.out.printf("%-10s %-20s %-15s %-10s\n",orders.get(o).id, orders.get(o).itemTitle,String.valueOf(orders.get(o).itemAmount),orders.get(o).status);
            }
        }
    }

    public void changeOrderStatus(String orderID, HashMap<String,Order> orders, String newStatus) throws Exception{
        for (String key: orders.keySet()){
            if (key.equals(orderID)){
                File oldFile = new File("OrdersData.txt");
                
                FileOutputStream fos = new FileOutputStream("tmp.txt",true);
                BufferedReader br = new BufferedReader(new FileReader("OrdersData.txt"));
                String line;
                int lines = 1;
                while ((line = br.readLine()) != null){
                    if (!line.startsWith(orderID)){
                        if (lines < orders.size()){
                            fos.write(new String(line+"\n").getBytes());
                            lines++;
                        }
                        else {
                            fos.write(new String(line).getBytes());
                            lines++;
                        }
                    } else {
                        if (lines < orders.size()){
                            fos.write(new String(orderID + "," + orders.get(orderID).itemTitle + "," + orders.get(orderID).itemAmount + "," + newStatus + "," +orders.get(orderID).buyerID+"\n").getBytes());
                            lines++;
                        } 
                        else {
                            fos.write(new String(orderID + "," + orders.get(orderID).itemTitle + "," + orders.get(orderID).itemAmount + "," + newStatus + "," +orders.get(orderID).buyerID).getBytes());
                            lines++;
                        }
                    }
                }
                fos.close();
                br.close();
                oldFile.delete();
                File newFile = new File("tmp.txt");
                newFile.renameTo(new File("OrdersData.txt"));
                break;
            }
        }
    }
}
