import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class ScreenRotationTestClass extends NecessaryMethodsClass{
    AndroidDriver appiumDriver;
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
    public void RotationTest(){
        // Set default condition to screen orientation
        setDefaultScreenOrientation(appiumDriver);
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
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'page_list_item_container' by its id",
                10
        );
        String articleTitleBeforeFirstRotation = getElementTextByAttribute(
                appiumDriver,
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'view_page_title_text' element by its id"
        );
        // Rotate device window to landscape mode
        appiumDriver.rotate(ScreenOrientation.LANDSCAPE);
        String articleTitleAfterFirstRotation = getElementTextByAttribute(
                appiumDriver,
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'view_page_title_text' element by its id"
        );
        // Checking title of article to change
        Assert.assertEquals(
                "Article title has been changed after first rotation",
                articleTitleBeforeFirstRotation,
                articleTitleAfterFirstRotation
        );
        String articleTitleBeforeSecondRotation = getElementTextByAttribute(
                appiumDriver,
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'view_page_title_text' element by its id"
        );
        // Rotate device window to portrait mode
        appiumDriver.rotate(ScreenOrientation.PORTRAIT);
        String articleTitleAfterSecondRotation = getElementTextByAttribute(
                appiumDriver,
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'view_page_title_text' element by its id"
        );
        Assert.assertEquals(
                "Article title has been changed after second rotation",
                articleTitleBeforeSecondRotation,
                articleTitleAfterSecondRotation
        );
    }
    @Test
    public void findArticleAndChangeOrientationTest(){
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
        appiumDriver.rotate(ScreenOrientation.LANDSCAPE);
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'page_list_item_container' by its id",
                10
        );
    }
}
