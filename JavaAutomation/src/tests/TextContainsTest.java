package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TextContainsTest extends CoreTestCase {
    MainPageObject mainPageObject;
    @Override
    protected void setUp() throws Exception{
        super.setUp();
        mainPageObject = new MainPageObject(appiumDriver);
    }
    @Test
    public void testCheckTextIntoSearchField(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        List<WebElement> listOfElements = searchPageObject.getListOfFoundSearchElements();

        assertTrue(searchPageObject.checkForTextContains(listOfElements, "Java"));
        assertTrue(listOfElements.size() > 1);

        searchPageObject.clickCancelSearch();
        searchPageObject.checkForSearchText();
        searchPageObject.checkForSearchText();
    }
}
