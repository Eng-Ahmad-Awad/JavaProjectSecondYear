
import java.util.HashMap;


public class UserAndPassowrd {

    HashMap<String, String> loginInfo = new HashMap<String , String>();

    public UserAndPassowrd(){
        loginInfo.put("admin","admin1234");
        loginInfo.put("hr","hr1234");
    }

    

    //تابع يتحقق من المدخلات في صفحة التسجيل
    public boolean checkInfo (String user , String pass){


        return true ;
    }
    
}
