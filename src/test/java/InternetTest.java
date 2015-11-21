import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class InternetTest {

    private WebDriver driver;
    private final static String BASE_URL = "https://the-internet.herokuapp.com/";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void elementGotVisibleTest(){
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.partialLinkText("Example 1")).click();
        WebElement startButton = driver.findElement(By.cssSelector("#start>button"));
        WebElement finishBlock = driver.findElement(By.id("finish"));
        startButton.click();
        Assert.assertFalse(startButton.isDisplayed(), "startButton did not disappear");
        WebDriverWait wait = new WebDriverWait(driver, 12);
        wait.until(ExpectedConditions.visibilityOf(finishBlock));
        Assert.assertTrue(finishBlock.isDisplayed(), "finishBlock is invisible");
        Assert.assertEquals(finishBlock.getText(), "Hello World!");
    }

    @Test
    public void elementAppearedTest() {
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.partialLinkText("Example 2")).click();
        WebElement startButton = driver.findElement(By.cssSelector("#start>button"));
        startButton.click();
        Assert.assertFalse(startButton.isDisplayed(), "startButton did not disappear");
        By finish = By.id("finish");
        WebDriverWait wait = new WebDriverWait(driver, 12);
        wait.until(ExpectedConditions.visibilityOfElementLocated(finish));
        WebElement finishBlock = driver.findElement(finish);
        Assert.assertTrue(finishBlock.isDisplayed(), "finishBlock is invisible");
        Assert.assertEquals(finishBlock.getText(), "Hello World!");
    }
}
