
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.IIOException;

public class ProductLine implements Runnable {

    Scanner in = new Scanner(System.in);
    static List<ProductLine> productLines = new ArrayList<>();
    private int lineNumber;
    private String LineName;
    private String ProductLinestatus;
    static int count = 1;

    Thread thread;

//constructer
    public ProductLine(String LineName, String ProductLinestatus) {
        this.ProductLinestatus = ProductLinestatus;
        this.LineName = LineName;
        thread = new Thread(this, LineName);
        lineNumber = count;
        count++;
        productLines.add(this);
    }

//creat and start
    private Product proudect;
    private int Amount;
    private String costumerN;

    public ProductLine startProductLine(String costumerName, Product wantedProduct, int wantedAmount) {
        Amount = wantedAmount;
        proudect = wantedProduct;
        costumerN = costumerName;
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
    public int getAmount() {
        return Amount;
    }

    public Product getproudect() {
        return proudect ;
    }

    public String getcostumerName() {
        return costumerN;
    }

    public int getDone (){
        return done ;
    }

    @Override
    public void run() {
        if (check(proudect, Amount, costumerN)) {
            pullAmount(proudect, Amount, costumerN);
            AddProudectTOStore(proudect, Amount);
        } else {
            SendNotification(proudect, Amount, costumerN);
        }

        System.out.println("done");

    }

    //يتفقد ما اذا كانت الموارد كافية
    public boolean check(Product Proudect, int Amount, String costumerName) {
        boolean b = true;
        for (int i = 0; i < Proudect.Items.size(); i += 2) {
            if (((Item) Proudect.Items.get(i)).getAmount() - ((int) Proudect.Items.get(i + 1) * Amount) > 0) {
                System.out.println("1i1 = " + i);
            } else {
                b = false;
            }
        }
        return b;
    }

    //يقوم بحجز الموارد اللازمة لصنع المنتجات
    public void pullAmount(Product Proudect, int Amount, String costumerName) {
        for (int i = 0; i < Proudect.Items.size(); i += 2) {
            int a;
            a = (int) Proudect.Items.get(i + 1) * Amount;
            ((Item) Proudect.Items.get(i)).pullAmount(a);
            System.out.println("2i2 = " + i);
        }
    }
    //تابع يرسل اشعار و يتعامل مع الموارد الغير كافية للمنتجات

    public void SendNotification(Product Proudect, int Amount, String costumerName) {
        System.out.println("the items not enough \n menu : \n 1.suspend order untel resources become available \n 2.cancel order \n (chose number)");
        int i = in.nextInt();
        if (i == 1) {
            System.out.println(costumerName + ", your order : " + Amount + " of " + Proudect.getProductName() + " ,has been pended untel resources become available .");
            boolean j = false;
            do {
                j = check(Proudect, Amount, costumerName);

                try {
                    thread.sleep(10000);
                } catch (InterruptedException ex) {
                    SendExMessage(ex);
                    System.out.println(ex);
                }
            } while (j == false);
            System.out.println(costumerName + ", your suspended order : " + Amount + " of " + Proudect.getProductName() + " has been completed");
            pullAmount(Proudect, Amount, costumerName);
            AddProudectTOStore(Proudect, Amount);
        } else {
            System.out.println("your order canceled");
        }

    }

    //تابع يضيف المنتجات المصنعة للمخزون و اذا كانت موجودة يزيد العدد
    
    int done=0;
    public void AddProudectTOStore(Product product, int Amount) {
        int i;
        boolean b = false;
        for (i = 0; i < Item.products.size(); i += 2) {
            if (Item.products.get(i).equals(product.getProductName())) {
                b = true;
                return;
            }
            if (b == true) {
                for (int j = 0; j <= Amount; j++) {
                    try {
                        thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                        SendExMessage(ex);

                    }
                    Item.products.add(i, 1);
                }
            } else {
                for (done = 1; done <= Amount; done++) {
                    try {                       
                        thread.sleep(5000);
                        Item.products.add(Item.products.size() + 1, 1);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                        SendExMessage(ex);
                    }
                }
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
                for (int i = 0; i < Item.products.size(); i++) {
                    pr.print("Product Name :" + Item.products.get(i) + " , Amount :" + Item.products.get(i + 1));
                    pr.flush();}
                    
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

    //تابع يقرا حالة المخزون من ملف نصي عند تشغيل البرنامج
    public void ReadStoreMessage() {

    }

}
