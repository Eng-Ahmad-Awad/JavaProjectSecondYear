
import java.util.ArrayList;
import java.util.List;



public class Product {
    private int productId ;
    private String productName ;
    protected static List<Product> OProducts = new ArrayList<>();
    List Items = new ArrayList();
    private static int counter = 1 ;
    private int product_Amount_In_Stordge;

    //constructor
    
    public Product() {
    }
    
    public Product(String productName, List Items ) {
        this.productName = productName;
        this.Items = Items;
        counter++ ;
        this.productId = counter ;
        OProducts.add(this);
    }

    public String getProductName() {
        return productName;
    }

    public void setProduct_Amount_In_Stordge(int product_Amount_In_Stordge) {
        this.product_Amount_In_Stordge = product_Amount_In_Stordge;
    }

    public int getProduct_Amount_In_Stordge() {
        return product_Amount_In_Stordge;
    }

    

    


}
