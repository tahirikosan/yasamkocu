
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
@ManagedBean
@RequestScoped
public class UserNutrition {
     private int id;
    private int userid;
    private String ndate;
private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNdate() {
        return ndate;
    }

    public void setNdate(String ndate) {
        this.ndate = ndate;
    }

   
  
  
   
     private int nutritionid;
      private int nutritiongr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getNutritionid() {
        return nutritionid;
    }

    public void setNutritionid(int nutritionid) {
        this.nutritionid = nutritionid;
    }

    public int getNutritiongr() {
        return nutritiongr;
    }

    public void setNutritiongr(int nutritiongr) {
        this.nutritiongr = nutritiongr;
    }
    
  public String userAdd(){
        
             DBLayerZeyno db = new DBLayerZeyno();
              db.connect();
           boolean result = db.AddUserNutrition(this);

            if(result){
               return "nutrition.xhtml";
            }else{
               return "basarisiz.xhtml";
            }
     }
}
