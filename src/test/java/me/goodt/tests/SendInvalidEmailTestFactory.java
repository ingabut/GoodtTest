package me.goodt.tests;

import me.goodt.base.BaseTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SendInvalidEmailTestFactory extends BaseTests {
    private String email;
    String subject = "Test subject for incorrect emails";

    @DataProvider
    public static Object[][] invalidEmails(){
        return new Object[][] {
                {"test.send.goodt.ya.ru"},     //email without @
                {"test.send.goodt@@ya.ru"},   //email with doubled @
                {"test.send.goodt@"}          //email without domain
        };
    }
    @Factory(dataProvider = "invalidEmails")
    public SendInvalidEmailTestFactory(String email) {
        this.email = email;
    }
    @Test(description = "Check email sending with incorrect addressee")
    public void test_6_4(){
        assertTrue(mainPage.ifCurrentItemIsInbox());
        mainPage.clickComposeButton();
        assertTrue(mainPage.isComposeEmailPresent());
        mainPage.enterAddressee(email);
        assertEquals(mainPage.getInvalidEmailEntered(),email);
        assertTrue(mainPage.isAddresseeFieldRedColored());
        mainPage.enterSubject(subject);
        assertTrue(mainPage.isSubjectEntered(subject));
        mainPage.sendEmail();
        assertTrue(mainPage.isPopupPresentEmailNotSent());
        assertEquals(mainPage.getPopupTitle(),"Проверьте получателя");
        mainPage.closePopup();
      //  mainPage.closeComposeEmailButton();
    }


}
