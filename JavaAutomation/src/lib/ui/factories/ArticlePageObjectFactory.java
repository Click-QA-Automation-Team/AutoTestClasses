package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.ios.iosArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver appiumDriver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(appiumDriver);
        }else{
            return new iosArticlePageObject(appiumDriver);
        }
    }
}
