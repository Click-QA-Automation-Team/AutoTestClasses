package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {
    private static final String
            PLATFORM_ANDROID = "android",
            PLATFORM_IOS = "ios",
            APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    // Singleton
    private static Platform instance;
    private Platform(){};
    public static Platform getInstance(){
        if(instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception{
        URL url = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return new AndroidDriver(url, this.getAndroidDesiredCapabilities());
        }else if(this.isIOS()){
            return new IOSDriver(url, this.getIOSDesiredCapabilities());
        }else{
            throw new Exception("Cannot identify the type of driver.\nPlatform: "+this.getPlatformVar());
        }
    }
    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }
    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "GoogleNexus4");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Ruslan\\Desktop\\Automation\\Tools\\org.wikipedia.apk");
        return capabilities;
    }
    private DesiredCapabilities getIOSDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 12 pro");
        capabilities.setCapability("platformVersion", "15.7");
        capabilities.setCapability("app", "C:\\Users\\Ruslan\\Desktop\\Automation\\Tools\\Wikipedia.app");
        return capabilities;
    }
    private boolean isPlatform(String myPlatform){
        String platform = this.getPlatformVar();
        return myPlatform.equals(platform);
    }
    private String getPlatformVar(){
        return System.getenv("PLATFORM");
    }
}
