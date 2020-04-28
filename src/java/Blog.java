/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eda
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="BlogBean")
@SessionScoped
public class Blog implements Serializable{
    private int id;
    private String title;
    private String author;
    private String content;
    private Date date;
    private String label;
    private String imageUrl;
    private List<Blog> blogs;
    
    public Blog(){
        
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public List<Blog> getBlogList() {
        DBLayerEda db = new DBLayerEda();
        blogs = new ArrayList();
        blogs = db.blogList();
        return blogs;
    }
    
    public String getBlog(){
        DBLayerEda db = new DBLayerEda();
        Blog selectedBlog = new Blog();
        selectedBlog = db.findBlog(id);
        this.author = selectedBlog.getAuthor();
        this.content = selectedBlog.getContent();
        this.date = selectedBlog.getDate();
        this.imageUrl = selectedBlog.getImageUrl();
        this.label = selectedBlog.getLabel();
        this.title = selectedBlog.getTitle();
        
        return "blogDisplay";
    }
    
}
