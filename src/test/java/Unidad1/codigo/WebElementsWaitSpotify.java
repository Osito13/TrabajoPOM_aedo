package Unidad1.codigo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class WebElementsWaitSpotify {

    //@Test
    public void CP002_ya_se_me_ocurrira(){
        Assertions.assertTrue(true);
    }

    //@Test
    public void CP003_otra_cosa(){
        Assertions.assertTrue(true);
    }


    //@Test
    public void CP001_Creacion_Cta_Spotify() throws InterruptedException {

        String rutaProyecto = System.getProperty("user.dir");

        String rutaDriver = rutaProyecto + "\\src\\test\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", rutaDriver);

        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://open.spotify.com/");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        By localizadorRegistrarte = By.xpath("//button[contains(text(),'Reg')]");

        WebElement btnRegistrarte = driver.findElement(localizadorRegistrarte);

        btnRegistrarte.click();

        driver.manage().window().maximize();


        driver.findElement(By.id("username")).sendKeys("emas1sdfsdf1dfsd23@algo.com");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        driver.findElement(By.id("new-password")).sendKeys("Frre3232");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("displayName")).sendKeys("Curso BCI Selenium");

        WebDriverWait wait = new WebDriverWait(driver,10);


        WebElement txtDay = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//input[@id='day']")));

        txtDay.sendKeys("28");

        Select drpCountry = new Select(wait.until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//select[@id='month']"))));

        drpCountry.selectByValue("10");

        WebElement txtYeah = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//input[@id='year']")));

        txtYeah.sendKeys("1991");

        Thread.sleep(3000);


        js.executeScript("window.scrollBy(0,100)");

        List<WebElement> generos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(@for,'gender')]")));

        generos.get(0).click();
        Thread.sleep(2000);
        generos.get(1).click();
        Thread.sleep(2000);
        generos.get(2).click();
        Thread.sleep(2000);
        generos.get(3).click();
        Thread.sleep(2000);
        generos.get(4).click();
        Thread.sleep(2000);
        generos.get(0).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        Thread.sleep(3000);

        List<WebElement> checks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(@for,'checkbox')]")));

        if(checks.size()==2){
            checks.get(0).click();
            Thread.sleep(1000);
            checks.get(1).click();
            Thread.sleep(1000);
            checks.get(0).click();
            Thread.sleep(1000);
            checks.get(1).click();
            Thread.sleep(1000);
            checks.get(0).click();
            Thread.sleep(1000);
            checks.get(1).click();
            Thread.sleep(1000);
        }

        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        Thread.sleep(4000);

        String username = driver.findElement(By.xpath("//button[@data-testid='user-widget-link']")).getAttribute("aria-label");

        Assertions.assertEquals("Curso BCI Selenium",username);
    }
}
