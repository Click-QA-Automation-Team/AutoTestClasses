package tests;

import lib.Platform;
import lib.ui.*;
import lib.CoreTestCase;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class AddAndRemoveArticleClass extends CoreTestCase {
    @Test
    public void testAddArticleToList(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(appiumDriver);
        NavigationUI navigationUI = NavigationUIFactory.get(appiumDriver);
        MyListPageObject myListPageObject = MyListsPageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickOnArticleWithSubstring("Java (programming language)");
        String articleTitle = articlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            articlePageObject.addArticleToMyList("Learning programming");
        }else{
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();

        navigationUI.myList();

        if(Platform.getInstance().isAndroid()){
            myListPageObject.openFolderByName("Learning programming");
        }
        if(Platform.getInstance().isIOS()){
            myListPageObject.clickElementToTheRightUpperCorner(
                    articleTitle,
                    "Cannot find saved article.\nArticle: "+articleTitle
            );
        }
        myListPageObject.swipeArticleToDelete(articleTitle);
    }
    @Test
    public void testAmountOfSearchResults(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("List of French Formula One engine manufacturer");
        int amountOfElementsBySearchCondition = searchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Cannot find any element by searching condition",
                amountOfElementsBySearchCondition > 0
        );
    }
    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("aaawwweee");
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNotResultOfSearch();
    }
}
