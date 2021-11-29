package me.goodt.tests;

import me.goodt.base.BaseTests;
import me.goodt.utils.LANG;
import org.testng.annotations.Test;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class YandexMailTests extends BaseTests {
    String addressee = "test.send.goodt@yandex.ru";
    String subject = "Test subject";

    @Test(description = "Switch language to English")
    public void testYA_1_1(){
        assertTrue(mainPage.ifCurrentItemIsInbox(LANG.Russian));
        mainPage.clickOnSettings();
        assertTrue(mainPage.isSettingsPopupVisible(),
                "Settings popup is not visible");
        mainPage.clickOnOtherSettings();
        assertTrue(mainPage.isOtherSettingsWindowVisible(),"Other settings window is not visible");
        mainPage.clickOnLangMenu();
        assertTrue(mainPage.isDropdownLangBoxDisplayed(),"Box with languages is not open");
        mainPage.clickOnLang(LANG.English);
        assertTrue(!mainPage.isDropdownLangBoxDisplayed(),"Box with languages is not closed");
        assertEquals(mainPage.getTitle(),"Yandex.Mail");
    }

    @Test (description = "Switch language to Russian")
    public void testYA_1_2(){
        assertTrue(mainPage.ifCurrentItemIsInbox(LANG.English));
        mainPage.clickOnSettings();
        assertTrue(mainPage.isSettingsPopupVisible(),
                "Settings popup is not visible");
        mainPage.clickOnOtherSettings();
        assertTrue(mainPage.isOtherSettingsWindowVisible(),"Other settings window is not visible");
        mainPage.clickOnLangMenu();
        assertTrue(mainPage.isDropdownLangBoxDisplayed(),"Box with languages is not open");
        mainPage.clickOnLang(LANG.Russian);
        assertTrue(!mainPage.isDropdownLangBoxDisplayed(),"Box with languages was not closed");
        assertEquals(mainPage.getTitle(),"Яндекс.Почта");
    }
    @Test (description = "Email removal without email choice")
    public void testYA_2(){
        assertTrue(mainPage.ifCurrentItemIsInbox());
        assertTrue(mainPage.isDeleteEmailButtonDisabled());
        mainPage.deleteEmail();
       // assertTrue(!mainPage.isEmailDeleted());
    }
    @Test (description = "Check remove random emails")
    public void testYA_3(){
        assertTrue(mainPage.ifCurrentItemIsInbox());
        assertTrue(mainPage.toggleOnRandomEmail());
        assertTrue(!mainPage.isDeleteEmailButtonDisabled());
        mainPage.deleteEmail();
        assertTrue(mainPage.isEmailDeleted());
    }
    @Test (description = "Check toggle random emails")
    public void testYA_4(){
        assertTrue(mainPage.ifCurrentItemIsInbox());
        assertTrue(mainPage.toggleOnRandomEmail());
    }
    @Test (description = "Check mail sending")
    public void testYA_5(){
        assertTrue(mainPage.ifCurrentItemIsInbox());
        mainPage.clickComposeButton();
        assertTrue(mainPage.isComposeEmailPresent());
        mainPage.enterAddressee(addressee);
        assertEquals(mainPage.getValidEmailEntered(),addressee);
        mainPage.enterSubject(subject);
        assertTrue(mainPage.isSubjectEntered(subject));
        mainPage.sendEmail();
        assertTrue(mainPage.isEmailSent());
    }

    @Test (description = "Check mail is not sent without addressee and subject")
    public void testYA_6_1(){
        assertTrue(mainPage.ifCurrentItemIsInbox());
        mainPage.clickComposeButton();
        assertTrue(mainPage.isComposeEmailPresent());
        mainPage.sendEmail();
        assertTrue(mainPage.isPopupPresentEmailNotSent());
        assertTrue(mainPage.isComposeEmailPresent());
        assertEquals(mainPage.getPopupDescription(),"Пожалуйста, укажите адрес получателя");
        mainPage.closePopup();
    }
    @Test (description = "Check mail is not sent without addressee")
    public void testYA_6_2(){
        assertTrue(mainPage.ifCurrentItemIsInbox());
        mainPage.clickComposeButton();
        assertTrue(mainPage.isComposeEmailPresent());
        mainPage.enterSubject(subject);
        assertTrue(mainPage.isSubjectEntered(subject));
        mainPage.sendEmail();
        assertTrue(mainPage.isPopupPresentEmailNotSent());
        assertTrue(mainPage.isComposeEmailPresent());
        assertEquals(mainPage.getPopupDescription(),"Пожалуйста, укажите адрес получателя");
        mainPage.closePopup();
    }
    @Test(description = "Check mail is sent without subject")
    public void testYA_6_3(){
        assertTrue(mainPage.ifCurrentItemIsInbox());
        mainPage.clickComposeButton();
        assertTrue(mainPage.isComposeEmailPresent());
        mainPage.enterAddressee(addressee);
        assertEquals(mainPage.getValidEmailEntered(),addressee);
        mainPage.sendEmail();
        assertEquals(mainPage.getPopupTitle(),"Укажите тему письма");
        mainPage.clickSendWithoutSubjectButton();
        mainPage.closePopup();
        assertTrue(mainPage.isEmailSent());
    }

}
