
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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

    //عرض اداء خطوط الانتاج
    public void View_ProductLine_Performance() {
        System.out.println("The Product Line currently in operation :");
        for (int i = 0; i < ProductLine.productLines.size(); i++) {
            if (ProductLine.productLines.get(i).thread.isAlive()) {
                System.out.println(i + ". " + ProductLine.productLines.get(i).getLineName());
            }            
        }
        System.out.println("chose number :");
        int a = in.nextInt();
        System.out.println("the task of this Product line is to produce " + ProductLine.productLines.get(a).getAmount() +" "+ ProductLine.productLines.get(a).getproudect().getProductName());
        int lift =ProductLine.productLines.get(a).getAmount()-ProductLine.productLines.get(a).getDone();
        System.out.println(ProductLine.productLines.get(a).getDone()  + ProductLine.productLines.get(a).getproudect().getProductName() + "have been made ," + lift + " lift");
        int percentage = (ProductLine.productLines.get(a).getAmount()/ProductLine.productLines.get(a).getDone()) * 100 ;
        System.out.println(percentage + " completed .");
        System.out.println("the estimated completion time is : " + (lift*5)/60 + " minute and : " + (lift*5)%60 + " seconds ." );
        System.out.println("do you want to add comment to this product line \n 1.yes 2.no \n (chose number)");
        int c = in.nextInt();
        if (c==1){
            try {
                FileWriter fr  = new FileWriter("notes.txt",true );
                PrintWriter pr = new PrintWriter(fr);
                String s1 ="notes for" + ProductLine.productLines.get(a).getLineName() + "product line :";
                String s2 = in.nextLine();
                pr.print(s1);
                pr.print(s2);
            } catch (FileNotFoundException e) {
                System.out.println(e);
                ProductLine.productLines.get(0).SendExMessage(e);
            }catch(IOException e){
                ProductLine.productLines.get(0).SendExMessage(e);

            }

        }
  
    }

    //توابع مشرف الانتاج

    //اضافة عناصر 

    public void Add_Item(String itemName, String tybe, int price, int amount, float limit){
        Item i = new Item(itemName, tybe, price, amount, limit);
    }

    //عرض عناصر 

    public void View_Item(){
        System.out.println("the items : ");
        for(int i = 0 ; i < Item.OItems.size() ; i++){

            System.out.println(i+1 +". "+Item.OItems.get(i).getItemName() +" , the amount :"+ Item.OItems.get(i).getAmount() +" ,the type : " + Item.OItems.get(i).getTybe() + " ,the price : " +  Item.OItems.get(i).getPrice() + " ,the limit : " + Item.OItems.get(i).getLimit());
        }
    }

    //تعديل عناصر 

    public void Edit_Item(){
        View_Item();
        System.out.println("chose the item that you want to edit(chose number) .");
        int c = in.nextInt();
        String oldName = Item.OItems.get(c-1).getItemName() ;
        String oldType = Item.OItems.get(c-1).getTybe();
        int oldPrice = Item.OItems.get(c-1).getPrice();
        int oldAmount = Item.OItems.get(c-1).getAmount();
        float oldLimit = Item.OItems.get(c-1).getLimit();

        System.out.print("enter new name : ");
        String newName = in.nextLine();
        Item.OItems.get(c).setItemName(newName);

        System.out.print("enter new type : ");
        String newType = in.nextLine();
        Item.OItems.get(c).setTybe(newType);

        System.out.print("enter new price : ");
        int newPrice = in.nextInt() ;
        Item.OItems.get(c).setPrice(newPrice);

        System.out.print("enter new amount : ");
        int newAmount = in.nextInt();
        Item.OItems.get(c).setAmount(newAmount);

        System.out.println("enter new limit : ");
        float newLimit = in.nextFloat();
        Item.OItems.get(c).setLimit(newLimit);

    }

    //حذف عناصر

    public void Delete_Item(){
        View_Item();
        System.out.println("chose the item that you want to delete (chose number) .");
        int c = in.nextInt();
        Item.OItems.remove(c-1);
    }

    //بحث و تصفية للعناصر حسب الاسم

    public void Searsh_Name_Item(String name){
        boolean b =false;
        for(int i = 0 ; i < Item.OItems.size() ; i++){
            if(Item.OItems.get(i).getItemName().equals(name)){
            System.out.println("Item : "+Item.OItems.get(i).getItemName() +" , the amount :"+ Item.OItems.get(i).getAmount() +" ,the type : " + Item.OItems.get(i).getTybe() + " ,the price : " +  Item.OItems.get(i).getPrice() + " ,the limit : " + Item.OItems.get(i).getLimit());
            b=true;
            }if(b==false){
                System.out.println("the item not exist or you made a mistake entering .");
            }
        }

    }

    //بحث و تصفية للعناصر حسب الفئة


    public void Searsh_Type_Item(String type){ boolean b =false;
        for(int i = 0 ; i < Item.OItems.size() ; i++){
            if(Item.OItems.get(i).getItemName().equals(type)){
            System.out.println("Item : "+Item.OItems.get(i).getItemName() +" , the amount :"+ Item.OItems.get(i).getAmount() +" ,the type : " + Item.OItems.get(i).getTybe() + " ,the price : " +  Item.OItems.get(i).getPrice() + " ,the limit : " + Item.OItems.get(i).getLimit());
            b=true;
            }if(b==false){
                System.out.println("the item not exist or you made a mistake entering .");
            }
        }

    }

    //بحث و تصفية للعناصر حسب الحالة 

    public void Searsh_status_Item(String status){
         boolean b =false;
        for(int i = 0 ; i < Item.OItems.size() ; i++){
            if(Item.OItems.get(i).getStatus().equals(status)){
            System.out.println("Item : "+Item.OItems.get(i).getItemName() +" , the amount :"+ Item.OItems.get(i).getAmount() +" ,the type : " + Item.OItems.get(i).getTybe() + " ,the price : " +  Item.OItems.get(i).getPrice() + " ,the limit : " + Item.OItems.get(i).getLimit());
            b=true;
            }if(b==false){
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
                for (int i = 0; i < Item.products.size(); i++) {
                    pr.print("Product Name :" + Item.products.get(i) + " , Amount :" + Item.products.get(i + 1));
                    pr.flush();}
                    
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            ProductLine.productLines.get(1).SendExMessage(ex);
        } catch (IOException ex) {
            System.out.println(ex);
             ProductLine.productLines.get(1).SendExMessage(ex);

        }
    }



}
