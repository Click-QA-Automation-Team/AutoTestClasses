import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class ScrollTestClass extends NecessaryMethodsClass{
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
    public void scrollWindowUpTest(){
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'search_container' element by its id",
                5
        );
        waitForElementAndSendKeys(
                appiumDriver,
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find 'search_src_text' element by its id",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'page_list_item_container' element by its id",
                15
        );
        scrollUpToElementMethod(
                appiumDriver,
                By.id("org.wikipedia:id/page_external_link"),
                "Searching element is 'page_external_link'",
                3
        );
    }
}
