import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SecondPartOfTests {
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
    public void scrollTest(){
        findElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'search_container' element by id",
                5
        );
        findElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find 'search_src_text' element by id",
                5
        );
        findElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'search_src_text' element by id",
                5
        );
        scrollUpMethod(1000);
        scrollUpMethod(1000);
        scrollUpMethod(1000);
    }
    private WebElement waitMethod(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, timeoutInSeconds);
        webDriverWait.withMessage(errorMessage);
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement findElementAndClick(By by, String errorMessage, long timeoutInSeconds){
        WebElement webElement = waitMethod(by, errorMessage, timeoutInSeconds);
        webElement.click();
        return webElement;
    }
    private WebElement findElementAndSendKeys(By by, String sendValue, String errorMessage, long timeoutInSeconds){
        WebElement webElement = waitMethod(by, errorMessage, timeoutInSeconds);
        webElement.sendKeys(sendValue);
        return webElement;
    }
    protected void scrollUpMethod(int scrollDuration){
        TouchAction touchAction = new TouchAction(appiumDriver);
        // Set coordinates for scroll
        // Getting window size of device
        Dimension deviceWindowSize = appiumDriver.manage().window().getSize();
        // Set center-point of width
        int x = deviceWindowSize.width / 2;
        // from 80%-point of device window
        int startForY = (int) (deviceWindowSize.height * 0.8);
        // to 20%-point of device window
        int endForY = (int) (deviceWindowSize.height * 0.2);

        touchAction.press(x, startForY).waitAction(scrollDuration).moveTo(x, endForY).release().perform();
    }
}