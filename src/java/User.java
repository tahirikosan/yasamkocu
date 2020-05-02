
import java.text.DateFormat;
import java.util.Date;
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
 * @author Tahir
 */
@ManagedBean
@SessionScoped
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private String surname;
    private int age;
    private String gender;
    private int height;
    private int weight;
    
    private String password_c;
    private String errorRegister; 
    private String errorLogin;

    public String getErrorRegister() {
        return errorRegister;
    }

    public void setErrorRegister(String errorRegister) {
        this.errorRegister = errorRegister;
    }
    
    
    public User(){
    }

    public User(int id, String name, String email, String password, String surname, int age, String gender, int height, int weight) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public String getErrorLogin() {
        return errorLogin;
    }

    public void setErrorLogin(String errorLogin) {
        this.errorLogin = errorLogin;
    }

    public String getPassword_c() {
        return password_c;
    }

    public void setPassword_c(String password_c) {
        this.password_c = password_c;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

 
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

 

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String register(){
        
        if(validate()){
             DBLayerTahir db = new DBLayerTahir();
              db.connect();
            boolean result = db.registerUser(this);

            if(result){
               return "login.xhtml";
            }else{
                errorRegister = "Kayıt işlemi yapılamadı lütfen tekrar deneyiniz.";
               return "register.xhtml";
            }
        }else{
            errorRegister = "Şifreler aynı olmalı";
            return "register.xhtml";
        }
       
    }
    
    private boolean validate(){
        
        if(password.equals(password_c)){
            return true;
        }
        
        return false;
    }
    
    public String login(){
        DBLayerTahir db = new DBLayerTahir();
        db.connect();
        this.id = db.loginUser(this);
        
        if(this.id != -1){
            return "main_menu.xhtml";
        }else{
            errorLogin = "Kullanıcı adı veya şifre yanlış";
            return "login.xhtml";
        }
    }
    
    
    public String update(){
       
        if(validate()){
           DBLayerTahir db = new DBLayerTahir();
           db.connect();
           boolean result = db.updateUser(this);
           
           if(result){
               return "profile.xhtml";
           }else{
               return "login.xhtml";
           }
        }else{
            return "main_menu.xhtml";
        }
       
    }
       
}
