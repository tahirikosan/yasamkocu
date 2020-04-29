
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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
    //column names for BLOG table
    private int BLOG_ID = 1;
    private int BLOG_TITLE = 2;
    private int BLOG_AUTHOR = 3;
    private int BLOG_CONTENT = 4;
    private int BLOG_DATE = 5;
    private int BLOG_LABEL = 6;
    private int BLOG_IMAGE_URL = 7;
    
    //column names for FIT_RECIPES table
    private int FIT_RECIPES_ID = 1;
    private int FIT_RECIPES_TITLE = 2;
    private int FIT_RECIPES_CONTENT = 3;
    private int FIT_RECIPES_SERVINGS = 4;
    private int FIT_RECIPES_PREP = 5;
    private int FIT_RECIPES_LABEL = 6;
    private int FIT_RECIPES_IMAGE_URL = 7;
    
    //column names for USER_RECIPES table
    private int USER_RECIPES_ID = 1;
    private int USER_RECIPES_USER_ID = 2;
    private int USER_RECIPES_READING_DATE = 3;
    private int USER_RECIPES_RECIPE_ID = 4;
    
    //database connection config
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
    
    public boolean UserRecipeTried(int userid, int recipeid){
        if(conn == null){
            connect();
        }
        
        try{
           
            String query = "INSERT INTO LIFECOACH.USER_RECIPES (USERID, READINGDATE, RECIPEID) VALUES (?,?,?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(USER_RECIPES_USER_ID, userid);
            Date utilDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            statement.setDate(USER_RECIPES_READING_DATE, sqlDate);
            statement.setInt(USER_RECIPES_RECIPE_ID, recipeid);
            
            int result = statement.executeUpdate();
          
            
            if(result == 1){
                return true;
            }else{
                return false;
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        
        return false;
    }
    
    
    /*
    //returns records with matching label from USER_RECIPES table as a List<Recipe> object
    public List<UserRecipe> userRecipeList(int userid) {
        if(conn == null){
            System.out.println("Bağlantı sağlanamadı, yeniden bağlanıyor...");
            connect();
        }
        
        List<UserRecipe> userRecipes = new ArrayList<UserRecipe>();
        
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM LIFECOACH.USER_RECIPES WHERE USERID="+userid);
            
            while(result.next()){
              UserRecipe userRecipe = new UserRecipe();
              userRecipe.setId(result.getInt(USER_RECIPES_ID));
              userRecipe.setUserid(result.getInt(USER_RECIPES_USER_ID));
              userRecipe.setRecipeid(result.getInt(USER_RECIPES_RECIPE_ID));
              userRecipe.setReadingDate(result.getDate(USER_RECIPES_READING_DATE));
              
              Statement statement2 = conn.createStatement();
              ResultSet result2 = statement.executeQuery("SELECT * FROM LIFECOACH.FIT_RECIPES WHERE RECIPEID="+);
              
              userRecipes.add(userRecipe);
            }
            result.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("Veriler getirilirken bir hata oluştu! \n" + e.toString());
 
        }
       
        return userRecipes;
    }*/
    
    
    //returns all the record from FIT_RECIPES table as a List<Recipe> object
    public List<Recipe> recipeList() {
        if(conn == null){
            System.out.println("Bağlantı sağlanamadı, yeniden bağlanıyor...");
            connect();
        }
        
        List<Recipe> recipes = new ArrayList<Recipe>();
        
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM LIFECOACH.FIT_RECIPES");
            
            while(result.next()){
              Recipe recipe = new Recipe();
              recipe.setId(result.getInt(FIT_RECIPES_ID));
              recipe.setTitle(result.getString(FIT_RECIPES_TITLE));
              recipe.setContent(result.getString(FIT_RECIPES_CONTENT));
              recipe.setLabel(result.getString(FIT_RECIPES_LABEL));
              recipe.setImageUrl(result.getString(FIT_RECIPES_IMAGE_URL));
              recipe.setServings(result.getInt(FIT_RECIPES_SERVINGS));
              recipe.setPrepTime(result.getInt(FIT_RECIPES_PREP));
              recipes.add(recipe);
            }
            result.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("Veriler getirilirken bir hata oluştu! \n" + e.toString());
 
        }
       
        return recipes;
    }
    
    //returns one Recipe object with the given id
    public Recipe findRecipe(int id) {
        if(conn == null){
            System.out.println("Bağlantı sağlanamadı, yeniden bağlanıyor...");
            connect();
        }
        
        Recipe recipe = new Recipe();
        
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM LIFECOACH.FIT_RECIPES WHERE ID=" + id);
            
            result.next();
            
            recipe.setId(result.getInt(FIT_RECIPES_ID));
            recipe.setTitle(result.getString(FIT_RECIPES_TITLE));
            recipe.setContent(result.getString(FIT_RECIPES_CONTENT));
            recipe.setLabel(result.getString(FIT_RECIPES_LABEL));
            recipe.setImageUrl(result.getString(FIT_RECIPES_IMAGE_URL));
            recipe.setServings(result.getInt(FIT_RECIPES_SERVINGS));
            recipe.setPrepTime(result.getInt(FIT_RECIPES_PREP));
            result.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("Veriler getirilirken bir hata oluştu! \n" + e.toString());
 
        }
       
        return recipe;
    }
    
    //returns all the record from BLOG table as a List<Blog> object
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

    
    //returns one Blog object with the given id
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
    
    public static void main(String[] args){
        DBLayerEda db = new DBLayerEda();
        boolean sonuc = db.UserRecipeTried(8, 3);
    }

    
}
