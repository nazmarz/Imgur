package test.pageobjects;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.objects.HomePage;

public class HomePageTest extends HomePage {

    HomePage objOfHomePage;

    @BeforeMethod
    public void initializationOfElements() {
        objOfHomePage = PageFactory.initElements (driver, HomePage.class);
    }

    @Test
    public void createNewPostTest(){
        objOfHomePage.createNewPost();
        Assert.assertTrue(objOfHomePage.getVerifyNewPostPage().isDisplayed());
    }

    @Test
    public void searchTest(){
        objOfHomePage.search();
        Assert.assertTrue(objOfHomePage.getVerifySearchQuery().isDisplayed());
    }

    @Test
    public void sortByRandomTest(){
        objOfHomePage.sortByRandom();
        String s = objOfHomePage.getDropDownText();
        Assert.assertEquals(s,"RANDOM");
    }

}


