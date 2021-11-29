package me.goodt.pages;

import me.goodt.utils.LANG;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainPage extends AbstractPage {
    //a[contains(@class,'legouser__current-account')]
    @FindBy(xpath="//a[contains(@class,'legouser__current-account')]")
    private WebElement accountMenu;

    @FindBy(xpath="//a[contains(@class,'legouser__menu-item_action_exit')]")
    private WebElement exitMenu;

    @FindBy(css = ".SettingsButton__icon--1_TD8")
    private WebElement settingsButton;

    @FindBy(css = ".Popup2_visible")
    private WebElement settingsPopup;

    @FindBy(css = ".b-heading")
    private WebElement heading;

    @FindBy(xpath = "//a[contains(@href, 'setup/other')]")
    private WebElement otherSettings;

    @FindBy(xpath = "//div[@data-id ='other']")
    private WebElement otherSettingsWindow;

    @FindBy(css = ".mail-Settings-Lang_arrow")
    private WebElement arrowLangMenu;

    @FindBy(css = ".b-mail-dropdown__box__content")
    private WebElement dropdownLangBox;

    @FindBy(xpath = "//a[contains(@data-params,'lang=en')]")
    private WebElement enLang;

    @FindBy(xpath = "//a[contains(@data-params,'lang=ru')]")
    private WebElement ruLang;

    @FindBy(id = "nb-1")
    private WebElement htmlPage;

    @FindBy(css = ".mail-NestedList-Item_current")
    private WebElement currentItem;

    @FindBy(css = ".ns-view-toolbar-button-delete")
    private WebElement deleteEmailButton;

    @FindBy(xpath = "//label[@data-nb='checkbox']")
    private List <WebElement> emailLabels;

    @FindBy(xpath = "//input[@class='_nb-checkbox-input']")
    private List <WebElement> emailCheckboxes;

    @FindBy(xpath = "//a[contains(@class,'mail-MessageSnippet')]/../..")
    private List <WebElement> emailData;

    @FindBy(css = ".mail-ComposeButton")
    private WebElement composeButton;

    @FindBy(css = ".ComposePopup-Content")
    private List <WebElement> composePopup;

    @FindBy(css = ".composeYabbles")
    private List <WebElement> addresseeElements;

    @FindBy(name = "subject")
    private WebElement subjectElement;

    @FindBy(css = ".composeHeader-Title")
    private List <WebElement> composeHeaderElements;

    @FindBy(xpath="//span[text()='Отправить без темы']/../..")
    private WebElement sendWithoutSubjectButton;

    @FindBy(xpath = "//div[@class='ComposeSendButton-Text']/../..")
    //button[following-sibling::span//div[@class='ComposeSendButton-Text']]
    private WebElement emailSendButton;

    @FindBy(css = ".ComposeYabble_invalid")
    private List<WebElement> invalidEmailElements;

    @FindBy(css = ".ComposeYabble-Text")
    private List<WebElement> emailElements;

    @FindBy(xpath = "//div[@class='ComposeConfirmPopup-Title']/span")
    private WebElement popupTitle;

    @FindBy(css = ".ComposeConfirmPopup-Close")
    private List <WebElement> closePopupButtons;

    @FindBy(xpath = "//div[contains(@class,'ControlButton_button_close')]/button")
    private List <WebElement> closeComposeEmailButtons;

    @FindBy(xpath="//div[@class='ComposeConfirmPopup-Description']/div/div/span")
    private WebElement popupDescription;

    @FindBy(css = " .ComposeDoneScreen-Title")
    private List<WebElement> composeDoneElements;

    @FindBy(css = ".ComposeConfirmPopup-Title")
    private List<WebElement> confirmPopupElements;

    //$$("input._nb-checkbox-input")[0].checked

    private List <String> inboxNames = Arrays.asList("Входящие","Inbox");
    private String redColor = "rgba(255, 0, 0, 0.2)";
    private  int emailCount = 0;


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getLanguages(){
        List<WebElement> languages = getDriver().findElements(By.cssSelector(".b-mail-dropdown__item__content"));
        return languages.stream().map(e->e.getText().trim()).collect(Collectors.toList());
    }
    public List<WebElement> getLangElements(){
        return getDriver().findElements(By.cssSelector(".b-mail-dropdown__item__content"));
    }
    public void clickOnSettings() {
        settingsButton.click();
    }
    public boolean isSettingsPopupVisible() {
        return settingsPopup.isDisplayed();
    }
    public void clickOnOtherSettings() {
        otherSettings.click();
    }
    public boolean isOtherSettingsWindowVisible() {
        return otherSettingsWindow.isDisplayed();
    }

    public void clickOnLangMenu() {
        arrowLangMenu.click();
    }

    public boolean isDropdownLangBoxDisplayed() {
        return dropdownLangBox.isDisplayed();
    }
    public void clickOnLang(LANG lang) {
        switch (lang) {
            case Russian:
                ruLang.click();
                break;
            case English:
                enLang.click();
                break;
        }
        waitForCondition(ExpectedConditions.invisibilityOf(dropdownLangBox), timeout);

    }
    public String getTitle() {
        return getDriver().getTitle();
    }

    public boolean ifCurrentItemIsInbox(LANG lang) {
        if (lang == LANG.Russian && currentItem.getAttribute("title").equals("Входящие"))
            return true;
        else if (lang == LANG.English && currentItem.getAttribute("title").equals("Inbox"))
            return true;
        return false;
    }
    public boolean ifCurrentItemIsInbox() {
        if (inboxNames.contains(currentItem.getAttribute("title"))) {
            return true;
        }
        return false;
    }
    public boolean isDeleteEmailButtonDisabled() {
        return hasClass(deleteEmailButton,"is-disabled");
    }
    public void deleteEmail() {
        deleteEmailButton.click();

    }
    public boolean toggleOnRandomEmail() {
        if (emailCheckboxes.size() == 0) return false;
        Random random = new Random();
        int randomIndex = random.nextInt(emailLabels.size());
        emailCount = emailData.size();
        WebElement label = emailLabels.get(randomIndex);
        label.click();
        if (emailCheckboxes.get(randomIndex).isSelected()) return true;
        return false;
    }
    public boolean isEmailDeleted() {
        waitForCondition(ExpectedConditions.numberOfElementsToBe(By.xpath("//a[contains(@class,'mail-MessageSnippet')]/../.."),emailCount -1), timeout);
        if (emailData.size() == emailCount - 1) return true;
        return false;
    }

    public void clickComposeButton() {
        composeButton.click();
    }
    public boolean isComposeEmailPresent() {
        return isElementPresent(composePopup, 1);
    }
    public void enterAddressee(String addressee) {
        addresseeElements.get(0).sendKeys(addressee);
        new Actions(getDriver()).moveToElement(composeButton).click().perform();
    }
    public boolean isAddresseeEntered(String addressee) {
        return isElementPresent(getDriver().findElements(By.xpath("//div[contains(text(),'"+addressee+"')]")));
    }
    public String getInvalidEmailEntered() {
        if (isElementPresent(invalidEmailElements)) {
            for (WebElement e:invalidEmailElements) {
                List <WebElement> invalidEmails = e.findElements(By.xpath("./div[contains(@class, 'ComposeYabble-Text')]"));
                if (invalidEmails.size()>0) {
                    return invalidEmails.get(0).getText();
                }
            }

        }
        return "";
    }
    public String getValidEmailEntered() {
        if (isElementPresent(emailElements)) {
            return emailElements.get(0).getText();
        }
        return "";
    }
    public void enterSubject(String subject) {
        subjectElement.sendKeys(subject);
        subjectElement.sendKeys(Keys.ENTER);
    }
    public boolean isSubjectEntered(String subject) {
        System.out.println(((JavascriptExecutor) getDriver()).executeScript("return $('input[name=subject]').val();"));
        if (((JavascriptExecutor) getDriver()).executeScript("return $('input[name=subject]').val();").equals(subject))
            return true;
        else return false;
    }
    public void sendEmail() {
        emailSendButton.click();
    }
    public boolean isEmailSent() {
        return isElementPresent(composeDoneElements);
    }
    public boolean isPopupPresentEmailNotSent() {
        return isElementPresent(confirmPopupElements);
    }
    public String getPopupTitle() {
        return popupTitle.getText();
    }
    public String getPopupDescription() {
        return popupDescription.getText();
    }
    public void clickSendWithoutSubjectButton() {
        sendWithoutSubjectButton.click();
    }
    public void closePopup() {
        if (isElementPresent(closePopupButtons)) {
            for (WebElement e:closePopupButtons) {
                e.click();
            }
        }
    }

    public boolean isAddresseeFieldRedColored() {
        if (!isElementPresent(invalidEmailElements)) return false;
        for (WebElement e:invalidEmailElements)
            if (e.getCssValue("background-color").toString().equals(redColor))
                return true;
        return false;
    }

}
