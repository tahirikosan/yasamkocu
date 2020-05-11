
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Zeynep
 */
@ManagedBean(name = "UserNutritionBean")
@SessionScoped
public class UserNutrition {

    private int id;
    private int userid;
    private String ndate;
    private String Name;
    private int nutritionid;
    private int nutritiongr;
    private List<UserNutrition> usernutritions;

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

    public void addUserNutrition(int nutritionid, int usernutgr, int userid) { //Adding new nutrition to user

        DBLayerZeyno db = new DBLayerZeyno();
        db.connect();
        db.AddUserNutrition(nutritionid, usernutgr, userid);

    }

    public List<UserNutrition> getUserNutritions(int userid) {//Displaying user's all nutrition

        DBLayerZeyno db = new DBLayerZeyno();
        usernutritions = new ArrayList();
        usernutritions = db.getUserNutritions(userid);
        return usernutritions;
    }

    public void DeleteUserNutrition(int usernutritionid) { //Deleting nutrition from user
        DBLayerZeyno db = new DBLayerZeyno();
        db.connect();
        db.DeleteUserNutrition(usernutritionid);
    }

    public int TotalCalNutrition(int userid) { //Returning user nutrition's total cal
        DBLayerZeyno db = new DBLayerZeyno();
        db.connect();
        return db.getTotalCalNutrition(userid);
    }
}
