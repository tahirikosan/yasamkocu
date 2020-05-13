import java.io.Serializable;
import static java.lang.Math.pow;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Profil implements Serializable{

    private float basalMetabolism;
    private float bodyMassIndex;
    private int dailyReceivedCalories;
    private int dailyGıvenCalories;
    private int receivedCaloriesList[];
    private int givenCaloriesList[];
    private int triedRecipesList[] = new int[3];
    private int weeklyTriedRecipe[] = new int[7];


   public Profil() {
        
    }


    public float getBasalMetabolism() {
        return basalMetabolism;
    }

    public void setBasalMetabolism(float basalMetabolism) {
        this.basalMetabolism = basalMetabolism;
    }

    public float getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(float bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    public float getDailyReceivedCalories() {
        return dailyReceivedCalories;
    }

    public void setDailyReceivedCalories(int dailyReceivedCalories) {
        this.dailyReceivedCalories = dailyReceivedCalories;
    }

    public int getDailyGıvenCalories() {
        return dailyGıvenCalories;
    }

    public void setDailyGıvenCalories(int dailyGıvenCalories) {
        this.dailyGıvenCalories = dailyGıvenCalories;
    }

    public int[] getReceivedCaloriesList() {
        return receivedCaloriesList;
    }

    public void setReceivedCaloriesList(int[] receivedCaloriesList) {
        this.receivedCaloriesList = receivedCaloriesList;
    }

    public int[] getGivenCaloriesList() {
        return givenCaloriesList;
    }

    public void setGivenCaloriesList(int[] givenCaloriesList) {
        this.givenCaloriesList = givenCaloriesList;
    }

    public int[] getTriedRecipesList() {
        return triedRecipesList;
    }

    public void setTriedRecipesList(int[] triedRecipesList) {
        this.triedRecipesList = triedRecipesList;
    }

    public int[] getWeeklyTriedRecipe() {
        return weeklyTriedRecipe;
    }

    public void setWeeklyTriedRecipe(int[] weeklyTriedRecipe) {
        this.weeklyTriedRecipe = weeklyTriedRecipe;
    }
    
    
    
    
    public float calculateBasalMetabolism(User user){
        if(user.getGender() == "female"){
            basalMetabolism = (float) (655 + (9.6 * (user.getWeight())) + (1.8 * user.getHeight()) - (4.7 * user.getAge()));
        }else{
            basalMetabolism = (float) (65.5 + (13.7 * (user.getWeight())) + (5 * user.getHeight()) - (6.7 * user.getAge()));
        }
        return basalMetabolism;
    }
    
    public float calculateBodyMassIndex(User user){
        bodyMassIndex = (float) (user.getWeight()/pow(user.getHeight(), 2));
        return bodyMassIndex;
    }
    
    public int calculateDailyReceivedCalories(int id){
        DBLayerZeyno db = new DBLayerZeyno();
        int dondur = db.getTotalCalNutrition(id);
        System.out.println(dondur);
        return dondur;
    }
    
    public int calculateDailyGivenCalories(int id){
        DBLayerZeyno db = new DBLayerZeyno();
        int dondur = db.getTotalCalExercise(id);
        System.out.println(dondur);
        return dondur;
    }
    
    
    public void getRecipeNumbersByLabel(int id){
        DBLayerEda db = new DBLayerEda();
        triedRecipesList[0] = db.userRecipeNumberByLabel(id, "vegan");
        triedRecipesList[1] = db.userRecipeNumberByLabel(id, "vejetaryen");
        triedRecipesList[2] = db.userRecipeNumberByLabel(id, "normal");
    }
    
    public void getRecipeNumbersByLabelAndDate(int id){
        DBLayerEda db = new DBLayerEda();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        
        for(int i = 0; i < 7; i++){
            Date date = new Date(System.currentTimeMillis()-i*24*60*60*1000);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            weeklyTriedRecipe[i] = db.userRecipeNumberByDate(id, sqlDate);
        }
    }
    
}
