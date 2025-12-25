
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //حاجات التطبيق //لا تلمس
        

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
        l5.add(2);
        l5.add(i6);
        l5.add((float)0.25);
        Product p5 = new Product("juice" , l5);

        
        //creat ProductLine

        Management.Add_ProductLine("furniture");
        Management.Add_ProductLine("dietetics");
        
        //creat task

        Task t1 = new Task(p1 , 77 , "hussien" ,"furniture");
        Task t2 = new Task(p2 , 47 , "ahmad" , "furniture");
        Task t3 = new Task(p3 , 57 , "ali" ,"furniture");
        Task t4 = new Task(p4 , 227 , "omar" , "dietetics");
        // Task t5 = new Task(p5 , 117 , "khalid" , pl5);
        // Task t6 = new Task(p5 , 227 , "mohummad" , pl6);

        // // تجريب توابع خط الانتاج

        // //يتفقد ما اذا كانت الموارد كافية
        // pl1.check(p5, Amount, costumerName);

        // //يقوم بحجز الموارد اللازمة لصنع المنتجات
        // pl1.pullAmount(p5, Amount, costumerName);

        // //تابع يرسل اشعار و يتعامل مع الموارد الغير كافية للمنتجات
        // pl1.SendNotification(p5, Amount, costumerName);

        // //تابع يضيف المنتجات المصنعة للمخزون و اذا كانت موجودة يزيد العدد
        // pl1.AddProudectTOStore(p5, Amount);//مش شغاااال//تحت الصيانة

        // //تابع بسجل الاستثناءات الى ملف error.txt
        // pl1.SendExMessage(e);

        // //تابع يسجل حالة المخزون بشكل يومي الى ملف نصي
        // pl1.SendStoreMessage();

        // //تابع يقرا حالة المخزون من ملف نصي عند تشغيل 
        // pl1.ReadStoreMessage();






        

        //تجريب توابع الادارة

        //Management mg = new Management();

        // //اضافة خط انتاج//ok
        // mg.Add_ProductLine("alaa","not activate");

        // //تعديل خط انتاج//ok
        // mg.ProductLine_editer1();

        //عرض اداء خطوط الانتاج
        //mg.View_ProductLine_Performance();//كلو تمام ما عدا استدعاء المنتجات المصنعة


        //توابع مشرف الانتاج
        // //اضافة عناصر 
        // mg.Add_Item("hummus","breakfast",500,250,22);//ok

        // //عرض عناصر 
        // mg.View_Item();//ok

        // //تعديل عناصر 
        // mg.Edit_Item();//ok

        // //حذف عناصر
        // mg.Delete_Item();//ok

        //بحث و تصفية للعناصر حسب الاسم
        // mg.Searsh_Name_Item("potato");//ok

        // //بحث و تصفية للعناصر حسب الفئة
        // mg.Searsh_Type_Item("dietetics");//ok

        // //بحث و تصفية للعناصر حسب الحالة 
        // mg.Searsh_status_Item("below the minimum");//ok

        // //يحفظ حالة المخزون
        // mg.SendStoreMessage();//ok

        // //اضافة مهمة 
        // mg.Add_Task(p1,2," hh",pl1);//ok

        // //الغاء مهمة
        // mg.cancel_Task();//بقي طريقة ايقاف الثريد

        // //تابع يبحث عن مهمة معينة في خط معين
        // System.out.println(mg.Searsh_Task_In_ProductLine("desk" , 77 , "hussien" , "furniture"));//ok2

        // //عرض المهام التابعة لخط انتاج
        // mg.View_Tasks_In_ProductLine();//ok

        // //عرض المهام الخاصة بمنتج محدد
        // mg.View_Tasks_By_Product(p1);//ok

        // //بحث وتصفية للمهام (قيد التنفيذ / المكتملة)
        // mg.Searsh_Tasks_By_Status();//ok

        // //عرض خطوط الانتاج التي قامت بمهمات محددة لمنتج محدد
        // mg.View_ProductLine_By_Tasks_For_Product();//ok

        // //عرض المنتجات المصنعة التابعة لخط انتاج محدد
        // mg.View_Product_By_ProductLine();//ok

        // //عرض المنتجات المصنعة من قبل جميع خطوط الانتاج
        // mg.View_All_finished_product();//ok

       

       


        for(int i = 0 ; i < ProductLine.productLines.size();i++){
        if(ProductLine.productLines.get(i).thread.isAlive()){
           try{
           ProductLine.productLines.get(i).thread.join();
           }catch(Exception e){
               ProductLine.productLines.get(i).SendExMessage(e);
           }
       }
        }










    

        










        


    }
}
