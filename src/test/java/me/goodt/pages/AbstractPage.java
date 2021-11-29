package me.goodt.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class AbstractPage {
    private final WebDriver driver;
    protected final int timeout = 4;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitForCondition(ExpectedCondition condition, int timeout) {
        Wait wait = new FluentWait(getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .ignoring(TimeoutException.class)
                .ignoring(NoSuchElementException.class);
        wait.until(condition);
    }
    public boolean hasClass(WebElement element, String className) {
        String classes = element.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals(className)) {
                return true;
            }
        }
        return false;
    }
    public boolean isElementPresent(List<WebElement> elements){
        return elements != null && elements.size() > 0;
    }
    public boolean isElementPresent(List<WebElement> elements, int size){
        return elements != null && elements.size() == size;
    }
}
