
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Management {
    Scanner in = new Scanner(System.in);
    
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

}
