
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Creat Items

        Item i1 = new Item( "wood" , "raw" , 25 ,5000 , 1000); 
        Item i2 = new Item( "iron" , "raw" , 25 ,10000 , 1500);
        Item i3 = new Item("potato" , "dietetics" , 4 , 2000 , 500);
        Item i4 = new Item("fruits" ,"dietetics",6,500 ,100);
        Item i5 = new Item("oil","dietetics",2,3000 , 1000);
        Item i6 = new Item("flavoring","dietetics",1,300 , 100);
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

        //طاولة 

        List l3 = new ArrayList();
        l3.add(i1);
        l3.add(15);
        l3.add(i2);
        l3.add(10);
        Product p3 = new Product("table" , l3);

        //شيبس 
        
        List l4 = new ArrayList();
        l4.add(i3);
        l4.add((float)0.50);
        l4.add(i5);
        l4.add((float)0.1);
        l4.add(i6);
        l4.add((float)0.1);
        Product p4 = new Product("chips" , l3);

        //عصير
        List l5 = new ArrayList();
        l5.add(i4);
        l5.add((float)2);
        l5.add(i6);
        l5.add((float)0.25);
        Product p5 = new Product("juice" , l5);

        
        //creat ProductLine

        ProductLine pl1 = new ProductLine("furniture", "not activate");
        ProductLine pl2 = new ProductLine("furniture", "not activate");
        ProductLine pl3 = new ProductLine("furniture", "not activate");
        ProductLine pl4 = new ProductLine("dietetics", "not activate");
        ProductLine pl5 = new ProductLine("dietetics", "not activate");
        ProductLine pl6 = new ProductLine("dietetics", "not activate");
        
        //creat task

        // Task t1 = new Task(p1 , 777 , "hussien" , pl1);
        // Task t2 = new Task(p2 , 47 , "ahmad" , pl2);
        // Task t3 = new Task(p3 , 57 , "ali" , pl3);
        // Task t4 = new Task(p4 , 227 , "omar" , pl4);
        // Task t5 = new Task(p5 , 117 , "khalid" , pl5);
        // Task t6 = new Task(p5 , 227 , "mohummad" , pl6);

        










        


    }
}
