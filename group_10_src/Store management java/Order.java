public class Order {
    String id;
    String itemTitle;
    Integer itemAmount;
    String status;
    String buyerID;

    public Order(String id_,String itemTitle_, Integer itemAmount_, String status_, String buyerID_){
        this.id = id_;
        this.itemTitle = itemTitle_;
        this.itemAmount = itemAmount_;
        this.status = status_;
        this.buyerID = buyerID_;
    }
}
