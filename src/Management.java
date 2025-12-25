
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.SourceDataLine;

public class Management {

    Scanner in = new Scanner(System.in);

    //توابع المدير

    //اضافة خط انتاج جديد
    
    public static  void Add_ProductLine(String LineName) {
        ProductLine p1 = new ProductLine(LineName);
        ProductLine p2 = new ProductLine(LineName);
        ProductLine p3 = new ProductLine(LineName);
    }

    //التعديل على خط انتاج
   
    public void ProductLine_editer(String OldLineName, String NewLineName) {

        for(int i = 0 ; i < ProductLine.productLines.size() ; i++){
            if(ProductLine.productLines.get(i).getLineName().equals(OldLineName))
            ProductLine.productLines.get(i).setLineName(NewLineName);
        }
    for(int i = 0 ; i < ProductLine.deferentproductLines.size() ; i++){
        if(ProductLine.deferentproductLines.get(i).equals(OldLineName)){
            ProductLine.deferentproductLines.set(i, NewLineName);
        }
    }
    }

    //عرض اداء خطوط الانتاج

    public void View_ProductLine_Performance(/*String ProductLine_name , String task_name*/) {
        System.out.println("the product lines :");
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            System.out.println((i + 1)+ ". " + ProductLine.deferentproductLines.get(i));
        }
        System.out.println("The Product Line currently in operation :");
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            boolean bool = false;
            for (int j = 0; j < ProductLine.productLines.size(); j++) {
                if (ProductLine.productLines.get(j).getProductLinestatus().equals("under implementation") && ProductLine.productLines.get(j).getLineName().equals(ProductLine.deferentproductLines.get(i))) {
                    bool = true;
                }
            }
            if (bool == true) {
                System.out.println(i + ". " + ProductLine.deferentproductLines.get(i));
            }
        }
        int wantedAmount = 0;
        String wantedProduct = null;
        String costumerName = null;

        System.out.print("chose product line number : ");
        int a = in.nextInt();
        System.out.println("the tasks in this product line :");
        for (int i = 0 ; i < ProductLine.productLines.size() ; i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a)) && ProductLine.productLines.get(i).getProductLinestatus().equals("under implementation")) {
                System.out.println((i + 1) + ". " + ProductLine.productLines.get(i).task.getWantedAmount() + " " + ProductLine.productLines.get(i).task.getWantedProduct().getProductName() + " for : " + ProductLine.productLines.get(i).task.getCostumerName());
            }
        }
        System.out.println("chose task number : ");
        int b = in.nextInt();
        int x = 0;
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a))) {
                x++;
            }
            if (x == b) {
                wantedAmount = ProductLine.productLines.get(i).task.getWantedAmount();
                wantedProduct = ProductLine.productLines.get(i).task.getWantedProduct().getProductName();
                costumerName = ProductLine.productLines.get(i).task.getCostumerName();
            }
        }
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a)) && ProductLine.productLines.get(i).task.getWantedAmount() == wantedAmount && ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(wantedProduct) && ProductLine.productLines.get(i).task.getCostumerName().equals(costumerName)) {
                int lift = ProductLine.productLines.get(i).task.getWantedAmount() - ProductLine.productLines.get(i).getDone();
                System.out.println(ProductLine.productLines.get(i).task.getDone() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() + "have been made ," + lift + " lift");
                int percentage = (ProductLine.productLines.get(i).getDone() /ProductLine.productLines.get(i).task.getWantedAmount() ) * 100;
                System.out.println(percentage + "% completed .");
                System.out.println("the estimated completion time is : " + (lift * 5) / 60 + " minute and : " + (lift * 5) % 60 + " seconds .");
                System.out.println("do you want to add comment to this product line \n 1.yes 2.no \n (chose number)");
                int c = in.nextInt();
                if (c == 1) {
                    try {
                        FileWriter fr = new FileWriter("notes.txt", true);

                        PrintWriter pr = new PrintWriter(fr);

                        String s1 = "notes for" + ProductLine.deferentproductLines.get(a) + "product line :";
                        System.out.println("write the note : ");
                        String s2 = in.next();

                        pr.print(s1);
                        pr.print(s2);

                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                        ProductLine.productLines.get(0).SendExMessage(e);
                    } catch (IOException e) {
                        ProductLine.productLines.get(0).SendExMessage(e);

                    }
                    
                }
                return ;
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
        String newName = in.next();
        Item.OItems.get(c-1).setItemName(newName);

        System.out.print("enter new type : ");
        String newType = in.next();
        Item.OItems.get(c-1).setTybe(newType);

        System.out.print("enter new price : ");
        int newPrice = in.nextInt();
        Item.OItems.get(c-1).setPrice(newPrice);

        System.out.print("enter new amount : ");
        int newAmount = in.nextInt();
        Item.OItems.get(c-1).setAmount(newAmount);

        System.out.print("enter new limit : ");
        float newLimit = in.nextFloat();
        Item.OItems.get(c-1).setLimit(newLimit);

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
        }
        if (b == false) {
            System.out.println("the item not exist or you made a mistake entering .");
        }

    }

    //بحث و تصفية للعناصر حسب الفئة
    public void Searsh_Type_Item(String type) {
        boolean b = false;
        for (int i = 0; i < Item.OItems.size(); i++) {
            if (Item.OItems.get(i).getTybe().equals(type)) {
                System.out.println("Item : " + Item.OItems.get(i).getItemName() + " , the amount :" + Item.OItems.get(i).getAmount() + " ,the type : " + Item.OItems.get(i).getTybe() + " ,the price : " + Item.OItems.get(i).getPrice() + " ,the limit : " + Item.OItems.get(i).getLimit());
                b = true;
            }
        }
        if (b == false) {
            System.out.println("the item not exist or you made a mistake entering .");
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
        }
        if (b == false) {
            System.out.println("the item not exist or you made a mistake entering .");
        }

    }

    //يحفظ حالة المخزون
    public void SendStoreMessage() {
        try {
            File f = new File("store.txt");
            PrintWriter pr = new PrintWriter(f);
                for (int i = 0; i < Item.OItems.size(); i++) {
                    pr.println("Item Name :" + Item.OItems.get(i).itemName + " , Amount :" + Item.OItems.get(i).getAmount());
                    pr.flush();
                }
                for (int i = 0; i < Item.products_in_Storage.size(); i += 2) {
                    pr.println("Product Name :" + Item.products_in_Storage.get(i).getProductName() + " , Amount :" + Item.products_in_Storage.get(i).getProduct_Amount_In_Stordge());
                    pr.flush();
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
    public void Add_Task(Product wantedProduct, int wantedAmount, String costumerName, String productLine) {
        Task t = new Task(wantedProduct, wantedAmount, costumerName, productLine);
        Task.tasks.add(t);
    }

    //الغاء مهمة
    public void cancel_Task() {

        System.out.println("the product lines :");
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            System.out.println((i + 1)+ ". " + ProductLine.deferentproductLines.get(i));
        }
        System.out.println("The Product Line currently in operation :");
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            boolean bool = false;
            for (int j = 0; j < ProductLine.productLines.size(); j++) {
                if (ProductLine.productLines.get(j).getProductLinestatus().equals("under implementation") && ProductLine.productLines.get(j).getLineName().equals(ProductLine.deferentproductLines.get(i))) {
                    bool = true;
                }
            }
            if (bool == true) {
                System.out.println(i + ". " + ProductLine.deferentproductLines.get(i));
            }
        }
        int wantedAmount = 0;
        String wantedProduct = null;
        String costumerName = null;

        System.out.print("chose product line number : ");
        int a = in.nextInt();
        System.out.println("the tasks in this product line :");
        for (int i = 0 ; i < ProductLine.productLines.size() ; i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a)) && ProductLine.productLines.get(i).getProductLinestatus().equals("under implementation")) {
                System.out.println((i + 1) + ". " + ProductLine.productLines.get(i).task.getWantedAmount() + " " + ProductLine.productLines.get(i).task.getWantedProduct().getProductName() + " for : " + ProductLine.productLines.get(i).task.getCostumerName());
            }
        }
        System.out.println("chose task number : ");
        int b = in.nextInt();
         int x = 0;
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a))) {
                x++;
            }
            if (x == b) {
                wantedAmount = ProductLine.productLines.get(i).task.getWantedAmount();
                wantedProduct = ProductLine.productLines.get(i).task.getWantedProduct().getProductName();
                costumerName = ProductLine.productLines.get(i).task.getCostumerName();
            }
        }
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a)) && ProductLine.productLines.get(i).task.getWantedAmount() == wantedAmount && ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(wantedProduct) && ProductLine.productLines.get(i).task.getCostumerName().equals(costumerName)) {    
            // ProductLine.productLines.get(i).task.getProductLine().thread.stop();
            int lift = ProductLine.productLines.get(i).task.getWantedAmount() - ProductLine.productLines.get(i).getDone();
            System.out.println(ProductLine.productLines.get(i).task.getDone() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() + "have been made ," + lift + " lift");
            int percentage = (ProductLine.productLines.get(i).getDone() /ProductLine.productLines.get(i).task.getWantedAmount()) * 100;
            System.out.println(percentage + "% completed .");
            for(int j = 0 ; j < ProductLine.productLines.get(i).task.getWantedProduct().Items.size() ; j+=2){
                int comeback = (int)ProductLine.productLines.get(i).task.getWantedProduct().Items.get(i+1) * lift;
                ((Item)ProductLine.productLines.get(i).task.getWantedProduct().Items.get(i)).addAmount(comeback) ;
            }
            return;
            }

    }
}

