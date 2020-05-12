
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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



public class DBLayerTahir {
    
    //column names
    private int NAME = 1;
    private int SURNAME = 2;
    private int PASSWORD = 3;
    private int EMAIL = 4;
    private int GENDER = 5;
    private int HEIGHT = 6;
    private int WEIGHT = 7;
    private int AGE = 8; 
    
    
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
              return result.getString(EMAIL) + " " + result.getString(NAME) + " " + result.getString(PASSWORD);
            }
            
        }catch(SQLException e){
            System.out.println("Veriler getirilirken bir hata oluştu! \n" + e.toString());
        }
        
        return "null_2";
    }
    
    
    // Register a new user
    public boolean registerUser(User user){
        if(conn == null){
            connect();
        }
        
        try{
            
            String lookUser = "SELECT * FROM kullanici WHERE email = '" +user.getEmail()+"'";
            Statement statement0 = conn.createStatement();
            ResultSet rs = statement0.executeQuery(lookUser);
            int id = 0;
            while(rs.next()){
                id = rs.getInt(1);
            }
            
            if(id != 0){
                return false;
            }
           
            String query = "INSERT INTO kullanici (name, surname, password, email, gender, height, weight, age) VALUES (?,?,?,?,?,?,?,?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(NAME, user.getName());
            statement.setString(SURNAME, user.getSurname());
            statement.setString(PASSWORD, user.getPassword());
            statement.setString(EMAIL, user.getEmail());
            statement.setString(GENDER, user.getGender());
            statement.setInt(HEIGHT, user.getHeight());
            statement.setInt(WEIGHT, user.getWeight());
            statement.setInt(AGE, user.getAge());
           
           
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
    
    // Login
    public User loginUser(User user){
        if(conn == null){
            connect();
        }
        User tmpUser = new User();
        tmpUser.setId(-1);
        try {
            String query = "SELECT * FROM kullanici WHERE email = '" +user.getEmail()+ "' AND password = '" +user.getPassword()+"'";
            Statement statement = conn.createStatement();
            
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()){
                
                return new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),result.getString(5),result.getString(6), result.getInt(7), result.getInt(8), result.getInt(9));
            }
            
            return tmpUser;
        
        } catch (Exception e) {
            System.out.println("Login hata " + e.toString());
        }
        
        return tmpUser;
    }
    
    public boolean updateUser(User user){
        
        if(conn == null){
            connect();
        }
        
        try {
            String query = "SELECT * FROM kullanici WHERE id = " +user.getId()+"";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
       
            while(result.next()){
                System.out.println(result.getString(2));
                query = "UPDATE kullanici SET name='"+user.getName()+"', surname='"+user.getSurname()+"', password='"+user.getPassword()+"', email = '"+user.getEmail()+"', gender='"+user.getGender()+"',  height="+user.getHeight()+", weight="+user.getWeight()+", age="+user.getAge()+" WHERE id = " +result.getInt(1)+ "";
                int rs = statement.executeUpdate(query);
                
                if(rs == 1){
                    return true;
                }else{
                    return false;
                }
            }
            
        } catch (Exception e) {
            System.out.println("Update user error " + e.toString());
        }  
        
        return false;
    }
    
    public static void main(String[] args) {
        DBLayerTahir db = new DBLayerTahir();
        
        db.connect();
        
        //User user = new User(1, "degıstı",  "c@gmail.com",  "Silgi123",  "degıstı", 100,  "degıstı", 99, 0);
        //db.registerUser(user);
        //db.loginUser(user);
        //db.updateUser(user);
        
        
    }
    
 
    
}
