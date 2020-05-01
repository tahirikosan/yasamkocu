
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name="RecipeBean")
@SessionScoped
public class Recipe {
    private int id;
    private String title;
    private String content;
    private String label;
    private int servings;
    private int prepTime;
    private String imageUrl;
    private List<Recipe> recipes;

    public Recipe() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipeList() {
        DBLayerEda db = new DBLayerEda();
        recipes = new ArrayList();
        recipes = db.recipeList();
        return recipes;
    } 
    
    public String getRecipe(int id){
        DBLayerEda db = new DBLayerEda();
        Recipe selectedRecipe = new Recipe();
        selectedRecipe = db.findRecipe(id);
        this.id = id;
        this.content = selectedRecipe.getContent();
        this.imageUrl = selectedRecipe.getImageUrl();
        this.label = selectedRecipe.getLabel();
        this.title = selectedRecipe.getTitle();
        this.servings = selectedRecipe.getServings();
        this.prepTime = selectedRecipe.getPrepTime();
        
        return "recipeDisplay";
    }
    
}