//تابع يبحث عن مهمة معينة في خط معين

public boolean  Searsh_Task_In_ProductLine(String ProductName, int wantedAmount, String costumerName, String productLine ){
    for(int i = 0 ; i < ProductLine.productLines.size() ; i++){
        if(ProductLine.productLines.get(i).getLineName().equals(productLine)  && ProductLine.productLines.get(i).task.getCostumerName().equals(costumerName) && ProductLine.productLines.get(i).task.getWantedAmount() == wantedAmount && ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(ProductName)) {
           return true;
        }

    }
    return false; 
}

    //عرض المهام التابعة لخط انتاج

    public void View_Tasks_In_ProductLine() {
        System.out.println("the product lines :");
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            System.out.println((i + 1)+ ". " + ProductLine.deferentproductLines.get(i));
        }
        System.out.println("The Product Line currently in operation :");
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            boolean bool = false;
            for (int j = 0; j < ProductLine.productLines.size(); j++) {
                if (ProductLine.productLines.get(j).getProductLinestatus().equals("under implementation") && ProductLine.productLines.get(j).getLineName().equals(ProductLine.deferentproductLines.get(i))) {
                    bool = true;
                }
            }
            if (bool == true) {
                System.out.println(i + ". " + ProductLine.deferentproductLines.get(i));
            }
        }
        System.out.print("chose product line number : ");
        int a = in.nextInt();
        System.out.println("the tasks in this product line :");
        for (int i = 0 ; i < ProductLine.productLines.size() ; i++) {
            if (ProductLine.productLines.get(i).getLineName().equals(ProductLine.deferentproductLines.get(a)) && ProductLine.productLines.get(i).getProductLinestatus().equals("under implementation")) {
                System.out.println((i + 1) + ". " + ProductLine.productLines.get(i).task.getWantedAmount() + " " + ProductLine.productLines.get(i).task.getWantedProduct().getProductName() + " for : " + ProductLine.productLines.get(i).task.getCostumerName());
            }
        }}

    //عرض المهام الخاصة بمنتج محدد

    public void View_Tasks_By_Product(Product product){
        int j = 1 ;
        for(int i = 0 ; i <ProductLine.productLines.size() ; i++ ){
            if(ProductLine.productLines.get(i).task != null &&ProductLine.productLines.get(i).task.getWantedProduct().equals(product)){
                System.out.println(j+". "+ProductLine.productLines.get(i).task.getWantedAmount() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() +" for : "+ ProductLine.productLines.get(i).task.getCostumerName());
                j++;
            }
        }
    }

    //بحث وتصفية للمهام (قيد التنفيذ / المكتملة)

    public void Searsh_Tasks_By_Status(){
        System.out.println("view tasks : \n 1.under implementation \n 2.finished \n (chose number)");
        int c = in.nextInt();
        boolean b = false ;
        int j = 1;
        for(int i = 0 ; i < ProductLine.productLines.size() ; i++){
            if(c==1 && ProductLine.productLines.get(i).task != null &&ProductLine.productLines.get(i).task.getTaskStatus().equals("under implementation")){
                System.out.println(j+". "+ProductLine.productLines.get(i).task.getWantedAmount() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() +" for : "+ ProductLine.productLines.get(i).task.getCostumerName());
                b=true;
                j++;
            }
            if(c==2 && ProductLine.productLines.get(i).task != null && ProductLine.productLines.get(i).task.getTaskStatus().equals("finished")){
                System.out.println(j+". "+ProductLine.productLines.get(i).task.getWantedAmount() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() +" for : "+ ProductLine.productLines.get(i).task.getCostumerName());
                b=true;
                j++;
            }
        }
        if(b==false){
            System.out.println("there is no tasks");
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
            if(ProductLine.productLines.get(i).task != null &&ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(bn) && ProductLine.productLines.get(i).task.getWantedAmount() == a){
            System.out.println(ProductLine.productLines.get(i).task.getWantedAmount() +" "+ ProductLine.productLines.get(i).task.getWantedProduct().getProductName() +" for : "+ ProductLine.productLines.get(i).task.getCostumerName() + " in Product Line : " + ProductLine.productLines.get(i).getLineName());
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
            for(int j = 0 ; j < Item.products_in_Storage.size() ; j++){
                if(ProductLine.productLines.get(i).task.getWantedProduct().getProductName().equals(s) && Item.products_in_Storage.get(j).getProductName().equals(s)){
                   System.out.println(Item.products_in_Storage.get(j).getProductName());
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
        for(int i = 0 ; i < (Item.products_in_Storage.size()) ; i++){
            System.out.println("the Product : "+Item.products_in_Storage.get(i).getProductName() +" the amount : "+Item.products_in_Storage.get(i).getProduct_Amount_In_Stordge());
        }
    }

    //عرض المنتج الاكثر طلبا خلال فترة محددة ضمن جميع خطوط الانتاج

    public void View_Most_Requested_Product_In_Specific_Period(){

    }
}
