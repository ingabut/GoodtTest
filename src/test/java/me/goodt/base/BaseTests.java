package me.goodt.base;

import me.goodt.pages.HomePage;
import me.goodt.pages.LoginPage;
import me.goodt.pages.MainPage;
import me.goodt.utils.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    int timeout = 5;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void goHome() {
        loginToEmail();
    }
    public void loginToEmail() {
        driver.get(ConfProperties.getProperty("homepage"));
        homePage = new HomePage(driver);
        loginPage = homePage.clickEnter();
        mainPage = loginPage.login();

    }

    @AfterMethod
    public void logout() {
        driver.manage().deleteAllCookies();
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() {
      //  driver.manage().deleteAllCookies();
        driver.quit();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        return options;
    }

}
