import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.List;

public class AddAndRemoveArticleClass extends NecessaryMethodsClass{
    AppiumDriver appiumDriver;
    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "GoogleNexus4");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Ruslan\\Desktop\\Automation\\Tools\\org.wikipedia.apk");

        appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown(){
        appiumDriver.quit();
    }
    @Test
    public void addArticleToList(){
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'search_container' element by its id",
                5
        );
        waitForElementAndSendKeys(
                appiumDriver,
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find 'search_src_text' by its id",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'page_list_item_container' by its id",
                10
        );
        waitMethod(
                appiumDriver,
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'view_page_title_text' element by its id",
                15
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More options' button by its xpath",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' by its xpath",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'onboarding_button' element by its id",
                5
        );
        waitForElementAndClear(
                appiumDriver,
                By.id("org.wikipedia:id/text_input"),
                "Cannot clear input text field",
                5
        );
        waitForElementAndSendKeys(
                appiumDriver,
                By.id("org.wikipedia:id/text_input"),
                "Test article",
                "Cannot type article title into input field",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@text='OK']"),
                "Cannot find 'OK' button",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'X' button to close article",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' element by its xpath",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@text='Test article']"),
                "Cannot find 'Test article' article",
                5
        );
        swipeElementToLeft(
                appiumDriver,
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                appiumDriver,
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }
    @Test
    public void testAmountOfSearchResults(){
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'search_container' element by its id",
                5
        );
        waitForElementAndSendKeys(
                appiumDriver,
                By.id("org.wikipedia:id/search_src_text"),
                "List of French Formula One engine manufacturer",
                "Cannot find 'search_src_text' element by its id",
                5
        );
        waitMethod(
                appiumDriver,
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']" +
                        "/*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "Cannot find any element by searching conditions",
                15
        );
        int amountOfElementsBySearchCondition = getAmountOfArticles(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']" +
                        "/*[@resource-id='org.wikipedia:id/page_list_item_container']")
                );
        Assert.assertTrue(
                "Cannot find any element by searching condition",
                amountOfElementsBySearchCondition > 0
        );
    }
    private int getAmountOfArticles(By by){
        List<WebElement> listOfElements = appiumDriver.findElements(by);
        return listOfElements.size();
    }
}
