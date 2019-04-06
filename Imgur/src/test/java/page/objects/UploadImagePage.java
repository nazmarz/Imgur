package page.objects;

import application.page.base.ApplicationPageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UploadImagePage extends ApplicationPageBase {

    @FindBy(id = "global-files-button")
    private WebElement browseButton;

    @FindBy(linkText = "Share to community")
    private WebElement shareToCommunity;


    public void uploadPhoto(){
        sendKeysII(browseButton, "Browse Button","C:\\Users\\Marzana\\Desktop\\Capture.JPG");
    }

    public WebElement getVerifyUpload(){
        return shareToCommunity;
    }
}
