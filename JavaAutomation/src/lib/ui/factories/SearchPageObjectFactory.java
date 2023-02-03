package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.iosSearchPageObject;

public class SearchPageObjectFactory{
    public static SearchPageObject get(AppiumDriver appiumDriver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(appiumDriver);
        }else{
            return new iosSearchPageObject(appiumDriver);
        }
    }
}
