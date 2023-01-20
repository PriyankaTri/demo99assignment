package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(name = "uid")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(name = "btnLogin")
    WebElement loginBtn;

    public LoginPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);
    }

    public void enterUserName(String us){
        username.sendKeys(us);
    }

    public void enterPassword(String pass){
        password.sendKeys(pass);
    }

    public HomePage clickLoginBtn(){
       loginBtn.click();
       return new HomePage(driver);
    }

    public HomePage loginWithValidCredential(String uname, String pass){
        username.sendKeys(uname);
        password.sendKeys(pass);
        loginBtn.click();
        return new HomePage(driver);
    }

}
