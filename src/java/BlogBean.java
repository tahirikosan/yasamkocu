
import java.io.Serializable;
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
 * @author Eda
 */

@ManagedBean(name="blogb")
@SessionScoped
public class BlogBean implements Serializable{
    private DBLayerEda db = new DBLayerEda();
    private List<Blog> blogList = db.blogList();
    

    public void BlogBean() {
        //db.connect();
        //this.blogList = new ArrayList();
        //this.blogList = db.blogList();
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }
    
    /*public static void main(String[] args){
        BlogBean yeni = new BlogBean();
        for(int i = 1; i < 2; i++){
            System.out.println(yeni.getBlogList().get(i).getTitle());
        }
  
    }*/
}
