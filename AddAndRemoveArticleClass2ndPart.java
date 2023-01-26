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

public class AddAndRemoveArticleClass2ndPart extends NecessaryMethodsClass{
    private AppiumDriver appiumDriver;
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
    public void addTwoArticlesAndRemoveOneTest(){
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
                "Cannot find 'search_src_text' element by its id",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Java']"),
                "Cannot find 'page_list_item_container' element by its xpath",
                5
        );
        waitMethod(
                appiumDriver,
                By.id("org.wikipedia:id/view_page_title_text"),
                "Timeout has been run out",
                15
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'Add to reading list' element by its xpath",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' element by its xpath",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'GOT IT' element by its id",
                5
        );
        waitForElementAndClear(
                appiumDriver,
                By.id("org.wikipedia:id/text_input"),
                "Cannot clear 'text_input' input field",
                5
        );
        waitForElementAndSendKeys(
                appiumDriver,
                By.id("org.wikipedia:id/text_input"),
                "First article",
                "Cannot find 'text_input' element by its id",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.id("android:id/button1"),
                "Cannot find 'OK' element by its id",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'X' close button by its xpath",
                5
        );
        ///
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
                "Cannot find 'search_src_text' element by its id",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'page_list_item_container' element by its xpath",
                5
        );
        waitMethod(
                appiumDriver,
                By.id("org.wikipedia:id/view_page_title_text"),
                "Timeout has been run out",
                15
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'Add to reading list' element by its xpath",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' element by its xpath",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/create_button"),
                "Cannot find 'create_button' element by its xpath",
                5
        );
        waitForElementAndClear(
                appiumDriver,
                By.id("org.wikipedia:id/text_input"),
                "Cannot clear 'text_input' input field",
                5
        );
        waitForElementAndSendKeys(
                appiumDriver,
                By.id("org.wikipedia:id/text_input"),
                "Second article",
                "Cannot find 'text_input' element by its id",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.id("android:id/button1"),
                "Cannot find 'OK' element by its id",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'X' close button by its xpath",
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
                By.id("org.wikipedia:id/item_overflow_menu"),
                "Cannot find 'item_overflow_menu' element by its id",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@text='Delete list']"),
                "Cannot find 'item_overflow_menu' element by its id",
                5
        );
        waitMethod(
                appiumDriver,
                By.xpath("//*[@text='Second article']"),
                "Cannot find 'Second article' element by its xpath",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@text='Second article']"),
                "Cannot find 'Second article' element by its xpath",
                5
        );
        WebElement articleTitle = waitMethod(
                appiumDriver,
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'page_list_item_container' element by its xpath",
                5
        );
        String actualTitle = articleTitle.getText();
        Assert.assertEquals(
                "Titles are not equal",
                "Java (programming language)",
                actualTitle
        );
    }
}
