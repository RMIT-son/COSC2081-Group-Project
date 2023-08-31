import java.util.*;
import java.io.*;

public class filesManager {
    public HashMap<String,Member> readMembers() throws Exception{
        String line;
        HashMap<String,Member> members = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("customersData.txt"));

        while ((line = br.readLine()) != null) {
            members.put(line.split(",")[5].trim(),new Member(line.split(",")[0].trim(),
                                                                    line.split(",")[1].trim(),
                                                                    line.split(",")[2].trim(),
                                                                    line.split(",")[3].trim(),
                                                                    line.split(",")[4].trim(),
                                                                    line.split(",")[5].trim(),
                                                                    line.split(",")[6].trim()));
        }
        br.close();
        return members;
    }

    public HashMap<String,Item> readItems() throws FileNotFoundException {
        HashMap<String,Item> items = new HashMap<>();
        Scanner iteminput = new Scanner(new File("itemsData.txt"));
        while (iteminput.hasNextLine()){
            String line=iteminput.nextLine();
            items.put(line.split(",")[1].trim(), new Item(line.split(",")[0].trim(),
                                                                 line.split(",")[1].trim(),
                                                                 Double.valueOf(line.split(",")[2].trim()),
                                                                 line.split(",")[3].trim()));
        }
        iteminput.close();
        return items;
    }

    public HashMap<String,Order> readOrders() throws FileNotFoundException{
        HashMap<String,Order> orders = new HashMap<>();
        Scanner ordersInput = new Scanner(new File("OrdersData.txt"));
        while(ordersInput.hasNextLine()){
            String line = ordersInput.nextLine();
            orders.put(line.split(",")[0].trim(),new Order(line.split(",")[0].trim(),
                                                                  line.split(",")[1].trim(),
                                                                  Integer.valueOf(line.split(",")[2].trim()),
                                                                  line.split(",")[3].trim(),
                                                                  line.split(",")[4].trim()));
        }
        ordersInput.close();
        return orders;
    }
}
