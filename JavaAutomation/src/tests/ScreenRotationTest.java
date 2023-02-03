package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;

import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ScreenRotationTest extends CoreTestCase {
    @Test
    public void testRotation(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickOnArticleWithSubstring("Java (programming language)");

        String articleTitleBeforeFirstRotation = articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String articleTitleAfterFirstRotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title has been changed after first rotation",
                articleTitleBeforeFirstRotation,
                articleTitleAfterFirstRotation
        );

        String articleTitleBeforeSecondRotation = articlePageObject.getArticleTitle();

        this.rotateScreenPortrait();

        String articleTitleAfterSecondRotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title has been changed after second rotation",
                articleTitleBeforeSecondRotation,
                articleTitleAfterSecondRotation
        );
    }
    @Test
    public void testFindArticleAndChangeOrientation(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        this.rotateScreenLandscape();
        searchPageObject.clickOnArticleWithSubstring("Java (programming language)");
    }
}
