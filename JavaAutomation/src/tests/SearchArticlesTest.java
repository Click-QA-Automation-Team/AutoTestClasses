package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchArticlesTest extends CoreTestCase {
    @Test
    public void testFindSomeArticle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelAction(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonAppears();
        //searchPageObject.typeSearchLine("Java");
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonDisappears();
    }
    @Test
    public void testArticleTitleCompare(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickOnArticleWithSubstring("Object-oriented programming language");

        String article_title = articlePageObject.getArticleTitle();

        assertEquals(
                "Titles are not equal",
                "Java (programming language)",
                article_title
        );
    }
    @Test
    public void testEmptySearchResult(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Javasadasdasdad");
        searchPageObject.waitForEmptySearchResult();
    }
}