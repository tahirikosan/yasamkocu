
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eda
 */
public class DBLayerEda {
    //column names
    private int BLOG_ID = 1;
    private int BLOG_TITLE = 2;
    private int BLOG_AUTHOR = 3;
    private int BLOG_CONTENT = 4;
    private int BLOG_DATE = 5;
    private int BLOG_LABEL = 6;
    private int BLOG_IMAGE_URL = 7;
    
    
    private Connection conn;
    private String dbUrl = "jdbc:derby://localhost:1527/lifecoach";
    private String dbUser = "lifecoach";
    private String dbPass = "123";
    
    
    
    //create db connection
    public Connection connect(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            System.out.println("Bağlantı Başarılı");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        }catch(Exception e){
            System.out.println("Bağlantı oluşturulurken bir hata oluştu! \n " + e.toString());
        }
        
        return conn;
    }
    
    public List<Blog> blogList() {
        if(conn == null){
            System.out.println("Bağlantı sağlanamadı, yeniden bağlanıyor...");
            connect();
        }
        
        List<Blog> blogs = new ArrayList<Blog>();
        
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM LIFECOACH.BLOG");
            
            while(result.next()){
              Blog blog = new Blog();
              blog.setId(result.getInt(BLOG_ID));
              blog.setTitle(result.getString(BLOG_TITLE));
              blog.setAuthor(result.getString(BLOG_AUTHOR));
              blog.setContent(result.getString(BLOG_CONTENT));
              blog.setAuthor(result.getString(BLOG_AUTHOR));
              blog.setDate(result.getDate(BLOG_DATE));
              blog.setLabel(result.getString(BLOG_LABEL));
              blog.setImageUrl(result.getString(BLOG_IMAGE_URL));
              //System.out.println(blog.getImageUrl());
              blogs.add(blog);
            }
            result.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("Veriler getirilirken bir hata oluştu! \n" + e.toString());
 
        }
       
        return blogs;
    }
    
    public Blog findBlog(int id) {
        if(conn == null){
            System.out.println("Bağlantı sağlanamadı, yeniden bağlanıyor...");
            connect();
        }
        
        Blog blog = new Blog();
        
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM LIFECOACH.BLOG WHERE ID=" + id);
            
            result.next();
            
            blog.setId(result.getInt(BLOG_ID));
            blog.setTitle(result.getString(BLOG_TITLE));
            blog.setAuthor(result.getString(BLOG_AUTHOR));
            blog.setContent(result.getString(BLOG_CONTENT));
            blog.setAuthor(result.getString(BLOG_AUTHOR));
            blog.setDate(result.getDate(BLOG_DATE));
            blog.setLabel(result.getString(BLOG_LABEL));
            blog.setImageUrl(result.getString(BLOG_IMAGE_URL));
            result.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("Veriler getirilirken bir hata oluştu! \n" + e.toString());
 
        }
       
        return blog;
    }
    
    /*public static void main(String[] args){
        DBLayerEda db = new DBLayerEda();
        Blog yeni = db.findBlog(2);
    }*/
}
