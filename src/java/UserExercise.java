
import java.util.ArrayList;
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
@ManagedBean(name = "UserExerciseBean")
@SessionScoped
public class UserExercise {

    private int id;
    private int userid;
    private int exerciseid;
    private int exercisetime;
    private String edate;
    private String Name;
    private List<UserExercise> userexercises;

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

    public int getExerciseid() {
        return exerciseid;
    }

    public void setExerciseid(int exerciseid) {
        this.exerciseid = exerciseid;
    }

    public int getExercisetime() {
        return exercisetime;
    }

    public void setExercisetime(int exercisetime) {
        this.exercisetime = exercisetime;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void addUserExercise(int exerciseid, int userextime, int userid) { //Adding new exercise to user

        DBLayerZeyno db = new DBLayerZeyno();
        db.connect();
        db.AddUserExercise(exerciseid, userextime, userid);
    }

    public List<UserExercise> getUserExercises(int userid) {//Displaying user's all exercises

        DBLayerZeyno db = new DBLayerZeyno();
        userexercises = new ArrayList();
        userexercises = db.getUserExercises(userid);
        return userexercises;
    }

    public void DeleteUserExercise(int userexerciseid) { //Deleting nutrition from user
        DBLayerZeyno db = new DBLayerZeyno();
        db.connect();
        db.DeleteUserExercise(userexerciseid);
    }
    
    
       public int getTotalCalExercise(int userid) { //Returning user exercise's total cal
        DBLayerZeyno db = new DBLayerZeyno();
        db.connect();
        return db.getTotalCalExercise(userid);
    }

}
