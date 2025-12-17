public class Item {
    private int itemId ;
    private String itemName ;
    private String tybe ;
    private int price ;
    private float amount ;
    private float limit ;
    private static int counter=1 ;

    //constructor 
    public Item(String itemName, String tybe, int price, float amount, float limit) {
        this.itemName = itemName;
        this.tybe = tybe;
        this.price = price;
        this.amount = amount;
        this.limit = limit;
        this.itemId = counter ;
        counter++;

    }

    
}
