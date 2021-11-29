package me.goodt.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends AbstractPage{

    @FindBy(xpath = "//a[child::span[text()=\"Войти\"]]")
    private List<WebElement> loginButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public LoginPage clickEnter() {
        if (isElementPresent(loginButton))
            loginButton.get(0).click();
        return new LoginPage(getDriver());

    }
}
