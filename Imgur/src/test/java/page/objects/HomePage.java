package page.objects;

import application.page.base.ApplicationPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ApplicationPageBase {


    @FindBy(linkText = "New post")
    private WebElement newPost;

    @FindBy(className = "browse-btn")
    private WebElement verifyNewPostPage;

    @FindBy(className = "Searchbar-textInput")
    private WebElement searchBox;

    @FindBy(xpath = "//span[@class='search-term-text sorting-text-align']")
    private WebElement verifySearchQuery;

    @FindBy(xpath = "//span[text()='Newest']")
    private WebElement dropdownNewest;

    @FindBy(xpath = "//div[@class='Dropdown-option' and text()='Random']")
    private WebElement dropdownRandom;

    @FindBy(xpath = "//div[@class='Dropdown sort Dropdown--upper']//div[@class='Dropdown-title']")
    private WebElement verifyDropDownRandom;

    @FindBy(xpath = "//span[@class='NewCover-change-sort-wrapper']//span[@class='Dropdown-icon icon-dropdown-arrow']")
    private WebElement dropdown;

    public void createNewPost() {
        click(newPost, "New post");
    }

    public WebElement getVerifyNewPostPage() {
        return verifyNewPostPage;
    }

    public void search() {
        sendKeys(searchBox, "Search box", "cake");
    }

    public WebElement getVerifySearchQuery() {
        return verifySearchQuery;
    }

    public void sortByRandom() {
        click(dropdown,"Drop down");
        click(dropdownRandom, "Random");
    }

    public String getDropDownText(){
        String s = getText(verifyDropDownRandom,"Random");
        return s;


    }

}




















