package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Driver {

    public WebDriver setDriver(String pageName) {
        System.setProperty("webdriver.chrome.driver", "/Users/roman/IdeaProjects/webDriver/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.get(pageName);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}
