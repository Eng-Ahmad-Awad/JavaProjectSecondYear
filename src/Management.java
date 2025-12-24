
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.SourceDataLine;

public class Management {

    Scanner in = new Scanner(System.in);

    //توابع المدير

    //اضافة خط انتاج جديد
    
    public void Add_ProductLine(String LineName, String ProductLinestatus) {
        ProductLine p = new ProductLine(LineName, ProductLinestatus);
        ProductLine.productLines.add(p);

    }

    //التعديل على خط انتاج
    public void ProductLine_editer1() {
        System.out.println("chose the Product line that you want to edit :");
        int i;
        for (i = 0; i < ProductLine.productLines.size(); i++) {
            System.out.println(i + 1 + "." + ProductLine.productLines.get(i).getLineName() + " , status : " + ProductLine.productLines.get(i).getProductLinestatus());
        }
        System.out.println("enter the new name :");
        String NewName = in.nextLine();
        System.out.println("enter the new satus :");
        String NewStatus = in.nextLine();
        ProductLine_editer2(ProductLine.productLines.get(i), NewName, NewStatus);
    }

    public void ProductLine_editer2(ProductLine p, String LineName, String ProductLinestatus) {
        p.setLineName(LineName);
        p.setProductLinestatus(ProductLinestatus);
    }

    //عرض اداء خطوط الانتاج//صيانة

