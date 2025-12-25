
import com.sun.source.doctree.ErroneousTree;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import javax.imageio.IIOException;

public class ProductLine implements Runnable {

    Scanner in = new Scanner(System.in);
    static List<ProductLine> productLines = new ArrayList<>();
    static List<String> deferentproductLines = new ArrayList<>();

    private int lineNumber;
    private String LineName;
    private String ProductLinestatus;
    static int count = 1;

    Thread thread;

//constructer
    public ProductLine(String LineName) {
        ProductLinestatus = "not avtivate";
        this.LineName = LineName;
        thread = new Thread(this, LineName);
        lineNumber = count;
        count++;


        productLines.add(this);
        boolean b = true;
        for (int i = 0; i < deferentproductLines.size(); i++) {
            if (deferentproductLines.get(i).equals(LineName)) {
                b = false;
            }

        }
        if (b == true) {
            deferentproductLines.add(LineName);
        }
    }

//creat and start

    Task task;

    public ProductLine startProductLine(String costumerName, Product wantedProduct, int wantedAmount, Task task) {
        this.task = task;
        setProductLinestatus("under implementation");
        this.thread.start();
        return this;
    }

    //setters
    public void setLineName(String LineName) {
        this.LineName = LineName;
    }

    public void setProductLinestatus(String ProductLinestatus) {
        this.ProductLinestatus = ProductLinestatus;
    }

    //getters
    public int getLineNumber() {
        return lineNumber;
    }

    public String getLineName() {
        return LineName;
    }

    public String getProductLinestatus() {
        return ProductLinestatus;
    }

    //
   

    public int getDone() {
        return task.getDone();
    }

    @Override
    public void run() {
      check(task.getWantedProduct() , task.getWantedAmount() , task.getCostumerName());


        System.out.println("done");

    }

    //يتفقد ما اذا كانت الموارد كافية
    public synchronized void check (Product Proudect, int Amount, String costumerName) {
        boolean b = true;
        for (int i = 0; i < Proudect.Items.size(); i += 2) {
            if (((Item) Proudect.Items.get(i)).getAmount() - ((int) Proudect.Items.get(i + 1) * Amount) < 0) {
                b =false;
            }
        }
        if (b =true){
            pullAmount(Proudect, Amount);
        }else{
            SendNotification(Proudect, Amount, costumerName , b);
        }

        
    }

    //يقوم بحجز الموارد اللازمة لصنع المنتجات
    public synchronized  void pullAmount(Product Proudect, int Amount) {
        for (int i = 0; i < Proudect.Items.size(); i += 2) {
            int a;
            a = (int) Proudect.Items.get(i + 1) * Amount;
            ((Item) Proudect.Items.get(i)).pullAmount(a);
        }
        AddProudectTOStore(Proudect, Amount);
    }

    //تابع يرسل اشعار و يتعامل مع الموارد الغير كافية للمنتجات
    public void SendNotification(Product Proudect, int Amount, String costumerName , boolean check) {
        System.out.println("the items not enough \n menu : \n 1.suspend order untel resources become available \n 2.cancel order \n (chose number)");
        int i = in.nextInt();
        if (i == 1) {
            System.out.println(costumerName + ", your order : " + Amount + " of " + Proudect.getProductName() + " ,has been pended untel resources become available .");
            boolean j = false;
            do {
                j = check;

                try {
                    thread.sleep(10000);
                } catch (InterruptedException ex) {
                    SendExMessage(ex);
                    System.out.println(ex);
                }
            } while (j == false);
            System.out.println(costumerName + ", your suspended order : " + Amount + " of " + Proudect.getProductName() + " has been completed");
            pullAmount(Proudect, Amount);
        } else {
            System.out.println("your order canceled");
        }

    }

    //تابع يضيف المنتجات المصنعة للمخزون و اذا كانت موجودة يزيد العدد
    public void AddProudectTOStore(Product product, int Amount) {
        boolean b = false;
        for (int i = 0; i < Item.products_in_Storage.size(); i++) {
            if (Item.products_in_Storage.get(i).equals(product)) {
                b = true;
            }
        }
        if (b == false) {
            Item.products_in_Storage.add(product);
        }

        for (int i = 1; i <= Amount; i++) {
            try {
                thread.sleep(5000);
                product.setProduct_Amount_In_Stordge(i);
                task.setDone(i);
                if (i == Amount) {
                    setProductLinestatus("finished");

                }
            } catch (InterruptedException ex) {
                System.out.println(ex);
                SendExMessage(ex);
            }
        }
    }
    //تابع بسجل الاستثناءات الى ملف error.txt

    public void SendExMessage(Exception e) {
        try {
            FileWriter fw = new FileWriter("error.txt", true);
            PrintWriter pr = new PrintWriter(fw);
            pr.print(e);
            pr.flush();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            SendExMessage(ex);
        } catch (IOException ex) {
            System.out.println(ex);
            SendExMessage(ex);

        }
    }

    //تابع يسجل حالة المخزون بشكل يومي الى ملف نصي
    public void SendStoreMessage() {
        try {
            File f = new File("store.txt");
            PrintWriter pr = new PrintWriter(f);
            while (true) {
                for (int i = 0; i < Item.OItems.size(); i++) {
                    pr.print("Item Name :" + Item.OItems.get(i).itemName + " , Amount :" + Item.OItems.get(i).getAmount());
                    pr.flush();
                }
                for (int i = 0; i < Item.products_in_Storage.size(); i ++) {
                    pr.print("Product Name :" + Item.products_in_Storage.get(i) + " , Amount :" + Item.products_in_Storage.get(i).getProduct_Amount_In_Stordge());
                    pr.flush();
                }

                try {
                    thread.sleep(86400000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                    SendExMessage(e);
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            SendExMessage(ex);
        } catch (IOException ex) {
            System.out.println(ex);
            SendExMessage(ex);

        }
    }

    //تابع يقرا حالة المخزون من ملف نصي عند تشغيل 
    public void ReadStoreMessage() {
        File file = new File("store.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            SendExMessage(ex);
        } catch (IOException e) {
            System.out.println(e);
            SendExMessage(e);
        }

    }

}
