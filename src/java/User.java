
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private int id = -1;
    private String name ;
    private String password;
    private String email ;
    private String surname;
    private int age;
    private String gender;
    private int height;
    private int weight;
    
    private String password_c;
    private String errorRegister; 
    private String errorLogin;
    private String errorSettings;
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public String getErrorSettings() {
        return errorSettings;
    }

    public void setErrorSettings(String errorSettings) {
        this.errorSettings = errorSettings;
    }

    public String getErrorRegister() {
        return errorRegister;
    }

    public void setErrorRegister(String errorRegister) {
        this.errorRegister = errorRegister;
    }
    
    
    public User(){
    }

    public User(int id, String name, String surname, String password, String email, String gender, int height, int weight, int age) {
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
        
        if(validate().equals("Okey")){
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
            errorRegister = validate();
            return "register.xhtml";
        }
       
    }
    
    private String validate(){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        this.name = this.name.trim();
        this.surname = this.surname.trim();
        this.password = this.password.trim();
        this.password_c = this.password_c.trim();
        this.email = this.email.trim();
        
       if(name == null || surname== null || email== null || age == 0 || height == 0 || weight == 0 || gender == null){
            return "Lütfen boş kısım bırakmayın.";
        }else if(name.isEmpty() || surname.isEmpty() || email.isEmpty() || age == 0 || height == 0 || weight == 0 || gender == null){
            return "Lütfen boş kısım bırakmayın.";
        }else if(!password.equals(password_c)){
            return "Şifreler aynı olmalı";
        }else if(password.length() < 6){
           return "Şifre uzunluğu min. 6 karakter olmalı";
        }else if(!matcher.find()){
            return "Lütfen geçerli bir email adresi giriniz.";
        }
        
        return "Okey";
    }
    
    public String login(){
        DBLayerTahir db = new DBLayerTahir();
        db.connect();
        User newData = db.loginUser(this);
        this.id = newData.id;
        
        if(this.id != -1){
            
            this.name = newData.name;
            this.surname = newData.surname;
            this.email = newData.email;
            this.password = newData.password;
            this.password_c = newData.password;
            this.age = newData.age;
            this.gender = newData.gender;
            this.height = newData.height;
            this.weight = newData.weight;
     
            
            return "main_menu.xhtml";
        }else{
            errorLogin = "Kullanıcı adı veya şifre yanlış";
            return "login.xhtml";
        }
    }
    
    
    public String update(){
       
        if(validate().equals("Okey")){
           DBLayerTahir db = new DBLayerTahir();
           db.connect();
           boolean result = db.updateUser(this);
           
           if(result){
               return "profile.xhtml";
           }else{
               errorSettings = "Kaydetme işlemi yapılamadı tekrar deneyiniz.";
               return "settings.xhtml";
           }
        }else{
            errorSettings = validate();
            return "settings.xhtml";
        }
       
    }
       
}
