public class Item {
    private String id;
    private String title;
    private Double price;
    private String category;


    public String getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Item(String id_, String title_, Double price_, String category_){
        this.id = id_;
        this.title = title_;
        this.price = price_;
        this.category = category_;
    }

    public String getCategory() {
        return category;
    }

    public void printItself(){
        System.out.print("\n");
        System.out.printf("%-15s %-30s %-10s %-20s",this.id,this.title,String.valueOf(this.price),this.category);
    }
}
