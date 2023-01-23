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

public class FirstPartOfTests extends NecessaryMethodsClass{
    private AppiumDriver appiumDriver;

    @Before
    public void setUp()throws Exception{
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
    public void findSomeArticleTest(){
        // Search elements and take some actions using special methods
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' element",
                5
        );

        waitForElementAndSendKeys(
                appiumDriver,
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find 'Search…' element",
                5
        );

        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Topic of 'Object-oriented programming language' was not found",
                15
        );

        // Search elements and take some actions without using special methods
/*        WebElement mainSearchField = appiumDriver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        mainSearchField.click();

        WebElement enterToMainSearchField = waitMethod(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Waiting time has run out"
        );
        enterToMainSearchField.sendKeys("Java");

        WebElement someElementInDropDownMenu = waitMethod(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Topic of 'Object-oriented programming language' was not found",
                15
        );
        someElementInDropDownMenu.click();*/
    }
    @Test
    public void cancelActionTest(){
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/search_container"),
                "Cannot find searching container",
                5
        );
        waitForElementAndSendKeys(
                appiumDriver,
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find 'Search…' element",
                5
        );
        waitForElementAndClear(
                appiumDriver,
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot clear searching filed",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find the close button",
                5
        );
        waitForElementNotPresent(
                appiumDriver,
                By.id("org.wikipedia:id/search_close_btn"),
                "Element still exists",
                5
        );
    }
    @Test
    public void articleTitleCompareTest(){
        waitForElementAndClick(
                appiumDriver,
                By.id("org.wikipedia:id/search_container"),
                "Cannot find searching container",
                5
        );
        waitForElementAndSendKeys(
                appiumDriver,
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find 'Search…' element",
                5
        );
        waitForElementAndClick(
                appiumDriver,
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Cannot find searching container",
                5
        );
        WebElement element = waitMethod(
                appiumDriver,
                By.id("org.wikipedia:id/view_page_title_text"),
                "Timeout has been reached",
                15
        );
        String article_title = element.getText();
        Assert.assertEquals(
                "Titles are not equal",
                "Java (programming language)",
                article_title
        );
    }
}