package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class AppBackgroundTest extends CoreTestCase {
    @Test
    public void testAppBackground(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");

        this.backgroundApp(5);

        searchPageObject.waitForSearchResult("Java (programming language)");
    }
}
