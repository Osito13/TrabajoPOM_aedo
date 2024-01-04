package Unidad1.codigo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class WebElementsWait {
    public static void main(String[] args) throws InterruptedException, AWTException {

        String rutaProyecto = System.getProperty("user.dir");

        String rutaDriver = rutaProyecto + "\\src\\test\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", rutaDriver);

        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://www.bci.cl/personas");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        By localizadorBtnHazteCliente = By.xpath("//button[contains(text(),'Hazte Cliente')]");

        WebElement btnHazteCliente = driver.findElement(localizadorBtnHazteCliente);

        btnHazteCliente.click();

        driver.findElement(By.xpath("//button[@data-slide='next']")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Abrir plan cuenta corriente')]")).click();

        driver.manage().window().maximize();

        int size = driver.findElements(By.tagName("iframe")).size();

        System.out.println("el sitio cuenta: " + size + " iframes");

        driver.switchTo().frame(0);

        driver.findElement(By.id("names")).sendKeys("Domingo");

        driver.findElement(By.name("apellidoPaterno")).sendKeys("Saavedra");

        driver.findElement(By.name("apellidoMaterno")).sendKeys("Saavedra");

        driver.findElement(By.name("rut")).sendKeys("178152513");

        WebElement telefono = driver.findElement(By.name("telefono"));

        telefono.sendKeys("99998765");

        WebElement correo = driver.findElement(By.name("correoElectronico"));

        js.executeScript("arguments[0].scrollIntoView();", correo);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);

        correo.sendKeys("corroe@algo.com");

        Thread.sleep(25000);

        driver.findElement(By.id("checkPrivacidad")).click();

        WebElement btnContinuar = driver.findElement(By.id("botonOculto"));

        if (btnContinuar.isEnabled()) {
            btnContinuar.click();
        }



        robot.keyPress(KeyEvent.VK_PAGE_UP);


        WebElement txtTituloMensajeError = driver.findElement(By.xpath("//p[contains(text(),'Al parecer')]"));

        String textoTituloError = txtTituloMensajeError.getText();

        WebElement txtDescMensajeError = driver.findElement(By.xpath("//p[contains(text(),' Seg')]"));

        String textoDescError = txtDescMensajeError.getText();

        js.executeScript("arguments[0].scrollIntoView();", txtDescMensajeError);

        System.out.println("Titulo Error: "+ textoTituloError);

        System.out.println("Descrpción Error: "+ textoDescError);

        WebElement btnRetomar = driver.findElement(By.xpath("//button[contains(text(),'RETOMAR')]"));
        js.executeScript("arguments[0].scrollIntoView();", btnRetomar);

        System.out.println("Btn RETOMAR desplegado: "+ btnRetomar.isEnabled());

        driver.quit();


    }
}
