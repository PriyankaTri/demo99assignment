package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//*[text()='Gtpl Bank']")
    WebElement homePageGtplBankText;

    @FindBy(xpath = "//*[text()='Log out']")
    WebElement logoutBtn;

    public HomePage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);
    }

    public String getGtplText(){
       return homePageGtplBankText.getText();
    }

    public void clickLogout(){
        logoutBtn.click();
    }
}
