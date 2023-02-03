package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class AddAndRemoveArticle2NdPart extends CoreTestCase {
    @Test
    public void testAddTwoArticlesAndRemoveOne(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(appiumDriver);
        NavigationUI navigationUI = NavigationUIFactory.get(appiumDriver);

        // Adding first article to list
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickOnArticleWithSubstring("Java (programming language)");

        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToMyList("First article");
        articlePageObject.closeArticle();

        // Adding second article to list
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickOnArticleWithSubstring("Java");

        articlePageObject.waitForTitleElement();
        articlePageObject.addSecondArticleToMyList("Second article");
        articlePageObject.closeArticle();

        // Go to my list section
        navigationUI.myList();

        articlePageObject.deleteArticleByOverflowAndCheckForAnother("Second article");
        articlePageObject.openArticleByTextSubstring("Java");
        String actualTitle = articlePageObject.getArticleTitle();
        assertEquals(
                "Titles are not equal",
                "Java",
                actualTitle
        );
    }
}
