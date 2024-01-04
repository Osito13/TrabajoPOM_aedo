package Unidad1.codigo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserActions {
    public static void main(String[] args) throws InterruptedException{

        String rutaProyecto = System.getProperty("user.dir"); //Parametrizar el driver

        String rutaDriver = rutaProyecto+"\\src\\test\\resources\\drivers\\chromedriver.exe";

        //Enlazar el driver via property en el sistema
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar objetos para emular el browser
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.cl");

        System.out.println("EL titulo del sitio es: "+driver.getTitle());

        System.out.println("La url del sitio es: "+driver.getCurrentUrl());

        Thread.sleep(5000);

        driver.navigate().to("https://www.tsoftglobal.com");

        System.out.println("EL titulo del sitio es: "+driver.getTitle());

        System.out.println("La url del sitio es: "+driver.getCurrentUrl());

        Thread.sleep(5000);

        driver.navigate().back();

        Thread.sleep(2000);

        driver.navigate().refresh();

        Thread.sleep(2000);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.manage().window().fullscreen();

        Thread.sleep(2000);

        driver.manage().window().maximize();

        Thread.sleep(2000);

        driver.quit();


    }
}
