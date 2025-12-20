import java.util.Scanner;

public class ProductLine  implements Runnable{
            Scanner in = new Scanner(System.in);

    private int lineNumber ; 
    private String LineName ; 
    private String ProductLinestatus ;
    static int count = 1 ;

    

    Thread thread ;

//constructer

    public ProductLine (String LineName , String  ProductLinestatus){
        this.ProductLinestatus = ProductLinestatus ;
        this.LineName = LineName ;
        thread = new Thread(this , LineName);
        lineNumber = count ;
        count++ ;
    }

//creat and start

    private Product proudect ;
    private int Amount ;
    private String costumerN ;

    public ProductLine startProductLine  (String costumerName ,Product wantedProduct,int wantedAmount ){
    Amount = wantedAmount ;
    proudect = wantedProduct ;
    costumerN = costumerName ;
    this.thread.start();
    return this ; 
    }
    
    @Override
    public void run() {   
        if (check(proudect, Amount, costumerN)){
            pullAmount(proudect, Amount, costumerN);
            AddProudectTOStore(proudect, Amount);
        }else SendNotification(proudect, Amount, costumerN);

   

    
    System.out.println("done");
               

    }

        //يتفقد ما اذا كانت الموارد كافية

        public boolean check (Product Proudect,int Amount,String costumerName){
            boolean b = true ;
            for(int i = 0 ; i<Proudect.Items.size() ; i +=2 ){       
                 if (((Item)Proudect.Items.get(i)).getAmount() -  ((int)Proudect.Items.get(i+1)*Amount) > 0){
                    System.out.println("1i1 = " + i);
                    }else{ 
                        b = false ;}
                        }
            return b;
            }

        //يقوم بحجز الموارد اللازمة لصنع المنتجات

        public void pullAmount (Product Proudect,int Amount,String costumerName){
             for(int i = 0 ; i<Proudect.Items.size() ; i +=2 ){   int a  ;
                a = (int)Proudect.Items.get(i+1) * Amount ;
                ((Item)Proudect.Items.get(i)).pullAmount(a);
                System.out.println("2i2 = " + i);     
             }
            }
            //تابع يرسل اشعار و يتعامل مع الموارد الغير كافية للمنتجات
            public void SendNotification(Product Proudect,int Amount,String costumerName){
                System.out.println("the items not enough \n menu : \n 1.suspend order untel resources become available \n 2.cancel order \n (chose number)");
                int i = in.nextInt();
                if(i==1){
                    System.out.println(costumerName+", your order : "+Amount+" of "+ Proudect.getProductName()+" ,has been pended untel resources become available .");
                    boolean j=false;
                    do{
                        j=check(Proudect, Amount, costumerName);
                       
                        try {
                            thread.sleep(10000);
                        } catch (InterruptedException ex) {

                                System.out.println(ex);                        }
                    }while(j==false);
                    System.out.println(costumerName+", your suspended order : "+Amount+" of "+ Proudect.getProductName()+" has been completed");
                    pullAmount(Proudect, Amount, costumerName);
                    AddProudectTOStore(Proudect, Amount);
                }else{
                    System.out.println("your order canceled");
                }
                
                
            }
            
            //تابع يضيف المنتجات المصنعة للمخزون و اذا كانت موجودة يزيد العدد
            
            public void AddProudectTOStore(Product Proudect,int Amount){
                Item.Add_products(proudect.getProductName(),Amount);
            }

            //



    }
  
    