    public void View_ProductLine_Performance() {
        System.out.println("the product lines :");
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            System.out.println(i + 1 + ". " + ProductLine.deferentproductLines.get(i));
        }
        System.out.println("The Product Line currently in operation :");
        boolean bool = false;
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            for (int j = 0; j < ProductLine.productLines.size(); j++) {
                if (ProductLine.productLines.get(j).thread.isAlive() && ProductLine.productLines.get(j).getLineName().equals(ProductLine.deferentproductLines)) {
                    bool = true;
                }
            }
            if (bool == true) {
                System.out.println(i + 1 + ". " + ProductLine.deferentproductLines.get(i));
            }
        }
        int wantedAmount = 0;
        String wantedProduct = null;
        String costumerName = null;

        System.out.print("chose product line number : ");
        int a = in.nextInt();
        System.out.println("the tasks in this product line :");
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName() == ProductLine.deferentproductLines.get(a - 1)) {
                System.out.println(i + 1 + ". " + ProductLine.productLines.get(i).task.getWantedAmount() + " " + ProductLine.productLines.get(i).task.getWantedProduct().getProductName() + " for : " + ProductLine.productLines.get(i).task.getCostumerName());
            }
        }
        System.out.println("chose task number : ");
        int b = in.nextInt();
        int x = 0;
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName() == ProductLine.deferentproductLines.get(a - 1)) {
                x++;
            }
            if (x == b) {
                wantedAmount = ProductLine.productLines.get(i).task.getWantedAmount();
                wantedProduct = ProductLine.productLines.get(i).task.getWantedProduct().getProductName();
                costumerName = ProductLine.productLines.get(i).task.getCostumerName();
            }
        }

        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a - 1)) && ProductLine.productLines.get(i).task.getWantedAmount() == wantedAmount && ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(wantedProduct) && ProductLine.productLines.get(i).task.getCostumerName().equals(costumerName)) {
                int lift = ProductLine.productLines.get(i).task.getWantedAmount() - ProductLine.productLines.get(i).getDone();
                System.out.println(ProductLine.productLines.get(i).task.getDone() + ProductLine.productLines.get(i).getproudect().getProductName() + "have been made ," + lift + " lift");
                int percentage = (ProductLine.productLines.get(i).getAmount() / ProductLine.productLines.get(i).getDone()) * 100;
                System.out.println(percentage + " completed .");
                System.out.println("the estimated completion time is : " + (lift * 5) / 60 + " minute and : " + (lift * 5) % 60 + " seconds .");
                System.out.println("do you want to add comment to this product line \n 1.yes 2.no \n (chose number)");
                int c = in.nextInt();
                if (c == 1) {
                    try {
                        FileWriter fr = new FileWriter("notes.txt", true);
                        PrintWriter pr = new PrintWriter(fr);
                        String s1 = "notes for" + ProductLine.productLines.get(a).getLineName() + "product line :";
                        String s2 = in.nextLine();
                        pr.print(s1);
                        pr.print(s2);
                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                        ProductLine.productLines.get(0).SendExMessage(e);
                    } catch (IOException e) {
                        ProductLine.productLines.get(0).SendExMessage(e);

                    }
                }
            }
        }

    }

    //توابع مشرف الانتاج
    //اضافة عناصر 
    public void Add_Item(String itemName, String tybe, int price, int amount, float limit) {
        Item i = new Item(itemName, tybe, price, amount, limit);
    }

    //عرض عناصر 
    public void View_Item() {
        System.out.println("the items : ");
        for (int i = 0; i < Item.OItems.size(); i++) {

            System.out.println(i + 1 + ". " + Item.OItems.get(i).getItemName() + " , the amount :" + Item.OItems.get(i).getAmount() + " ,the type : " + Item.OItems.get(i).getTybe() + " ,the price : " + Item.OItems.get(i).getPrice() + " ,the limit : " + Item.OItems.get(i).getLimit());
        }
    }

    //تعديل عناصر 
    public void Edit_Item() {
        View_Item();
        System.out.println("chose the item that you want to edit(chose number) .");
        int c = in.nextInt();
        String oldName = Item.OItems.get(c - 1).getItemName();
        String oldType = Item.OItems.get(c - 1).getTybe();
        int oldPrice = Item.OItems.get(c - 1).getPrice();
        int oldAmount = Item.OItems.get(c - 1).getAmount();
        float oldLimit = Item.OItems.get(c - 1).getLimit();

        System.out.print("enter new name : ");
        String newName = in.nextLine();
        Item.OItems.get(c).setItemName(newName);

        System.out.print("enter new type : ");
        String newType = in.nextLine();
        Item.OItems.get(c).setTybe(newType);

        System.out.print("enter new price : ");
        int newPrice = in.nextInt();
        Item.OItems.get(c).setPrice(newPrice);

        System.out.print("enter new amount : ");
        int newAmount = in.nextInt();
        Item.OItems.get(c).setAmount(newAmount);

        System.out.println("enter new limit : ");
        float newLimit = in.nextFloat();
        Item.OItems.get(c).setLimit(newLimit);

    }

    //حذف عناصر
    public void Delete_Item() {
        View_Item();
        System.out.println("chose the item that you want to delete (chose number) .");
        int c = in.nextInt();
        Item.OItems.remove(c - 1);
    }

    //بحث و تصفية للعناصر حسب الاسم
    public void Searsh_Name_Item(String name) {
        boolean b = false;
        for (int i = 0; i < Item.OItems.size(); i++) {
            if (Item.OItems.get(i).getItemName().equals(name)) {
                System.out.println("Item : " + Item.OItems.get(i).getItemName() + " , the amount :" + Item.OItems.get(i).getAmount() + " ,the type : " + Item.OItems.get(i).getTybe() + " ,the price : " + Item.OItems.get(i).getPrice() + " ,the limit : " + Item.OItems.get(i).getLimit());
                b = true;
            }
            if (b == false) {
                System.out.println("the item not exist or you made a mistake entering .");
            }
        }

    }

    //بحث و تصفية للعناصر حسب الفئة
    public void Searsh_Type_Item(String type) {
        boolean b = false;
        for (int i = 0; i < Item.OItems.size(); i++) {
            if (Item.OItems.get(i).getItemName().equals(type)) {
                System.out.println("Item : " + Item.OItems.get(i).getItemName() + " , the amount :" + Item.OItems.get(i).getAmount() + " ,the type : " + Item.OItems.get(i).getTybe() + " ,the price : " + Item.OItems.get(i).getPrice() + " ,the limit : " + Item.OItems.get(i).getLimit());
                b = true;
            }
            if (b == false) {
                System.out.println("the item not exist or you made a mistake entering .");
            }
        }

    }

    //بحث و تصفية للعناصر حسب الحالة 
    public void Searsh_status_Item(String status) {
        boolean b = false;
        for (int i = 0; i < Item.OItems.size(); i++) {
            if (Item.OItems.get(i).getStatus().equals(status)) {
                System.out.println("Item : " + Item.OItems.get(i).getItemName() + " , the amount :" + Item.OItems.get(i).getAmount() + " ,the type : " + Item.OItems.get(i).getTybe() + " ,the price : " + Item.OItems.get(i).getPrice() + " ,the limit : " + Item.OItems.get(i).getLimit());
                b = true;
            }
            if (b == false) {
                System.out.println("the item not exist or you made a mistake entering .");
            }
        }

    }

    //يحفظ حالة المخزون
    public void SendStoreMessage() {
        try {
            File f = new File("store.txt");
            PrintWriter pr = new PrintWriter(f);
            while (true) {
                for (int i = 0; i < Item.OItems.size(); i++) {
                    pr.print("Item Name :" + Item.OItems.get(i).itemName + " , Amount :" + Item.OItems.get(i).getAmount());
                    pr.flush();
                }
                for (int i = 0; i < Item.products.size(); i += 2) {
                    pr.print("Product Name :" + Item.products.get(i) + " , Amount :" + Item.products.get(i + 1));
                    pr.flush();
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            ProductLine.productLines.get(1).SendExMessage(ex);
        } catch (IOException ex) {
            System.out.println(ex);
            ProductLine.productLines.get(1).SendExMessage(ex);

        }
    }

    //اضافة مهمة 
    public void Add_Task(Product wantedProduct, int wantedAmount, String costumerName, ProductLine productLine) {
        Task t = new Task(wantedProduct, wantedAmount, costumerName, productLine);
        Task.tasks.add(t);
    }

    //الغاء مهمة
    public void cancel_Task(Task task) {

        System.out.println("the product lines :");
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            System.out.println(i + 1 + ". " + ProductLine.deferentproductLines.get(i));
        }
        System.out.println("The Product Line currently in operation :");
        boolean bool = false;
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            for (int j = 0; j < ProductLine.productLines.size(); j++) {
                if (ProductLine.productLines.get(j).thread.isAlive() && ProductLine.productLines.get(j).getLineName().equals(ProductLine.deferentproductLines)) {
                    bool = true;
                }
            }
            if (bool == true) {
                System.out.println(i + 1 + ". " + ProductLine.deferentproductLines.get(i));
            }
        }
        int wantedAmount = 0;
        String wantedProduct = null;
        String costumerName = null;

        System.out.print("chose product line number : ");
        int a = in.nextInt();
        System.out.println("the tasks currently in operation in this product line :");
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName() == ProductLine.deferentproductLines.get(a - 1) && ProductLine.productLines.get(i).thread.isAlive()) {
                System.out.println(i + 1 + ". " + ProductLine.productLines.get(i).task.getWantedAmount() + " " + ProductLine.productLines.get(i).task.getWantedProduct().getProductName() + " for : " + ProductLine.productLines.get(i).task.getCostumerName());
            }
        }
        System.out.println("chose task number : ");
        int b = in.nextInt();
        int x = 0;
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName() == ProductLine.deferentproductLines.get(a - 1)) {
                x++;
            }
            if (x == b) {
                wantedAmount = ProductLine.productLines.get(i).task.getWantedAmount();
                wantedProduct = ProductLine.productLines.get(i).task.getWantedProduct().getProductName();
                costumerName = ProductLine.productLines.get(i).task.getCostumerName();
            }
        }

        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a - 1)) && ProductLine.productLines.get(i).task.getWantedAmount() == wantedAmount && ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(wantedProduct) && ProductLine.productLines.get(i).task.getCostumerName().equals(costumerName)) {    
            ProductLine.productLines.get(i).task.getProductLine().thread.stop();
            }

    }
}

