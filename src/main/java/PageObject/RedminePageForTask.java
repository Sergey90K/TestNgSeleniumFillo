package PageObject;

import java.util.ArrayList;

public class RedminePageForTask {
    public String urlPage(){
        return "https://www.redmine.org/account/register";
    }
    public String homePageRefXpas(){
        return "//*[@id=\"top-menu\"]/ul/li[1]/a";
    }
    public String homePageRefUrl(){
        return "https://www.redmine.org/";
    }
    public String progectRefXpas(){
        return "//*[@id=\"top-menu\"]/ul/li[2]/a";
    }
    public String progectRefUrl(){
        return "https://www.redmine.org/projects";
    }
    public String helpRefXpas(){
        return "//*[@id=\"top-menu\"]/ul/li[3]/a";
    }
    public String helpRefUrl(){
        return "https://www.redmine.org/guide";
    }
    public String enterRefXpas(){
        return "//*[@id=\"account\"]/ul/li[1]/a";
    }
    public String enterRefUrl(){
        return "https://www.redmine.org/login";
    }
    public String regidtrationsRefXpas(){
        return "//*[@id=\"account\"]/ul/li[2]/a";
    }
    public String regidtrationsRefUrl(){
        return "https://www.redmine.org/account/register";
    }
    public String searchRefXpas(){
        return "//*[@id=\"quick-search\"]/form/label/a";
    }
    public String searchRefUrl(){
        return "https://www.redmine.org/search";
    }
    public ArrayList<String> allRefXpas(){
        ArrayList<String> allXpas = new ArrayList<String>();
        allXpas.add(this.homePageRefXpas());
        allXpas.add(this.progectRefXpas());
        allXpas.add(this.helpRefXpas());
        allXpas.add(this.enterRefXpas());
        allXpas.add(this.regidtrationsRefXpas());
        allXpas.add(this.searchRefXpas());
        return allXpas;
    }
    public ArrayList<String> allRefUrl(){
        ArrayList<String> allUrl = new ArrayList<String>();
        allUrl.add(this.homePageRefUrl());
        allUrl.add(this.progectRefUrl());
        allUrl.add(this.helpRefUrl());
        allUrl.add(this.enterRefUrl());
        allUrl.add(this.regidtrationsRefUrl());
        allUrl.add(this.searchRefUrl());
        return allUrl;
    }
    public String userField(){
        return "//*[@id=\"user_login\"]";
    }
    public String passwordField(){
        return "//*[@id=\"user_password\"]";
    }
    public String secondPasswordField(){
        return "//*[@id=\"user_password_confirmation\"]";
    }
    public String nameField(){
        return "//*[@id=\"user_firstname\"]";
    }
    public String lastNameField(){
        return "//*[@id=\"user_lastname\"]";
    }
    public String emailField(){
        return "//*[@id=\"user_mail\"]";
    }
    public String ircNickField(){
        return "//*[@id=\"user_custom_field_values_3\"]";
    }
    public String searchField(){
        return "//*[@id=\"q\"]";
    }
    public ArrayList<String > allFieldXpas(){
        ArrayList <String> fieldLinc = new ArrayList<String>();
        fieldLinc.add(this.userField());
        fieldLinc.add(this.passwordField());
        fieldLinc.add(this.secondPasswordField());
        fieldLinc.add(this.nameField());
        fieldLinc.add(this.lastNameField());
        fieldLinc.add(this.emailField());
        fieldLinc.add(this.ircNickField());
        return fieldLinc;
    }
    public String okButton (){
        return "//*[@id=\"new_user\"]/input";
    }
    public String errorFild(){
        return "//*[@id=\"errorExplanation\"]";
    }
    public String testDataForSearch(){
        return "Test data";
    }
}
