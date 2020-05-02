
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zeyno
 */

@ManagedBean(name="zeyno")
@SessionScoped
public class DBLayerZeyno implements Serializable{ 
    private int NAME = 1;
    private int CAL = 2;
    private int IMAGEURL = 3;
    
    
    private int USERID = 4;
    private String NDATE="" ;
    private int NUTRITIONID = 5;
    private int NUTRITIONGR = 6;
    
    private Connection conn;
    private String dbUrl = "jdbc:derby://localhost:1527/lifecoach";
    private String dbUser = "lifecoach";
    private String dbPass = "123";
    
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
     
     
     public List<Nutrition> getNutritions(int userid) {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = connect();
      String stm = "Select * from nutrition where nutrition.userid="+userid;
      List<Nutrition> records = new ArrayList<Nutrition>();
      
      try {
        
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();
         
         while(rs.next()) {
            Nutrition nutrition = new Nutrition();
            nutrition.setId(rs.getInt(1));
            nutrition.setName(rs.getString(2));
            nutrition.setCal(rs.getInt(3));
            nutrition.setImageurl(rs.getString(4));
           //System.out.println(nutrition.name);
            records.add(nutrition);				
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      for(int i=0; i<records.size();i++)
      System.out.println(records.get(i).name);
      return records;
   }
     
      public List<UserNutrition> getUserNutritions(int userid) {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = connect();
      String stm = "SELECT USER_NUTRITION.ID,USER_NUTRITION.USERID,USER_NUTRITION.NUTRITIONID,USER_NUTRITION.NUTRITIONGR,USER_NUTRITION.NDATE,NUTRITION.NAME FROM LIFECOACH.USER_NUTRITION INNER JOIN NUTRITION ON USER_NUTRITION.NUTRITIONID=NUTRITION.ID WHERE USER_NUTRITION.USERID="+userid;
      List<UserNutrition> records = new ArrayList<UserNutrition>();
      
      try {
        
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();
         
         while(rs.next()) {
           UserNutrition nutrition = new UserNutrition();
            nutrition.setId(rs.getInt(1));
              nutrition.setUserid(rs.getInt(2));
                nutrition.setNutritionid(rs.getInt(3));
                  nutrition.setNutritiongr(rs.getInt(4));
                    nutrition.setNdate(rs.getString(5));
           nutrition.setName(rs.getString(6));
       
            records.add(nutrition);				
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
 
      return records;
   }
    
      public int getTotalCalNutrition(int userid) {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = connect();
      String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
      String stm = "SELECT SUM(USER_NUTRITION.NUTRITIONGR*NUTRITION.CAL) FROM LIFECOACH.USER_NUTRITION INNER JOIN NUTRITION ON USER_NUTRITION.NUTRITIONID=NUTRITION.ID WHERE USER_NUTRITION.NDATE='"+ date+"' AND USER_NUTRITION.USERID="+userid+" GROUP BY USER_NUTRITION.NDATE";
       int cal=0;
      try {
        
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();
         while(rs.next()){
         cal=rs.getInt(1);
         } 
         
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Error Data : " + e.getMessage());
      }
       return cal;
   }
   
      
   public boolean AddNutrition(Nutrition nutrition,int userid){
        if(conn == null){
            connect();
        }
        
        try{
           
            String query = "INSERT INTO nutrition(name, cal, imageurl,userid) VALUES (?,?,?,?)";
         
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(NAME, nutrition.getName());
            statement.setInt(CAL, nutrition.getCal());
            statement.setString(IMAGEURL, nutrition.getImageurl());
            statement.setInt(4,userid);
        
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

      public String DeleteUserNutrition(int usernutritionid){
        if(conn == null){
            connect();
        }
        try{
           
            String query = "DELETE FROM USER_NUTRITION WHERE ID="+usernutritionid;
            PreparedStatement statement = conn.prepareStatement(query);
            int result = statement.executeUpdate();
          
            
            if(result>0){
                return "nutrition.xhtml";
            }else{
                 return "basarisiz.xhtml";
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return "";
    }
          public String DeleteNutrition(int nutritionid,int userid){
        if(conn == null){
            connect();
        }
        try{
           
            String query = "DELETE FROM NUTRITION WHERE USERID="+userid+"AND ID="+nutritionid;
            PreparedStatement statement = conn.prepareStatement(query);
            int result = statement.executeUpdate();
          
            
            if(result>0){
                return "nutrition.xhtml";
            }else{
                 return "basarisiz.xhtml";
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return "";
    }
       
      
      public void AddUserNutrition(int nutritionid,int usernutritiongr,int userid){
        if(conn == null){
            connect();
        }
         String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        try{
           
           String query = "INSERT INTO USER_NUTRITION(USERID,NDATE,NUTRITIONID, NUTRITIONGR) VALUES (?,?,?,?)";
         
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userid);
            statement.setString(2,date);
            statement.setInt(3,nutritionid);
            statement.setInt(4, usernutritiongr);
            
            int result = statement.executeUpdate();
      
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
      
    public boolean AddExercise(Exercise exercise,int userid){
        if(conn == null){
            connect();
        }
        
        try{
           
            String query = "INSERT INTO exercise(name, cal, imageurl,userid) VALUES (?,?,?,?)";
         
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(NAME, exercise. getName());
            statement.setInt(CAL, exercise.getCal());
            statement.setString(IMAGEURL, exercise.getImageurl());
            statement.setInt(4,userid);
            
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
      public List<Exercise> getExercises(int userid) {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = connect();
      String stm = "Select * from exercise where exercise.userid="+userid;
      List<Exercise> records = new ArrayList<Exercise>();
      
      try {
        
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();
         
         while(rs.next()) {
            Exercise exercise = new Exercise();
            exercise.setId(rs.getInt(1));
            exercise.setName(rs.getString(2));
            exercise.setCal(rs.getInt(3));
            exercise.setImageurl(rs.getString(4));
            records.add(exercise);				
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      for(int i=0; i<records.size();i++)
      System.out.println(records.get(i).name);
      return records;
   }
      
      
       public void AddUserExercise(Exercise exercise ,UserExercise userexercise,int userid){
        if(conn == null){
            connect();
        }
         String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try{
           
            String query = "INSERT INTO USER_EXERCISE(USERID,EDATE, EXERCISEID, EXERCISETIME) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,userid);
            statement.setString(2,date);
            statement.setInt(3, exercise.getId());
           statement.setInt(4, userexercise.getExercisetime());
            
            int result = statement.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
            
 public String DeleteUserExercise(int userexerciseid){
        if(conn == null){
            connect();
        }
        try{
           
            String query = "DELETE FROM USER_EXERCISE WHERE ID="+userexerciseid;
            PreparedStatement statement = conn.prepareStatement(query);
            int result = statement.executeUpdate();
          
            
            if(result>0){
                return "exercise.xhtml";
            }else{
                 return "basarisiz.xhtml";
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        
        return "";
    }
   
  public List<UserExercise> getUserExercises(int userid) {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = connect();
      String stm = "SELECT USER_EXERCISE.ID,USER_EXERCISE.USERID,USER_EXERCISE.EXERCISEID,USER_EXERCISE.EXERCISETIME,USER_EXERCISE.EDATE,EXERCISE.NAME FROM LIFECOACH.USER_EXERCISE INNER JOIN EXERCISE ON USER_EXERCISE.EXERCISEID=EXERCISE.ID WHERE USER_EXERCISE.USERID="+userid;
      List<UserExercise> records = new ArrayList<UserExercise>();
      
      try {
        
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();
         
         while(rs.next()) {
           UserExercise exercise = new UserExercise();
            exercise.setId(rs.getInt(1));
              exercise.setUserid(rs.getInt(2));
                exercise.setExerciseid(rs.getInt(3));
                  exercise.setExercisetime(rs.getInt(4));
                    exercise.setEdate(rs.getString(5));
           exercise.setName(rs.getString(6));
       
            records.add(exercise);				
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
 
      return records;
   }
  
  public int getTotalCalExercise(int userid) {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = connect();
      String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
      String stm = "SELECT SUM(USER_EXERCISE.EXERCISETIME*EXERCISE.CAL) FROM LIFECOACH.USER_EXERCISE INNER JOIN EXERCISE ON USER_EXERCISE.EXERCISEID=EXERCISE.ID WHERE USER_EXERCISE.EDATE='"+ date+"'AND USER_EXERCISE.USERID="+userid+" GROUP BY USER_EXERCISE.EDATE";
      int cal=0;
      try {
        
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();
         while(rs.next()){
        cal=rs.getInt(1);
         }   
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Error Data : " + e.getMessage());
      }
       return cal;
   }
   public String DeleteExercise(int exerciseid,int userid){
        if(conn == null){
            connect();
        }
        try{
           
            String query = "DELETE FROM EXERCISE WHERE USERID="+userid+"AND ID="+exerciseid;
            PreparedStatement statement = conn.prepareStatement(query);
            int result = statement.executeUpdate();
          
            
            if(result>0){
                return "exercise.xhtml";
            }else{
                 return "basarisiz.xhtml";
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return "";
    }
      
   public static void main(String[] args) {
        DBLayerZeyno db = new DBLayerZeyno();
        //UserNutrition x= new UserNutrition();
       
       //db.connect();
       //db.getExercises();
      //db.AddUserNutrition(x);
       //Date today = new Date(); 
       //System.out.println(today);
       

//String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//System.out.println(date);
   // db.connect();
   //db.getTotalCalNutrition();
  // db.getTotalCalExercise();
    }
 } 