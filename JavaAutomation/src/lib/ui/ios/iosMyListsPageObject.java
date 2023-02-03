package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iosMyListsPageObject extends MyListPageObject {
    static {
        FOLDER_BY_NAME_TPL = "...";
        ARTICLE_BY_TITLE_TPL = "...";
    }
    public iosMyListsPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
