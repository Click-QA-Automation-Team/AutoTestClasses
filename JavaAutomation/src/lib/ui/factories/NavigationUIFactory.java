package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.iosNavigationUI;

public class NavigationUIFactory {
    public static NavigationUI get(AppiumDriver appiumDriver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidNavigationUI(appiumDriver);
        }else{
            return new iosNavigationUI(appiumDriver);
        }
    }
}
