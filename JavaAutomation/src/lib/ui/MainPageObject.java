package lib.ui;

import lib.CoreTestCase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject extends CoreTestCase {
    protected AppiumDriver appiumDriver;
    public MainPageObject(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }
    public WebElement waitMethod(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, timeoutInSeconds);
        webDriverWait.withMessage(errorMessage+"\n");
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    // Overloading of waitMethod()
    public WebElement waitMethod(String locator, String errorMessage){
        By by = this.getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, 5);
        webDriverWait.withMessage(errorMessage+"\n");
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds){
        WebElement element = waitMethod(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }
    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds){
        WebElement element = waitMethod(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, timeoutInSeconds);
        webDriverWait.withMessage(errorMessage);
        return webDriverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeoutInSeconds){
        WebElement element = waitMethod(locator, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }
    public void scrollUpMethod(int scrollDuration){
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
    public void swipeElementToLeft(String locator, String errorMessage){
        WebElement element = waitMethod(
                locator,
                errorMessage,
                5
        );
        int leftX = element.getLocation().getX();
        int rightX = (leftX + element.getSize().getWidth());
        int topY = element.getLocation().getY();
        int bottomY = (topY + element.getSize().getHeight());
        int middleY = (topY + bottomY) / 2;

        TouchAction touchAction = new TouchAction(appiumDriver);
        touchAction.press(rightX, middleY);
        touchAction.waitAction(250);

        if(Platform.getInstance().isAndroid()){
            touchAction.moveTo(leftX, middleY);
        }else{
            int offsetX = (-1 * element.getSize().getWidth());
            touchAction.moveTo(offsetX,0);
        }
        touchAction.release();
        touchAction.perform();
    }

    // For Android
    public void scrollUpToElementMethod(String locator, String errorMessage, int maxNumberOfSwipes){
        By by = this.getLocatorByString(locator);
        int swipes = 0;
        // Scroll till element will be found
        while (appiumDriver.findElements(by).size() == 0){
            // Prevent unreachable situation
            if(swipes > maxNumberOfSwipes){
                waitMethod(
                        locator,
                        "Cannot find element\n"+errorMessage,
                        0
                );
                return;
            }
            scrollUpMethod(200);
            ++swipes;
        }
    }
    /* iOS */
    public boolean isElementLocatedOnTheScreen(String locator){
        int elementLocationByY = this.waitMethod(
                locator,
                "Cannot find element by locator.\nLocator: "+locator,
                1
        ).getLocation().getY();
        int screenSizeByY = appiumDriver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }
    public void scrollUpToElementMethodForIOS(String locator, String errorMessage, int maxNumberOfSwipes){
        int swipes = 0;
        while (!this.isElementLocatedOnTheScreen(locator)){
            if(swipes > maxNumberOfSwipes){
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
            }
            scrollUpMethod(200);
            ++swipes;
        }
    }
    public void clickElementToTheRightUpperCorner(String locator, String errorMessage){
        /* If xpath is huge or unreadable, we can move to an element which located higher that current element
        and surely contains it, by simply adding '/..' to 'locator' variable */
        WebElement element = this.waitMethod(locator+"/..", errorMessage, 5);
        // Getting right 'x' coordinate
        int rightX = element.getLocation().getX();
        // Getting upper 'y' coordinate
        int upperY = element.getLocation().getY();
        // Getting lower 'y' coordinate
        int lowerY = upperY + element.getSize().getHeight();
        // Getting middle coordinate
        int middleY = (upperY + lowerY) / 2;
        // Getting width of element
        int width = element.getSize().getWidth();

        // Set 'x' point to click
        int pointToClickX = (rightX + width) - 3;
        // Set 'y' point to click
        int pointToClickY = middleY;

        TouchAction touchAction = new TouchAction(appiumDriver);
        touchAction.tap(pointToClickX, pointToClickY).perform();
    }
    /* iOS */
    public List<WebElement> waitForElements(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, timeoutInSeconds);
        webDriverWait.withMessage(errorMessage+"\n");
        return webDriverWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }
    public boolean textContains(List<WebElement> listOfElements, String text){
        for(int i = 0; i < listOfElements.size(); i++){
            if(listOfElements.get(i).getText().contains(text)){
                return true;
            }
        }
        return false;
    }
    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List<WebElement> listOfElements = appiumDriver.findElements(by);
        return listOfElements.size();
    }
    public void assertElementNotPresent(String locator, String errorMessage){
        int amountOfElements = getAmountOfElements(locator);
        if(amountOfElements > 0){
            String errorNote = locator+" elements shouldn't present\n"+errorMessage;
            throw new AssertionError(errorNote);
        }
    }
    public boolean assertTitleElementPresent(String locator){
        By by = this.getLocatorByString(locator);
        WebElement element = appiumDriver.findElement(by);
        String title = element.getText();
        if(!title.equals("")){
            return true;
        }
        throw new AssertionError();
    }
    public String getElementTextByAttribute(String locator, String errorMessage){
        WebElement element = waitMethod(locator, errorMessage, 15);
        return element.getText();
    }
    // Method for getting locator for correctly using both platform
    private By getLocatorByString(String locatorWithType){
        String explodedLocator[] = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if(byType.equals("xpath")){
            return By.xpath(locator);
        }else if(byType.equals("id")){
            return By.id(locator);
        }else{
            throw new IllegalArgumentException("Cannot get type of locator.\nLocator: "+locatorWithType);
        }
    }
}
