
import java.util.ArrayList;
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
 * @author Zeynep
 */
@ManagedBean(name = "MeditationBean")
@SessionScoped
public class Meditation {

    public int id;
    public String title;
    public String imageurl;
    public String videourl;
        public List<Meditation> meditations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
   public List<Meditation> getMeditations() { //Displaying all nutritions
       
        DBLayerZeyno db = new DBLayerZeyno();
        meditations = new ArrayList();
        meditations = db.getMeditations();
        return meditations;
    }
}