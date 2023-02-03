package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ScrollWindowTest extends CoreTestCase {
    @Test
    public void testWindowScroll(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickOnArticleWithSubstring("Java (programming language)");

        articlePageObject.scrollUpMethod(1000);
        articlePageObject.scrollUpMethod(1000);
        articlePageObject.scrollUpMethod(1000);
    }
}