package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTitleExistTest extends CoreTestCase {
    MainPageObject mainPageObject;
    @Override
    protected void setUp() throws Exception{
        super.setUp();
        mainPageObject = new MainPageObject(appiumDriver);
    }
    @Test
    public void testAttributeExistTest(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickOnArticleWithSubstring("Java (programming language)");

        articlePageObject.getArticleTitle();
    }
}
