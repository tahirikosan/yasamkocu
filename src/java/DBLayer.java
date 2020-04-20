
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
 * @author Tahir
 */





public class DBLayer {
    
    //column names
    private int ID = 1;
    private int NAME = 2;
    private int PASSWORD = 3;
    
    
    private Connection conn;
    private String dbUrl = "jdbc:derby://localhost:1527/yasam_kocu";
    private String dbUser = "icecream";
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
    
    //select a user
    public String  userList(){
        if(conn == null){
            System.out.println("Bağlantı sağlanamadı, yeniden bağlanıyor...");
            connect();
        }
        
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM kullanici");
            
            while(result.next()){
              return result.getString(ID) + " " + result.getString(NAME) + " " + result.getString(PASSWORD);
            }
            
        }catch(SQLException e){
            System.out.println("Veriler getirilirken bir hata oluştu! \n" + e.toString());
        }
        
        return "null_2";
    }
    
    
 
    
}
