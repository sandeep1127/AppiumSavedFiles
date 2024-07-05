package CopiedJavaClasses;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileCapabilityType;         >> MobileCapabilityType has been depricated
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BetterDriverSession {

    public static void main(String[] args) throws Exception {
    	initializeDriver("android");
    }

    public static AppiumDriver initializeDriver(String platformName) throws Exception {                // Here we created method which has argument on whose basis platform will be decided and it would return that platform's driver object.
        DesiredCapabilities caps = new DesiredCapabilities();

       // caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName); //  "MobileCapabilityType" is depricated, so use below line code.
        caps.setCapability(CapabilityType.PLATFORM_NAME, platformName);
        caps.setCapability("newCommandTimeout", 300);

        URL url = new URL("http://0.0.0.0:4723/");

        switch (platformName) {
            case "Android":
              //  caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nokia5.4");             // Instead of this, use below line code as "MobileCapabilityType" is depricated
            	caps.setCapability("deviceName",  "Nokia5.4");
            	caps.setCapability("automationName", "UiAutomator2");
            	caps.setCapability("udid", "PD21BDD464038424");

                String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "Resources" + File.separator + "ApiDemos-debug.apk";
                caps.setCapability("appPackage", "io.appium.android.apis");
                caps.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
                return new AndroidDriver(url, caps);

            case "IOS":
            	caps.setCapability("deviceName", "iPhone 12");
            	caps.setCapability("automationName", "XCUITest");
            	caps.setCapability("udid", "PD21BDD464038424");

                String iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "Resources" + File.separator + "ApiDemos-debug.apk";
                caps.setCapability("simulatorStartuptimeout", 180000);
                caps.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
                return new IOSDriver(url, caps);

            default:
                throw new Exception("Invalid platform. Please check your code");
        }
    }
}
