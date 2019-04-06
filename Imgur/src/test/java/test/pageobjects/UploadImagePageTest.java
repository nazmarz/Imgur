package test.pageobjects;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.objects.HomePage;
import page.objects.UploadImagePage;

public class UploadImagePageTest extends UploadImagePage {
    HomePage objOfHomePage;
    UploadImagePage objOfUploadImagePage;

    @BeforeMethod
    public void initializationOfElements() {
        objOfHomePage = PageFactory.initElements (driver, HomePage.class);
        objOfUploadImagePage = PageFactory.initElements(driver,UploadImagePage.class);
    }

    @Test
    public void createNewPostTest(){
        objOfHomePage.createNewPost();
        objOfUploadImagePage.uploadPhoto();
        Assert.assertTrue(objOfUploadImagePage.getVerifyUpload().isDisplayed());
    }
}
