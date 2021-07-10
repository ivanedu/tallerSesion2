import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WhenDo {

    private AppiumDriver driver;

    @BeforeEach
    public void setupConecction() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","G3223");
        capabilities.setCapability("platformVersion","8");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity",".ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void verifyCreateNote() throws InterruptedException {
        //click ok button-dialog
        // si existe el dialog
        if (driver.findElements(By.id("android:id/button1")).size()==1)
            driver.findElement(By.id("android:id/button1")).click();

        //click add button
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        Thread.sleep(5000);
        //set titulo
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys("titulo1");
        // set notas
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("nota1");


        //click Save button (Check)
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
        Thread.sleep(2000);
        WebDriverWait explicitWait= new WebDriverWait(driver,10);

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='titulo1']")));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='titulo1']")));
        //verificar la nota
        Assertions.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='titulo1']")).isDisplayed());

    }

    @AfterEach
    public void closeConnectyion() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}