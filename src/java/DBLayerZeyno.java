
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zeyno
 */
@ManagedBean
@RequestScoped
public class DBLayerZeyno implements Serializable{ 
    private int NAME = 1;
    private int CAL = 2;
    private int IMAGEURL = 3;
    
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
     
     
     public List<Nutrition> getNutritions() {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = connect();
      String stm = "Select * from nutrition";
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
     
     
     
     
   public boolean AddNutrition(Nutrition nutrition){
        if(conn == null){
            connect();
        }
        
        try{
           
            String query = "INSERT INTO nutrition(name, cal, imageurl) VALUES (?,?,?)";
         
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(NAME, nutrition.getName());
            statement.setInt(CAL, nutrition.getCal());
            statement.setString(IMAGEURL, nutrition.getImageurl());
            
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
   
  /* List<Nutrition> sorgusonucu; //datalari donduren liste
   
    public List<Nutrition> getSorgusonucu() {
        return sorgusonucu;
    }

    public void setSorgusonucu(List<Nutrition> sorgusonucu) {
        this.sorgusonucu = sorgusonucu;
    }

    public List<Nutrition> getTablodakiKayitlar(){
   
    sorgusonucu = new ArrayList<>(); 
    
     if(conn == null){
            connect();
        }
        try{
        //String query = "select * from nutrition";
        PreparedStatement preparedStatement=null;
        ResultSet result=null;
        preparedStatement=conn.prepareStatement("select * from nutrition");
        result=preparedStatement.executeQuery();
         while(result.next()){
             Nutrition nut =new Nutrition();
             nut.setId(result.getInt(1));
             nut.setName(result.getString(2));
             nut.setCal(result.getInt(3));
             nut.setImageurl(result.getString(4));
             sorgusonucu.add(nut);
         }}catch (Exception e) {
            System.err.println("Hata Meydana Geldi. Hata:"+e);
        }
        return sorgusonucu;                
    } 
    */
   
   public static void main(String[] args) {
        DBLayerZeyno db = new DBLayerZeyno();
       // db.connect();
        db.getNutritions();
        
        
    }
}