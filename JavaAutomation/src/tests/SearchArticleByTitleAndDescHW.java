package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchArticleByTitleAndDescHW extends CoreTestCase {
    @Test
    public void testSearchArticleByTitleAndDesc(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForElementByTitleAndDescription(
                "Java (programming language)",
                "Object-oriented programming language"
        );
    }
    @Test
    public void testCheckSearchResultContains(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(appiumDriver);
        String searchLine = "Java";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);

        List<WebElement> elements = searchPageObject.getListOfFoundSearchElements();

        assertTrue(elements.size() >= 3);

        for(int i = 0; i <= 2; i++){
            if(elements.get(i).getText().contains(searchLine)){
                continue;
            }else{
                throw new AssertionError(searchLine+ " doesn't contain in each line");
            }
        }
    }
}
