
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    private int BLOG_ID = 0;
    private int BLOG_TITLE = 1;
    private int BLOG_AUTHOR = 2;
    private int BLOG_CONTENT = 3;
    private int BLOG_DATE = 4;
    private int BLOG_LABEL = 5;
    
    
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
    
    public String  blogList(){
        if(conn == null){
            System.out.println("Bağlantı sağlanamadı, yeniden bağlanıyor...");
            connect();
        }
        
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM LIFECOACH.BLOG");
            
            while(result.next()){
              return result.getString(BLOG_ID) + " " + result.getString(BLOG_TITLE) + " " + result.getString(BLOG_AUTHOR);
            }
            
        }catch(SQLException e){
            System.out.println("Veriler getirilirken bir hata oluştu! \n" + e.toString());
        }
        
        return "null_2";
    }
}
