
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Creat Items

        Item i1 = new Item( "wood" , "raw" , 25 ,2000 , 500); 
        Item i2 = new Item( "iron" , "raw" , 25 ,5000 , 1500); 
      
        //Creat Product
      
        //مكتب

        List l1 = new ArrayList<>();
        l1.add(i1);
        l1.add(23);
        l1.add(i2);
        l1.add(36);
        Product p1 = new Product("desk" , l1 ) ;
       
        //خزانة 

        List l2 = new ArrayList<>();
        l2.add(i1);
        l2.add(42);
        Product p2 = new Product("cupboard" , l2);

        
        //creat ProductLine
        ProductLine pl1 = new ProductLine("furniture", "not activate");
        ProductLine pl2 = new ProductLine("dietetics", "not activate");
        
        //creat task
        
        Task t1 = new Task(p1 , 77 , "eee" , pl1);
System.out.println("i1 " + i1.getAmount());
System.out.println("i2 = "+i2.getAmount()); 
System.out.println("i1 " + i1.getAmount());
System.out.println("i2 = "+i2.getAmount()); 
System.out.println("i1 " + i1.getAmount());
System.out.println("i2 = "+i2.getAmount()); 
System.out.println("i1 " + i1.getAmount());
System.out.println("i2 = "+i2.getAmount());


    }
}
