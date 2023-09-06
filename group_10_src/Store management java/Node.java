public class Node {
    private Item item;
    public Node next;
    public Node prev;

    public Node(Item item_){
        this.item = item_;
        this.next = null;
        this.prev = null;
    }

    public Item getItem(){
        return this.item;
    }

    public void printItem(){
        this.item.printItself();
    }
}
