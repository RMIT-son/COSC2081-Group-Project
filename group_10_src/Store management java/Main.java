import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        //Read in data from membersData.txt
        filesManager fm = new filesManager();
        HashMap<String,Member> members = fm.readMembers();
        HashMap<String,Item> items = fm.readItems();
        SortedItemsLinkedList sill = new SortedItemsLinkedList();
        HashMap<String,Order> orders = fm.readOrders();
        
        // initiate scanner varible
        int scvar1 = 0;
        int scvar2 = 0;
        int scvar3 = 0;
        int scvar4 = 0;
        
        //initiate the i/o 
        Scanner sc = new Scanner(System.in);

        //switch on k
        int k = 0;
        /*
        k = 0 : Login menu
        k = 1 : Customer menu 
        k = 2 : Admin menu
        k = 3 : Register 
        k = 4 : Log in as Member
        k = 5 : Log in as Admin
        k = 6 : Customer menu
        k = 7 : Customer List Items + menu for sorting
        k = 8 : Getting wanted Category items
        k = 9 : Customer Ascending List
        k = 10: Customer Descending List
        k = 11: Member menu
        k = 12: Member List wanted Category items
        k = 13: Member Ascending List of Wanted Items
        k = 14: Member Descending List of Wanted Items
        
        k = 16: Admin Menu


        k = 99: Testing
        */
        
        //initiate different types of current user
        Customer customer = new Customer();
        Member currentMember = new Member("id", "name", "address", "phone", "customerType", "username", "password");
        Admin admin = new Admin("ADMIN","Admin","Admin's house","123456789","NotCustomer","Admin","Supr3m3admin");
        
        System.out.println("\n");
        System.out.println("*******************************");
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("STORE ORDER MANAGEMENT SYSTEM");
        System.out.println("");
        System.out.println("Instructor: Prof. Minh Vu, Prof. Ushik Shrestha Khwakhali");
        System.out.println("");
        System.out.println("Group 10");
        System.out.println("s3926953 : Doan Duy Bach");
        System.out.println("s3927233 : Duong");
        System.out.println("s3927205 : Le Ngoc Hieu");
        System.out.println("s3926387 : Nguyen Cong Thinh");
        System.out.println("*******************************");

        int program_run = 1;
        while (program_run != 0) {
            if (k == 0) {                                                       
                int input = -2;
                int exit = 0;
                printCustomerChoices();

                while (exit != 1){
                    if (!sc.hasNextInt()){
                        while (!sc.hasNextInt()){
                            printCustomerChoices();
                            sc.nextLine();
                        }
                    } else {
                        input = sc.nextInt();
                        if (Arrays.binarySearch(new Integer[]{0,1,2,3,4,5}, input)<0){
                            printCustomerChoices();
                            sc.nextLine();
                        } else if (input==0) {
                            System.out.println("System exiting");
                            System.exit(1);
                        } else if (input == 1) {
                            k = 3;
                            exit = 1;
                        } else if (input==2) {
                            k = 4;
                            exit = 1;
                        } else if (input == 3) {
                            k = 5;
                            exit = 1;
                        } else if (input == 4) {
                            k = 7;
                            exit = 1;
                        } else if (input == 5){
                            k = 8;
                            exit = 1;
                        }
                    }
                }
            } 
            else if (k==3) {
                sc.nextLine();
                System.out.println("Enter your username for registration: ");
                String username = sc.nextLine();
                while (members.containsKey(username)){
                    System.out.println("Username taken. Enter another username: ");
                    username = sc.nextLine();
                }
                System.out.println("Enter your password for registration: ");
                String password = sc.nextLine();
                System.out.println("Enter your full name for registration: ");
                String name = sc.nextLine();
                System.out.println("Enter your address: ");
                String address = sc.nextLine();
                System.out.println("Enter your phone number for registration: ");
                String phone = sc.nextLine();

                customer.register(name, address,phone,"Regular",username,password, members.size());
                members = fm.readMembers();
                k = 11;  
            } 
            else if (k==4 && members.size() == 0){
                System.out.println("There is no user in database. Please register: ");
                k = 3;
            } 
            else if (k==4){
                System.out.println("Enter your username: ");
                if (scvar1==0){
                    sc.nextLine();
                    scvar1++;
                }
                String username = sc.nextLine();

                while (!members.containsKey(username)){
                    System.out.println("Invalid username");
                    System.out.println("Enter valid username: ");
                    username = sc.nextLine();
                } 
                int triedTimes = 0;
                System.out.println("Enter your password: ");
                String password = sc.nextLine();
                while (!password.equals(members.get(username).getPassword())){
                    if (triedTimes==2){
                        System.out.println("You have tried too many times. System exiting");
                        System.exit(1);
                    } else {
                        triedTimes++;
                        System.out.println("Wrong password. You have tried "+triedTimes+" times. Re-enter password: ");
                        password = sc.nextLine();
                    }
                }
                System.out.println("\n\nWelcome member "+username+" !!\n");
                currentMember.setid(members.get(username).getid());
                currentMember.setName(members.get(username).getName());
                currentMember.setAddress(members.get(username).getAddress());
                currentMember.setPhone(members.get(username).getPhone());
                currentMember.setCustomerType(members.get(username).getCustomerType());
                currentMember.setUsername(username);
                currentMember.setPassword(password);
                k = 11;
            } 
            else if (k==5){
                int exit = 0;
                int triedTimes = 0;
                sc.nextLine();

                while (exit != 1) {
                    System.out.println("Enter Admin username: ");
                    String aUsername = sc.nextLine();
                    System.out.println("Enter Admin password: ");
                    String aPassword = sc.nextLine();
                    if (!aUsername.equals(admin.getUsername()) || !aPassword.equals(admin.getPassword())){
                        System.out.println("Wrong username or password!!");
                        if (triedTimes<3){
                            triedTimes += 1;
                            System.out.println("You have "+(2-triedTimes)+" chances left to log in ");
                        } else {
                            System.out.println("System exiting for too many failed attempts to log in as Admin");
                            System.exit(1);
                        }
                    } else {
                        System.out.println("Welcome Admin!");
                        k = 16;
                        exit = 1;
                    }
                }
            } 
            else if (k == 6){
                customer.displayMenu();
                Integer option = sc.nextInt();
                while (option != 0) {
                    System.out.println("Enter valid option");
                    option = sc.nextInt();
                }
            } 
            else if (k==7){
                customer.listItems(items);
                printSortChoices();
                Integer input = sc.nextInt();
                if (input == 0){
                    k = 0;
                    printCustomerChoices();
                }
                else if (input == 1) {
                    k = 9;
                } 
                else {
                    k = 10;
                }
            } 
            else if (k==8){
                System.out.print("Enter your wanted category: ");
                if (scvar2 == 0){
                    sc.nextLine();
                    scvar2 = 1;
                }
                String input = sc.nextLine().toLowerCase();
                int result = customer.listItemsInCategory(items, input);
                if (result == 0){
                    k = 8;
                }
                else{
                    program_run = 0;
                }
            }
            else if (k==9){
                customer.listItemsAscendingPrice(sill, items);
                printSortChoices();
                Integer input = sc.nextInt();
                if (input == 0){
                    k = 0;
                    printCustomerChoices();
                }
                else if (input == 1) {
                    k = 9;
                } 
                else {
                    k = 10;
                }
            }
            else if (k==10){
                customer.listItemsDescendingPrice(sill, items);
                printSortChoices();
                Integer input = sc.nextInt();
                if (input == 0){
                    k = 0;
                    printCustomerChoices();
                }
                else if (input == 1) {
                    k = 9;
                } 
                else {
                    k = 10;
                }
            }
            else if (k==11) {
                printMemberChoices();
                Integer input = sc.nextInt();
                if (input==0){
                    scvar1 = 0;
                    scvar2 = 0;
                    scvar3 = 0;
                    scvar4 = 0;
                    currentMember.setid("id");
                    currentMember.setName("Name");
                    currentMember.setAddress("Address");
                    currentMember.setPhone("Phone");
                    currentMember.setCustomerType("customerType");
                    currentMember.setUsername("username");
                    currentMember.setPassword("password");
                    currentMember.myOrders.clear();
                    k = 0;
                }
                else if(input==1){
                    currentMember.listItems(items);
                    printSortChoices();
                    Integer input1 = sc.nextInt();
                    if (input1 == 0){
                        k = 11;
                    }
                    else if (input1 == 1) {
                        k = 9;
                    } 
                    else {
                        k = 10;
                    }
                }
                else if(input==2){
                    k = 15;
                }
                else if(input==3){
                    if(scvar3==0){
                        sc.nextLine();
                        scvar3++;
                    }
                    System.out.println("Enter your wanted item to make an Order: ");
                    String wantedItemTitle = sc.nextLine();
                    System.out.println("Enter the quantity the item you need: ");
                    Integer itemAmount = sc.nextInt();  
                    Order order = currentMember.createOrder(wantedItemTitle, itemAmount, orders.size(), items);
                    admin.addToRevenue(order,items);
                    orders = fm.readOrders();
                }
                else if(input==4){
                    currentMember.listMyOrders();
                } 
                else{
                    System.out.println("Invalid Input");
                    System.exit(1);
                }
            }
            else if (k==12){
                System.out.print("Enter your wanted category: ");
                if (scvar2 == 0){
                    sc.nextLine();
                    scvar2 = 1;
                }
                String input = sc.nextLine().toLowerCase();
                int result = currentMember.listItemsInCategory(items, input);
                if (result == 0){
                    k = 11;
                }
                else{
                    k = 11;
                }
                k = 11;

            }
            else if (k==15){
                System.out.print("Enter your wanted category: ");
                if (scvar4 == 0){
                    sc.nextLine();
                    scvar4 = 1;
                }
                String input2 = sc.nextLine().toLowerCase();
                int result = customer.listItemsInCategory(items, input2);
                if (result == 0){
                    k = 12;
                }
                else{
                    printSortChoices();
                    Integer input3 = sc.nextInt();
                    if (input3 == 0){
                        k = 11;
                    }
                    else if(input3==1){
                        sill.empty();
                        currentMember.listItemsAscendingPrice(sill, currentMember.wantedItemsInCategory(items,input2));
                        k=11;
                    } else if (input3==2){
                        sill.empty();
                        currentMember.listItemsDescendingPrice(sill, currentMember.wantedItemsInCategory(items,input2));
                        k=11;
                    }
                }
            }
            else if (k == 16){
                printAdminChoices();
                Integer input = sc.nextInt();
                if (input == 0){
                    k = 0;
                }
                else if (input==1){
                    admin.listItems(items);
                }
                else if (input==2){
                    System.out.println("Enter your wanted category");
                    String input1 = sc.nextLine();
                    admin.listItemsInCategory(items, input1);
                }
                else if (input==3){
                    admin.listMembers(members);
                }
                else if (input==4){
                    admin.listItems(items);
                }
                else if (input==5){
                    admin.listOrders(orders);
                }
                else if (input==6){
                    sc.nextLine();
                    System.out.println("Enter item's title: ");
                    String newItemTitle = sc.nextLine();
                    if (items.keySet().contains(newItemTitle)){
                        System.out.println("This Item already exists");
                    }
                    else {
                        System.out.println("Enter price: ");
                        Double newItemPrice = sc.nextDouble();
                        System.out.println("Enter category: ");
                        sc.nextLine();
                        String newItemCategory = sc.nextLine();
                        admin.addItemToStore(newItemTitle,newItemPrice,newItemCategory,items.size());
                        items = fm.readItems();
                    }
                }
                else if (input==7){
                    sc.nextLine();
                    System.out.println("Enter Member's username: ");
                    String wantedMemberUsername = sc.nextLine();
                    admin.printMemberOrders(wantedMemberUsername, members,orders);
                } 
                else if (input==8){
                    sc.nextLine();
                    System.out.println("Enter ID of the order you want to change status: ");
                    String orderID = sc.nextLine();
                    System.out.println("Enter new status: ");
                    String newStatus = sc.nextLine();
                    admin.changeOrderStatus(orderID,orders,newStatus);
                    orders = fm.readOrders();
                }
            }
            else if(k==99){ //Modular Testing

            }
        }
        
        //closing i/o
        sc.close();
    }
    // static methods to print out menus 
    public static void printCustomerChoices(){
        System.out.println("\n\nEnter 0, 1, 2, 3, or 4: ");
        System.out.println("0: Exit");
        System.out.println("1: Register");
        System.out.println("2: Log in as Member");
        System.out.println("3: Log in as Admin");
        System.out.println("4: List all products");
        System.out.println("5: List all products in a category");
        System.out.print("Input: ");
    }

    public static void printSortChoices(){
        System.out.println("\n\nEnter 0, 1, or 2");
        System.out.println("0: Back");
        System.out.println("1: List these products in Ascending Order");
        System.out.println("2: List these products in Descending Order");
        System.out.print("Input: ");
    }

    public static void printMemberChoices(){
        System.out.println("\n\nEnter 0,1,2,");
        System.out.println("0: Log out");
        System.out.println("1: List all products");
        System.out.println("2: List all products in category");
        System.out.println("3: Make an order");
        System.out.println("4: See my order");
        System.out.print("Input: ");
    }

    public static void printAdminChoices(){
        System.out.println("\n\nEnter 0,1,2,");
        System.out.println("0: Log out");
        System.out.println("1: List all products");
        System.out.println("2: List all products in category");
        System.out.println("3: View all members");
        System.out.println("4: View all items");
        System.out.println("5: View all orders");
        System.out.println("6: Add new item to Store");
        System.out.println("7: View a member's orders");
        System.out.println("8: Change status of the order");
        System.out.print("Input: ");
    }
}
