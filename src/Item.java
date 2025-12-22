
import java.util.ArrayList;
import java.util.List;

public class Item {
    static List <Product>pproduct = new ArrayList<>();
    static List products = new ArrayList();
    static List<Item> OItems = new ArrayList<>();
    private int itemId ;
    protected String itemName ;
    private String tybe ;
    private int price ;
    private int amount ;
    private float limit ;
    private String status;
    private static int counter=1 ;

    //constructor 
    public Item(String itemName, String tybe, int price, int amount, float limit) {
        this.itemName = itemName;
        this.tybe = tybe;
        this.price = price;
        this.amount = amount;
        this.limit = limit;
        this.itemId = counter ;
        counter++;
        OItems.add(this);
    }

    public void pullAmount(int amount){
        this.amount -= amount;

    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount ;
    }

    public String getItemName() {
        return itemName;
    }

    public String getTybe() {
        return tybe;
    }

    public float getLimit() {
        return limit;
    }

    public int getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setTybe(String tybe) {
        this.tybe = tybe;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    public String getStatus() {
        if (amount > limit ){
            status = "available";
        }else if(amount < limit && amount > 0){
            status = "below the minimum";
        }else if(amount==0){
            status ="sold out";
        }
        return status;
    }




   

      



    
}
