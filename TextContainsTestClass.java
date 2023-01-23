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

public class TextContainsTestClass extends NecessaryMethodsClass{
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
    public void checkTextIntoSearchField(){
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
        List<WebElement> listOfElements = waitForElements(
                appiumDriver,
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find any element",
                20
        );
        Assert.assertTrue(textContains(listOfElements));
        Assert.assertTrue(listOfElements.size() > 1);
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'search_src_text' element by its id",
                5
        );
        WebElement searchFieldElement = waitMethod(
                appiumDriver,
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find 'search_src_text' element by its id",
                5
        );
        String searchFieldText = searchFieldElement.getText();
        Assert.assertEquals(
                "Texts are not equal",
                "Search…",
                searchFieldText
        );
    }
}