//تابع يبحث عن مهمة معينة في خط معين

public Task Searsh_Task_In_ProductLine(){

      System.out.println("the product lines :");
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            System.out.println(i + 1 + ". " + ProductLine.deferentproductLines.get(i));
        }
        System.out.println("The Product Line currently in operation :");
        boolean bool = false;
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            for (int j = 0; j < ProductLine.productLines.size(); j++) {
                if (ProductLine.productLines.get(j).thread.isAlive() && ProductLine.productLines.get(j).getLineName().equals(ProductLine.deferentproductLines)) {
                    bool = true;
                }
            }
            if (bool == true) {
                System.out.println(i + 1 + ". " + ProductLine.deferentproductLines.get(i));
            }
        }
        int wantedAmount = 0;
        String wantedProduct = null;
        String costumerName = null;

        System.out.print("chose product line number : ");
        int a = in.nextInt();
        System.out.println("the tasks in this product line :");
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName() == ProductLine.deferentproductLines.get(a - 1)) {
                System.out.println(i + 1 + ". " + ProductLine.productLines.get(i).task.getWantedAmount() + " " + ProductLine.productLines.get(i).task.getWantedProduct().getProductName() + " for : " + ProductLine.productLines.get(i).task.getCostumerName());
            }
        }
        System.out.println("chose task number : ");
        int b = in.nextInt();
        int x = 0;
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName() == ProductLine.deferentproductLines.get(a - 1)) {
                x++;
            }
            if (x == b) {
                wantedAmount = ProductLine.productLines.get(i).task.getWantedAmount();
                wantedProduct = ProductLine.productLines.get(i).task.getWantedProduct().getProductName();
                costumerName = ProductLine.productLines.get(i).task.getCostumerName();
            }
        }
        int i ;
        for ( i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a - 1)) && ProductLine.productLines.get(i).task.getWantedAmount() == wantedAmount && ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(wantedProduct) && ProductLine.productLines.get(i).task.getCostumerName().equals(costumerName)) {  
                break;
            }
            
        }
        return ProductLine.productLines.get(i).task;
}

    //عرض المهام التابعة لخط انتاج

    public void View_Tasks_In_ProductLine() {
        System.out.println("the product lines :");
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            System.out.println(i + 1 + ". " + ProductLine.deferentproductLines.get(i));
        }
        System.out.println("The Product Line currently in operation :");
        boolean bool = false;
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            for (int j = 0; j < ProductLine.productLines.size(); j++) {
                if (ProductLine.productLines.get(j).thread.isAlive() && ProductLine.productLines.get(j).getLineName().equals(ProductLine.deferentproductLines)) {
                    bool = true;
                }
            }
            if (bool == true) {
                System.out.println(i + 1 + ". " + ProductLine.deferentproductLines.get(i));
            }
        }
        System.out.print("chose product line number : ");
        int a = in.nextInt();
        System.out.println("the tasks in this product line :");
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName() == ProductLine.deferentproductLines.get(a - 1)) {
                System.out.println(i + 1 + ". " + ProductLine.productLines.get(i).task.getWantedAmount() + " " + ProductLine.productLines.get(i).task.getWantedProduct().getProductName() + " for : " + ProductLine.productLines.get(i).task.getCostumerName());
            }
        }
    }

    //عرض المهام الخاصة بمنتج محدد

    public void View_Tasks_By_Product(Product product){
        int j = 0 ;
        for(int i = 0 ; i <ProductLine.productLines.size() ; i++ ){
            if(ProductLine.productLines.get(i).task.getWantedProduct().equals(product)){
                System.out.println(j+". "+ProductLine.productLines.get(i).task.getWantedAmount() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() +" for : "+ ProductLine.productLines.get(i).task.getCostumerName());
                j++;
            }
        }
    }

    //بحث وتصفية للمهام (قيد التنفيذ / المكتملة)

    public void Searsh_Tasks_By_Status(){
        System.out.println("view tasks : \n 1.under implementation \n 2.finished \n (chose number)");
        int c = in.nextInt();
        int j = 1 ;
        for(int i = 0 ; i < ProductLine.productLines.size() ; i++){
            if(c==1 && ProductLine.productLines.get(i).task.getTaskStatus().equals("under implementation")){
                System.out.println(j+". "+ProductLine.productLines.get(i).task.getWantedAmount() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() +" for : "+ ProductLine.productLines.get(i).task.getCostumerName());
                j++ ;
            }
            if(c==2 && ProductLine.productLines.get(i).task.getTaskStatus().equals("finished")){
                System.out.println(j+". "+ProductLine.productLines.get(i).task.getWantedAmount() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() +" for : "+ ProductLine.productLines.get(i).task.getCostumerName());
                j++ ;
            }
        }

    }
    //عرض خطوط الانتاج التي قامت بمهمات محددة لمنتج محدد

    public void View_ProductLine_By_Tasks_For_Product(){
        System.out.println("chose the product : ");
        for( int i = 0 ; i <Product.OProducts.size() ; i++){
            System.out.println(i+1 +". " + Product.OProducts.get(i).getProductName());
        }
        System.out.print("chose number : ");
        int c =in.nextInt();
        String bn = Product.OProducts.get(c-1).getProductName();
        System.out.print("enter the amount : ");
        int a =in.nextInt();
        boolean b =false ; 
        for(int i = 0 ; i < ProductLine.productLines.size() ; i++){
            if(ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(bn) && ProductLine.productLines.get(i).task.getWantedAmount() == a){
            System.out.println("-"+ProductLine.productLines.get(i).task.getWantedAmount() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() +" for : "+ ProductLine.productLines.get(i).task.getCostumerName() + " in Product Line : " + ProductLine.productLines.get(i).getLineName());
            b=true;
            }
        }
        if(b==false){
            System.out.println("there is no task with these specifications.");
        }

    }

    //عرض المنتجات المصنعة التابعة لخط انتاج محدد

    public void View_Product_By_ProductLine(){
        System.out.println("the products Lines : ");
        for(int i = 0 ; i < ProductLine.deferentproductLines.size() ; i++){
            System.out.println(i+1 +". "+ ProductLine.deferentproductLines.get(i));
        }
        System.out.print("chose product Line number : ");
        int c = in.nextInt();
        String s = ProductLine.deferentproductLines.get(c-1);
        boolean b = false ;
        for(int i = 0 ; i < ProductLine.productLines.size() ; i++){
            for(int j = 0 ; j < Item.pproduct.size() ; j++){
                if(ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(s) && Item.pproduct.get(j).getProductName().equals(s)){
                   System.out.println(Item.pproduct.get(j).getProductName());
                   b =true ;
                }
            }
        }
        if(b==false){
            System.out.println("there is no finished product in this Product line .");
        }
    } 

    //عرض المنتجات المصنعة من قبل جميع خطوط الانتاج

    public void View_All_finished_product(){
        for(int i = 0 ; i < Item.pproduct.size() ; i++){
            System.out.println("-" + Item.pproduct.get(i).getProductName());
        }
    }

    //عرض المنتج الاكثر طلبا خلال فترة محددة ضمن جميع خطوط الانتاج

    public void View_Most_Requested_Product_In_Specific_Period(){

    }
}
