
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eda
 */
@ManagedBean(name="UserRecipeBean")
@SessionScoped
public class UserRecipe {
    private int id;
    private int userid;
    private int recipeid;
    private Date readingDate;
    private String label;
    private List<UserRecipe> userRecipes;

    public UserRecipe() {
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

    public int getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(int recipeid) {
        this.recipeid = recipeid;
    }

    public Date getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Date readingDate) {
        this.readingDate = readingDate;
    }
    
    public int getUserRecipeListByLabel(int userid, String label){
        DBLayerEda db = new DBLayerEda();
        int number = db.userRecipeNumberByLabel(userid, label);
        return number;
    }
    
    public void getUserRecipeByLabelAndDate(int userid, String label, Date readingDate){
        DBLayerEda db = new DBLayerEda();
        db.userRecipeNumberByLabelAndDate(userid, label, readingDate);
    }
    
    public void recipeMade(int userid, int recipeid){
        DBLayerEda db = new DBLayerEda();
        db.UserRecipeTried(userid, recipeid);
    }
    
}
