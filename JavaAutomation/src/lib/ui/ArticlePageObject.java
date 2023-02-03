package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BTN,
        OPTIONS_ADD_TO_READING_LIST_BTN,
        OPTIONS_OVERLAY_BTN,
        OPTIONS_OVERLAY_2ND_BTN,
        ADD_ARTICLE_TITLE_FIELD,
        ADD_ARTICLE_OK_BTN,
        CLOSE_ARTICLE_BTN,
        OVERFLOW_BTN,
        DELETE_ARTICLE_BTN,
        SEARCH_ELEMENT_BY_TEXT_TPL;
    public ArticlePageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    public WebElement waitForTitleElement(){
        return this.waitMethod(TITLE, "Cannot find article title", 15);
    }
    public String getArticleTitle(){
        WebElement webElement = waitForTitleElement();
        return webElement.getText();
    }
    /* TEMPLATE METHODS */
    private static String getResultSearchElementByText(String substring){
        return SEARCH_ELEMENT_BY_TEXT_TPL.replace("{TEXT}", substring);
    }
    /* TEMPLATE METHODS */
    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()){
            this.scrollUpToElementMethod(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    10
            );
        }else {
            this.scrollUpToElementMethodForIOS(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    10
            );
        }
    }
    public void addArticleToMyList(String nameOfArticle){
        this.waitForElementAndClick(
                OPTIONS_BTN,
                "Cannot find 'More options' button by its xpath",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_READING_LIST_BTN,
                "Cannot find 'Add to reading list' by its xpath",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_OVERLAY_BTN,
                "Cannot find element by its id ",
                5
        );
        this.waitForElementAndClear(
                ADD_ARTICLE_TITLE_FIELD,
                "Cannot clear input text field",
                5
        );
        this.waitForElementAndSendKeys(
                ADD_ARTICLE_TITLE_FIELD,
                nameOfArticle,
                "Cannot type article title into input field",
                5
        );
        this.waitForElementAndClick(
                ADD_ARTICLE_OK_BTN,
                "Cannot find 'OK' button",
                5
        );
    }
    public void addSecondArticleToMyList(String nameOfArticle){
        this.waitForElementAndClick(
                OPTIONS_BTN,
                "Cannot find 'More options' button by its xpath",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_READING_LIST_BTN,
                "Cannot find 'Add to reading list' by its xpath",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_OVERLAY_2ND_BTN,
                "Cannot find element by its id ",
                5
        );
        this.waitForElementAndClear(
                ADD_ARTICLE_TITLE_FIELD,
                "Cannot clear input text field",
                5
        );
        this.waitForElementAndSendKeys(
                ADD_ARTICLE_TITLE_FIELD,
                nameOfArticle,
                "Cannot type article title into input field",
                5
        );
        this.waitForElementAndClick(
                ADD_ARTICLE_OK_BTN,
                "Cannot find 'OK' button",
                5
        );
    }
    /* Adding article for iOS */
    public void addArticleToMySaved(){
        this.waitForElementAndClick(
                "default-text",
                "Cannot find saving button to add articles to saved list",
                5
        );
    }
    /* Adding article for iOS */
    public void closeArticle(){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BTN,
                "Cannot find 'X' button to close article",
                5
        );
    }
    public void deleteArticleByOverflowAndCheckForAnother(String substring){
        // Delete article
        this.waitForElementAndClick(
                OVERFLOW_BTN,
                "Cannot find 'item_overflow_menu' element by its id",
                5
        );
        this.waitForElementAndClick(
                DELETE_ARTICLE_BTN,
                "Cannot find 'item_overflow_menu' element by its id",
                5
        );
        // Check for existing of another
        String searchByText = getResultSearchElementByText(substring);
        this.waitMethod(
                searchByText,
                "Cannot find element by substring "+substring,
                5
        );
        this.waitForElementAndClick(
               searchByText,
               "Cannot find element by text "+searchByText,
               5
        );
    }
    public void openArticleByTextSubstring(String substring){
        String searchByText = getResultSearchElementByText(substring);
        this.waitMethod(
                searchByText,
                "Cannot find element by substring "+substring,
                5
        );
        this.waitForElementAndClick(
                searchByText,
                "Cannot find element by text "+searchByText,
                5
        );
    }
    public void scrollUp(int duration){
        this.scrollUpMethod(duration);
    }
}
