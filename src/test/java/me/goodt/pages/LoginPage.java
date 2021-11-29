package me.goodt.pages;

import me.goodt.utils.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends AbstractPage{

    private String email = ConfProperties.getProperty("login");
    private String password = ConfProperties.getProperty("password");

    @FindBy(id = "passp-field-login")
    private List<WebElement> loginField;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    @FindBy(id = "passp:sign-in")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public MainPage login() {
        if (isElementPresent(loginField)) {
            loginField.get(0).sendKeys(email);
            loginButton.click();
        }
        passwordField.sendKeys(password);
        loginButton.click();

        return new MainPage(getDriver());
    }
}
