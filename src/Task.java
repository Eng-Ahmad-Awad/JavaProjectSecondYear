
import java.time.LocalDate;
import java.util.Scanner;

public class Task {

    Scanner in = new Scanner(System.in);
    private int taskNumber;
    private Product wantedProduct;
    private int wantedAmount;
    private String costumerName;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String TaskStatus;
    private ProductLine productLine;
    static int count = 1;

    //constructer
    public Task(Product wantedProduct, int wantedAmount, String costumerName, ProductLine productLine) {
        this.productLine = productLine;
        this.wantedProduct = wantedProduct;
        this.wantedAmount = wantedAmount;
        this.costumerName = costumerName;
        taskNumber = count;
        count++;
        ((ProductLine) productLine).startProductLine(costumerName, wantedProduct, wantedAmount);
    }
//تم النقل الى management
    // //اضافة خط انتاج جديد
    // public void Add_ProductLine(String LineName, String ProductLinestatus) {
    //     ProductLine p = new ProductLine(LineName, ProductLinestatus);
    //     ProductLine.productLines.add(p);

    // }

    // //التعديل على خط انتاج
    // public void ProductLine_editer1() {
    //     System.out.println("chose the Product line that you want to edit :");
    //     int i;
    //     for (i = 0; i < productLine.productLines.size(); i++) {
    //         System.out.println(i + 1 + "." + productLine.productLines.get(i).getLineName() + " , status : " + ProductLine.productLines.get(i).getProductLinestatus());
    //     }
    //     System.out.println("enter the new name :");
    //     String NewName = in.nextLine();
    //     System.out.println("enter the new satus :");
    //     String NewStatus = in.nextLine();
    //     ProductLine_editer2(ProductLine.productLines.get(i), NewName, NewStatus);
    // }

    // public void ProductLine_editer2(ProductLine p, String LineName, String ProductLinestatus) {
    //     p.setLineName(LineName);
    //     p.setProductLinestatus(ProductLinestatus);
    // }

    // //عرض اداء خطوط الانتاج
    // public void View_ProductLine_Performance() {
    //     System.out.println("The Product Line currently in operation :");
    //     for (int i = 0; i < ProductLine.productLines.size(); i++) {
    //         if (ProductLine.productLines.get(i).thread.isAlive()) {
    //             System.out.println(i + ". " + ProductLine.productLines.get(i).getLineName());
    //         }            
    //     }
    //     System.out.println("chose number :");

    // }

}
