import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
        touchAction
                .press(x, startForY)
                .waitAction(scrollDuration)
                .moveTo(x, endForY)
                .release().perform();
    }
    protected void swipeElementToLeft(AppiumDriver appiumDriver, By by, String errorMessage){
        WebElement element = waitMethod(
                appiumDriver,
                by,
                errorMessage,
                5
        );
        int leftX = element.getLocation().getX();
        int rightX = (leftX + element.getSize().getWidth());
        int topY = element.getLocation().getY();
        int bottomY = (topY + element.getSize().getHeight());
        int middleY = (topY + bottomY) / 2;

        TouchAction touchAction = new TouchAction(appiumDriver);
        touchAction
                .press(rightX, middleY)
                .waitAction(250)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }
    protected void scrollUpToElementMethod(AppiumDriver appiumDriver, By by, String errorMessage, int maxNumberOfSwipes){
        int swipes = 0;
        // Scroll till element will be found
        while (appiumDriver.findElements(by).size() == 0){
            // Prevent unreachable situation
            if(swipes > maxNumberOfSwipes){
                waitMethod(appiumDriver,
                        by,
                        "Cannot find element\n"+errorMessage,
                        0
                );
                return;
            }
            scrollUpMethod(appiumDriver, 200);
            ++swipes;
        }
    }
    protected List<WebElement> waitForElements(AppiumDriver appiumDriver, By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, timeoutInSeconds);
        webDriverWait.withMessage(errorMessage+"\n");
        return webDriverWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }
    protected boolean textContains(List<WebElement> listOfElements){
        for(int i = 0; i < listOfElements.size(); i++){
            if(listOfElements.get(i).getText().contains("Java") ||
                    listOfElements.get(i).getText().contains("java")){
                return true;
            }
        }
        return false;
    }
}
