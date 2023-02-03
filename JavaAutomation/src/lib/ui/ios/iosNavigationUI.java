package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iosNavigationUI extends NavigationUI {
    static {
        MY_LIST_BTN = "...";
    }
    public iosNavigationUI(AppiumDriver appiumDriver){
        super(appiumDriver);
    }
}
