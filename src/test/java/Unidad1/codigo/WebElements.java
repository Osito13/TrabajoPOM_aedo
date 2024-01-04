package Unidad1.codigo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class WebElements {
    public static void main(String[] args) throws InterruptedException, AWTException {

        String rutaProyecto = System.getProperty("user.dir"); //Parametrizar el driver

        String rutaDriver = rutaProyecto + "\\src\\test\\resources\\drivers\\chromedriver.exe";

        //Enlazar el driver via property en el sistema
        System.setProperty("webdriver.chrome.driver", rutaDriver);

        //Instanciar objetos para emular el browser
        WebDriver driver = new ChromeDriver();

        //Crear el ejecutor de javascript para el scrolling via evento
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://www.bci.cl/personas");

        Thread.sleep(2000);

        //Localizador (By) es una forma de localizar los elementos web de un sitio
        //Existen 8 estrategias de busqueda:
        // ID,className, Name, tagname, linktext, partialLinktext, xpath(Relativo/Absoluto),cssSelector

        //By
        By localizadorBtnHazteCliente = By.xpath("//button[contains(text(),'Hazte Cliente')]");

        //Elemento Web (Botón, txt, dropDownList, label, link, etc)
        WebElement btnHazteCliente = driver.findElement(localizadorBtnHazteCliente);

        btnHazteCliente.click();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@data-slide='next']")).click();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[contains(text(),'Abrir plan cuenta corriente')]")).click();

        driver.manage().window().maximize();

        //Agregar texto en inputs
        Thread.sleep(3000);

        int size = driver.findElements(By.tagName("iframe")).size();

        System.out.println("el sitio cuenta: " + size + " iframes");

        driver.switchTo().frame(0);

        Thread.sleep(1000);

        driver.findElement(By.id("names")).sendKeys("Domingo");

        Thread.sleep(1000);

        driver.findElement(By.name("apellidoPaterno")).sendKeys("Saavedra");

        Thread.sleep(1000);

        driver.findElement(By.name("apellidoMaterno")).sendKeys("Saavedra");

        Thread.sleep(1000);

        driver.findElement(By.name("rut")).sendKeys("178152513");

        Thread.sleep(1000);

        WebElement telefono = driver.findElement(By.name("telefono"));

        telefono.sendKeys("99998765");

        js.executeScript("arguments[0].scrollIntoView();", telefono);

        Thread.sleep(1000);

        WebElement correo = driver.findElement(By.name("correoElectronico"));

        js.executeScript("arguments[0].scrollIntoView();", correo);

        correo.sendKeys("corroe@algo.com");

        Thread.sleep(25000);

        driver.findElement(By.id("checkPrivacidad")).click();

        Thread.sleep(1000);

        WebElement btnContinuar = driver.findElement(By.id("botonOculto"));

        if (btnContinuar.isEnabled()) {
            btnContinuar.click();
        }

        Thread.sleep(5000);

        WebElement btnRetomar = driver.findElement(By.xpath("//button[contains(text(),'RETOMAR')]"));

        js.executeScript("arguments[0].scrollIntoView();", btnRetomar);
        js.executeScript("window.scrollBy(0,-950)", "");

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);

        Thread.sleep(2000);

        WebElement txtTituloMensajeError = driver.findElement(By.xpath("//p[contains(text(),'Al parecer')]"));

        String textoTituloError = txtTituloMensajeError.getText();

        WebElement txtDescMensajeError = driver.findElement(By.xpath("//p[contains(text(),' Seg')]"));

        String textoDescError = txtDescMensajeError.getText();

        js.executeScript("arguments[0].scrollIntoView();", txtDescMensajeError);

        System.out.println("Titulo Error: "+ textoTituloError);

        System.out.println("Descrpción Error: "+ textoDescError);

        System.out.println("Btn RETOMAR desplegado: "+ btnRetomar.isEnabled());

        driver.quit();


    }
}
