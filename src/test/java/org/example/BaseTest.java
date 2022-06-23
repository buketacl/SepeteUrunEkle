package org.example;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.annotation.Native;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;

    @BeforeScenario
    public void setUp() throws MalformedURLException, Exception{
        String BaseUrl = "https://www.google.com.tr/";
        DesiredCapabilities capabilities;
        capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY,options);
        System.setProperty("webdriver.chrome.driver","src/test/resource/chromedriver");
        driver= new ChromeDriver(capabilities);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS).implicitlyWait(10,TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get(BaseUrl);

    }
    @AfterScenario
    public void tearDown() throws Exception{
        driver.quit();

    }

}
