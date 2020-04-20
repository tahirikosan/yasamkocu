
import javax.faces.bean.ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tahir
 */

@ManagedBean(name = "controller")
public class HomeController {
    
    
    public String goToRegisterPage(){
        return "register.xhtml";
    }
    
    public String goToLoginPage(){
        return "login.xhtml";
    }
   
}
