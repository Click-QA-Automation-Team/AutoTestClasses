import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NecessaryMethodsClass {
    protected WebElement waitMethod(AppiumDriver appiumDriver, By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, timeoutInSeconds);
        webDriverWait.withMessage(errorMessage+"\n");
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    // Overloading of waitMethod()
    protected WebElement waitMethod(AppiumDriver appiumDriver, By by, String errorMessage){
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, 5);
        webDriverWait.withMessage(errorMessage+"\n");
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    protected WebElement waitForElementAndClick(AppiumDriver appiumDriver, By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitMethod(appiumDriver, by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }
    protected WebElement waitForElementAndSendKeys(AppiumDriver appiumDriver, By by, String value, String errorMessage, long timeoutInSeconds){
        WebElement element = waitMethod(appiumDriver, by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    protected boolean waitForElementNotPresent(AppiumDriver appiumDriver,By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, timeoutInSeconds);
        webDriverWait.withMessage(errorMessage);
        return webDriverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    protected WebElement waitForElementAndClear(AppiumDriver appiumDriver, By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitMethod(appiumDriver, by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }
    protected void scrollUpMethod(AppiumDriver appiumDriver, int scrollDuration){
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
