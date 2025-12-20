import java.time.LocalDate;

public class Task {
    private int taskNumber  ;
    private Product wantedProduct ;
    private int wantedAmount ;
    private String costumerName ;
    private LocalDate startDate ; 
    private LocalDate finishDate ;
    private String TaskStatus ;
    private ProductLine productLine  ;
    static int count = 1 ;

    //constructer

    public Task(Product wantedProduct  , int wantedAmount , String costumerName ,ProductLine productLine){
        this.productLine = productLine ;
        this.wantedProduct = wantedProduct ;
        this.wantedAmount = wantedAmount ;
        this.costumerName = costumerName ;
        taskNumber = count ;
        count++ ;
((ProductLine)productLine).startProductLine(costumerName , wantedProduct , wantedAmount);
    }

    

    

}
    

