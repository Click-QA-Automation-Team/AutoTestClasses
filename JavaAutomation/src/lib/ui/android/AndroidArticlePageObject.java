package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static{
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "id:org.wikipedia:id/page_external_link";
        OPTIONS_BTN = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_READING_LIST_BTN = "xpath://*[@text='Add to reading list']";
        OPTIONS_OVERLAY_BTN = "id:org.wikipedia:id/onboarding_button";
        OPTIONS_OVERLAY_2ND_BTN = "id:org.wikipedia:id/create_button";
        ADD_ARTICLE_TITLE_FIELD = "id:org.wikipedia:id/text_input";
        ADD_ARTICLE_OK_BTN = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BTN = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        OVERFLOW_BTN = "id:org.wikipedia:id/item_overflow_menu";
        DELETE_ARTICLE_BTN = "xpath://*[@text='Delete list']";
        SEARCH_ELEMENT_BY_TEXT_TPL = "xpath://*[@text='{TEXT}']";
    }
    public AndroidArticlePageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
