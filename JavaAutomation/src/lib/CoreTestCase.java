package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

    public class CoreTestCase extends TestCase {
        public AppiumDriver appiumDriver;
        @Override
        protected void setUp()throws Exception{
        super.setUp();
        appiumDriver = Platform.getInstance().getDriver();
        // We use this to make sure that each test uses the right screen orientation
        this.rotateScreenPortrait();
    }
    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
        appiumDriver.quit();
    }
    protected void rotateScreenPortrait(){
        appiumDriver.rotate(ScreenOrientation.PORTRAIT);
    }
    protected void rotateScreenLandscape(){
        appiumDriver.rotate(ScreenOrientation.LANDSCAPE);
    }
    protected void backgroundApp(int seconds){
        appiumDriver.runAppInBackground(seconds);
    }
}
