
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
@ManagedBean
@SessionScoped
public class BlogBean {
    private List<Blog> blogList;

    public BlogBean() {
        DBLayerEda db = new DBLayerEda();
        db.connect();
        this.blogList = db.blogList();
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }
    
    
}
