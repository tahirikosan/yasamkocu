
import java.text.DateFormat;
import java.util.Date;
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
@ManagedBean
public class User {
    private String name;
    private String password;
    
    public User(){
       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String deneme(){
        DBLayer db = new DBLayer();
        return db.userList();
       
    }
       
}
