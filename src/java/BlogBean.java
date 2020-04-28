
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

@ManagedBean(name="blogBean")
@SessionScoped
public class BlogBean implements Serializable{
    private DBLayerEda db = new DBLayerEda();
    private List<Blog> blogList = db.blogList();
    private int selectedBlogID = 2;
    private Blog selectedBlog = new Blog();
    
    public void BlogBean() {
        
    }

    public int getSelectedBlogID() {
        return selectedBlogID;
    }

    public void setSelectedBlogID(int selectedBlogID) {
        this.selectedBlogID = selectedBlogID;
    }

    public DBLayerEda getDb() {
        return db;
    }

    public void setDb(DBLayerEda db) {
        this.db = db;
    }

    public Blog getSelectedBlog() {
        return selectedBlog;
    }

    public void setSelectedBlog(Blog selectedBlog) {
        this.selectedBlog = selectedBlog;
    }
    

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }
    
    public void findBlog(){
        //System.out.println(this.selectedBlogID);
        this.selectedBlog =  db.findBlog(this.selectedBlogID);
        //return "blogDisplay.xhtml";
    }
    
    /* public static void main(String[] args){
        BlogBean yeniBean = new BlogBean();
        System.out.println(yeniBean.getSelectedBlog().getTitle());
  
    }*/
}
