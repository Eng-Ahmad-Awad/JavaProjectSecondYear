public class ProductLine  implements Runnable{
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

    private Product Proudect ;
    private int Amount ;
    private String costumerN ;

    public ProductLine startProductLine  (String costumerName ,Product wantedProduct,int wantedAmount ){
    Amount = wantedAmount ;
    Proudect = wantedProduct ;
    costumerN = costumerName ;
     this.thread.start();
     return this ; 
    }
    
    @Override
    public void run() {   
        if (check(Proudect, Amount, costumerN)){
            pullAmount(Proudect, Amount, costumerN);
        }else System.out.println("the items not enough");

   

    
    System.out.println("done");
               

    }
/* 
    //Check enough Items

    synchronized public boolean ChecEnoughItems(Product Proudect,int Amount,String costumerName){

        if(Proudect.getProductName().equals("desk")){

           if(((Item)Proudect.Items.get(0)).getAmount() -  ((int)Proudect.Items.get(1)*Amount) >= 0 && ((Item)Proudect.Items.get(2)).getAmount() -  ((int)Proudect.Items.get(1)*Amount) >= 0)
            {
                int a ;
                a = (int)Proudect.Items.get(1) * Amount ;
                int b ;
                 b = (int)Proudect.Items.get(3) * Amount;
                ((Item)Proudect.Items.get(0)).pullAmount(a);
                ((Item)Proudect.Items.get(2)).pullAmount(b);
                return true ;
            }else{
                System.out.println("there is no enough amount of items for " + costumerName +"'s order .");
                return false ;
                }

            }else if(Proudect.getProductName().equals("cupboard")){

                if (((Item)Proudect.Items.get(0)).getAmount() -  ((int)Proudect.Items.get(1)*Amount) >= 0){
                int a  ;
                a = (int)Proudect.Items.get(1) * Amount ;
                ((Item)Proudect.Items.get(0)).pullAmount(a);
                return true ;

                }else {System.out.println("there is no enough amount of items for "+ costumerName +"'s order .");
             return false ;}  

            }

        return false;
        }  
*/
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
    }
  
    