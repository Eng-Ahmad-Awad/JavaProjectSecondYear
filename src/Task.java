
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {

    Scanner in = new Scanner(System.in);
    static List<Task>tasks = new ArrayList<>(); 
    private int taskNumber;
    private Product wantedProduct;
    private int wantedAmount;
    private String costumerName;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String TaskStatus;
    private ProductLine productLine;
    private int done = 0 ;
    static int count = 1;


    //constructer
    public Task(Product wantedProduct, int wantedAmount, String costumerName, String productLine) {
        this.wantedProduct = wantedProduct;
        this.wantedAmount = wantedAmount;
        this.costumerName = costumerName;
        taskNumber = count;
        count++;
        tasks.add(this);
        this.TaskStatus = "under implementation";
        int b =0 ; 
        int i;
        for( i = 0 ; i < ProductLine.productLines.size() ; i++){
            if(ProductLine.productLines.get(i).getLineName().equals(productLine)){
        
                b =1;
                if(ProductLine.productLines.get(i).thread.isAlive()){
                    b=2;
                }
            }
            if(b==1)break;
            
            if(i == ProductLine.productLines.size()-1 && b ==2 ){
                System.out.println("the factory is operating at maximum capacity , try again later");
            }
            
        } 
        if(i != ProductLine.productLines.size()){
        ProductLine.productLines.get(i).startProductLine(costumerName, wantedProduct, wantedAmount ,this);
        }
    }

    
    public void setDone(int done) {
        this.done = done;
    }


    public Product getWantedProduct() {
        return wantedProduct;
    }

    public int getWantedAmount() {
        return wantedAmount;
    }

    public String getCostumerName() {
        return costumerName;
    }

    
    

    public int getDone() {
        return done;
    }

    public ProductLine getProductLine() {
        return productLine;
    }

    public String getTaskStatus() {
        return TaskStatus;
    }



}
