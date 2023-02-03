package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject{
     protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        NO_RESULTS_FOUND,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_RESULT_ITEMS_LIST,
        SEARCH_SRC_TEXT;

    public SearchPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS*/
    public void initSearchInput(){
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click on search init element",
                5
        );
        this.waitMethod(
                SEARCH_INPUT,
                "Cannot find search init element after clicking on it"
        );
    }
    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                searchLine,
                "Cannot find and enter value into search input field",
                5
        );
    }
    public void waitForSearchResult(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitMethod(
                searchResultXpath,
                "Cannot find search result with substring"
        );
    }
    public void waitForEmptySearchResult(){
        this.waitForElementNotPresent(
                NO_RESULTS_FOUND,
                "Cannot find anything by search conditions",
                5
        );
    }
    public void clickOnArticleWithSubstring(String substring){
        String searchSearchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                searchSearchResultXpath,
                "Cannot find and click on search result with substring",
                10
        );
    }
    public void waitForCancelButtonAppears(){
        this.waitMethod(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button element"
        );
    }
    public void waitForCancelButtonDisappears(){
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button element is still present",
                5
        );
    }
    public void clickCancelSearch(){
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5
        );
    }
    public int getAmountOfFoundArticles(){
        this.waitMethod(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }
    public void waitForEmptyResultLabel(){
        this.waitMethod(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15
        );
    }
    public void assertThereIsNotResultOfSearch(){
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "Amount of found elements supposed to be zero"
        );
    }
    public List<WebElement> getListOfFoundSearchElements() {
        List<WebElement> listOfElements = this.waitForElements(
                SEARCH_RESULT_ITEMS_LIST,
                "Cannot find any element",
                20
        );
        return listOfElements;
    }
    public boolean checkForTextContains(List<WebElement> listOfElements, String text){
        return this.textContains(listOfElements, text);
    }
    public void checkForSearchText(){
        WebElement webElement = this.waitMethod(
                SEARCH_SRC_TEXT,
                "Cannot find 'search_src_text' element by its id",
                5
        );
        if(!webElement.getText().equals("Search…")){
            throw new AssertionError("Text into search field doesn't match with 'Search…' text");
        }
    }
    public void waitForElementByTitleAndDescription(String title, String desc){
        String titleXpath = getResultSearchElement(title);
        this.waitMethod(
                titleXpath,
                "Cannot find article title by substring.\nTitle: "+title
        );
        String descXpath = getResultSearchElement(desc);
        this.waitMethod(
                descXpath,
                "Cannot find description by substring.\nDescription: "+desc
        );
    }
}
