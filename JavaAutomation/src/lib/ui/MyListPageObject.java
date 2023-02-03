package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class MyListPageObject extends MainPageObject {
    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL;
    public MyListPageObject(AppiumDriver appiumDriver){
        super(appiumDriver);
    }
    private static String getFolderXpathByName(String nameOfFolder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }
    private static String getSavedArticleXpathByTitle(String articleTitle){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }
    public void openFolderByName(String nameOfFolder){
        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(
                folderNameXpath,
                "Cannot find folder by name "+nameOfFolder,
                5
        );
    }
    public void waitForArticleAppearByTitle(String articleTitle){
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitMethod(
                articleXpath,
                "Cannot find saved article by title "+articleTitle,
                15
        );
    }
    public void waitForArticleDisappearByTitle(String articleTitle){
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(
                articleXpath,
                "Saved article with title "+articleTitle+" is still present ",
                15
        );
    }
    public void swipeArticleToDelete(String articleTitle){
        this.waitForArticleAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.swipeElementToLeft(
                articleXpath,
                "Cannot find saved article"
        );
        this.waitForArticleDisappearByTitle(articleTitle);
    }
}
