package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListPageObject;
import lib.ui.android.AndroidMyListsPageObject;
import lib.ui.ios.iosMyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListPageObject get(AppiumDriver appiumDriver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(appiumDriver);
        }else{
            return new iosMyListsPageObject(appiumDriver);
        }
    }
}
