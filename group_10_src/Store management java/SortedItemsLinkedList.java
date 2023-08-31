public class SortedItemsLinkedList {
    public int size;
    public Node head;
    public Node tail;

    SortedItemsLinkedList(){
        this.size = 0;
        this.head = null;
        this.tail = null;
    }


    public void add(Item item){
        Node node = new Node(item);
        if (this.size == 0){
            this.head = node;
            this.tail = node;
            this.size++;
        } else if (this.size == 1) {
            if (node.getItem().getPrice()<this.head.getItem().getPrice()){
                this.head.prev = node;
                node.next = this.head; 
                this.head = node;
                this.size++;
            } 
            else {
                this.head.next = node;
                this.tail = node;
                node.prev = this.head;
                this.size++;
            }
        } else {    
            Node rover = this.head;
            if (node.getItem().getPrice()<rover.getItem().getPrice()){
                this.head.prev = node;
                node.next = this.head; 
                this.head = node;
                this.size++;
            } 
            else {
                while(rover.getItem().getPrice()<node.getItem().getPrice()){
                    if (rover == this.tail){
                        this.tail.next = node;
                        node.prev = this.tail;
                        this.tail = node;
                        this.tail.next = null;
                        this.size++;
                        break;
                    } 
                    else {
                        rover = rover.next;
                        if(rover.getItem().getPrice()>node.getItem().getPrice()){
                            node.prev = rover.prev;
                            rover.prev = node;
                            node.next = rover;
                            node.prev.next = node;
                            node.next = rover;
                            this.size++;
                            break;
                        }
                    } 
                }
            }
            
        }

    }

    public void empty(){
        if (this.head!= null){
            this.head.next = null;
            this.head = null;
            this.tail.prev = null;
            this.tail = null;
            this.size = 0;
        }
    }

    public void printItemInSortedItemsLinkedList(){  
        if (this.size <1){
            System.out.println("No item in list");
        } 
        else {   
            Node rover = this.head;      
            while (rover != null){
                rover.printItem();
                rover = rover.next;
            }
        }
    }

    public void printItemInSortedItemsLinkedListReversed(){
        if (this.size <1){
            System.out.println("No item in list");
        } 
        else {   
            Node rover = this.tail;      
            while (rover != null){
                rover.printItem();
                rover = rover.prev;
            }
        }
    }
}
