package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iosSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "...";
        SEARCH_INPUT = "...";
        SEARCH_CANCEL_BUTTON = "...";
        NO_RESULTS_FOUND = "...";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "...";
        SEARCH_RESULT_ELEMENT = "...";
        SEARCH_EMPTY_RESULT_ELEMENT = "...";
        SEARCH_RESULT_ITEMS_LIST = "...";
        SEARCH_SRC_TEXT = "...";
    }
    public iosSearchPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
