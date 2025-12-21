
import java.util.ArrayList;
import java.util.List;

public class Item {
    static List products = new ArrayList();
    static List<Item> OItems = new ArrayList<>();
    private int itemId ;
    protected String itemName ;
    private String tybe ;
    private int price ;
    private int amount ;
    private float limit ;
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
        products.add(itemName);
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



   

      



    
}
