
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
@ManagedBean(name = "ExerciseBean")
@SessionScoped
public class Exercise {

    public int id;
    public String name;
    public int cal;
    public String imageurl;
    private List<Exercise> exercises;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String add(int userid) {

        DBLayerZeyno db = new DBLayerZeyno();
        db.connect();
        boolean result = db.AddExercise(this, userid);

        if (result) {
            return "exercise.xhtml";
        } else {
            return "basarisiz.xhtml";
        }
    }

    public List<Exercise> getExercises(int userid) { //Displaying all exercises

        DBLayerZeyno db = new DBLayerZeyno();
        exercises = new ArrayList();
        exercises = db.getExercises(userid);
        return exercises;
    }

    public void DeleteExercises(int nutritionid, int userid) { //Deleting nutrition
        DBLayerZeyno db = new DBLayerZeyno();
        db.connect();
        db.DeleteExercise(nutritionid, userid);
    }
}
