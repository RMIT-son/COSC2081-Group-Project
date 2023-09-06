import java.util.*;
import java.io.*;

public class Customer {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String customerType;
    private String username;
    private String password;
    // private Boolean isAdmin = false;

    public void setid(String id_){
        this.id = id_;
    }
    public String getid(){
        return this.id;
    }

    public void setName(String name_){
        this.name = name_;
    }
    public String getName(){
        return this.name;
    }

    public void setAddress(String address_){
        this.address = address_;
    }
    public String getAddress(){
        return this.address;
    }

    public void setPhone (String phone_){
        this.phone = phone_;
    }
    public String getPhone(){
        return this.phone;
    }

    public void setCustomerType (String customerType_){
        this.customerType = customerType_;
    }
    public String getCustomerType(){
        return this.customerType;
    }

    public void setUsername(String un) {
        this.username = un;
    }
    public void setPassword(String pw) {
        this.password = pw;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

    public int register (String name_, String address_, String phone_, String customerType_, String username_, String password_,int membersCount) throws Exception{
        FileOutputStream fos = new FileOutputStream("customersData.txt",true);
        String data = "Hello World!";

        String[] mold = String.valueOf(Double.valueOf(membersCount+1)/1000).split("");
        String id = "C";
        for (int i = 2; i < mold.length; i++){
            id = id.concat(mold[i]);
        }

        if (membersCount==0){
            data = id + "," + name + "," + address_ + "," + phone_ + "," + customerType_ + "," + username_ + "," + password_;
        } else {
            data = "\n"+id+ "," + name + "," + address_ + "," + phone_ + "," + customerType_ + "," + username_ + "," + password_;
        }
        byte[] datab = data.getBytes();
        fos.write(datab);
        fos.close();
        return -1;
    }

    public void displayMenu(){
        System.out.println("Enter:");
        System.out.println("");
    }


    public void listItems(HashMap<String,Item> items){
        System.out.printf("\n\n%-15s %-30s %-10s %-20s","ID","Title","Price","Category");
        for (String key : items.keySet()){
            items.get(key).printItself();
        }
        System.out.println("\n");
    }

    public int listItemsInCategory(HashMap<String,Item> items,String input){
        Boolean hasCategory = false;
        for (String key : items.keySet()){
            if (input.equals(items.get(key).getCategory().toLowerCase())){
                hasCategory = true;
                break;
            }
        }
        if (!hasCategory){
            System.out.println("No such category found! Try another category.");
            return 0;
        } else {
            System.out.printf("\n\n%-15s %-30s %-10s %-20s","ID","Title","Price","Category");
            for (String key : items.keySet()){
                if (input.equals(items.get(key).getCategory().toLowerCase())){
                    items.get(key).printItself();
                }
            }
            return 1;
        }
    }

    public HashMap<String,Item> wantedItemsInCategory(HashMap<String,Item> items, String input){
        HashMap<String,Item> wantedItemsInCategory = new HashMap<>();
        for (String key : items.keySet()){
            if (input.equals(items.get(key).getCategory().toLowerCase())){
                wantedItemsInCategory.put(input,items.get(key));
            }
        }
        return wantedItemsInCategory;
    }

    public void listItemsAscendingPrice(SortedItemsLinkedList sill, HashMap<String,Item> items){
        System.out.printf("\n\n%-15s %-30s %-10s %-20s","ID","Title","Price","Category");
        for (String key: items.keySet()){
            sill.add(items.get(key));
        }
        sill.printItemInSortedItemsLinkedList();
    }

    public void listItemsDescendingPrice(SortedItemsLinkedList sill, HashMap<String,Item> items){
        System.out.printf("\n\n%-15s %-30s %-10s %-20s","ID","Title","Price","Category");
        for (String key: items.keySet()){
            sill.add(items.get(key));
        }
        sill.printItemInSortedItemsLinkedListReversed();
    }
}
