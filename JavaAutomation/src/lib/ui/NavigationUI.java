package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {
    public static String
        MY_LIST_BTN;

    public NavigationUI(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    public void myList(){
        this.waitForElementAndClick(
                MY_LIST_BTN,
                "Cannot find 'My lists' element by its xpath",
                5
        );
    }
}
