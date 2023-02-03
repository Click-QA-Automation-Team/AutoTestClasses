package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iosArticlePageObject extends ArticlePageObject {
    static{
        TITLE = "...";
        FOOTER_ELEMENT = "...";
        OPTIONS_BTN = "...";
        OPTIONS_ADD_TO_READING_LIST_BTN = "...";
        OPTIONS_OVERLAY_BTN = "...";
        OPTIONS_OVERLAY_2ND_BTN = "...";
        ADD_ARTICLE_TITLE_FIELD = "...";
        ADD_ARTICLE_OK_BTN = "...";
        CLOSE_ARTICLE_BTN = "...";
        OVERFLOW_BTN = "...";
        DELETE_ARTICLE_BTN = "...";
        SEARCH_ELEMENT_BY_TEXT_TPL = "...";
    }
    public iosArticlePageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}