package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.xml.sax.Locator;

public class SimulationPage extends ClaseBase {

    //CENTRALIZAR LOCALIZADOR
    By locatorBtnProductos = By.xpath("//button[@data-menu='productos-y-servicios']");
    By locatorCreditoConsumo = By.xpath("//div//a[@id='ppp_header-link-productos_credito_de_consumo']");
    By locatorSimularCredito = By.xpath("//div//a[@id='btn_bch_simula-credito-de-consumo']");
    By locatorInputRut = By.xpath("//input[@name='rut' and @placeholder='Ingresa tu RUT']");

    String locatorIframeBchSim = "//iframe[@title='iframe simulador credito de consumo']";

    By locatorIndicaRutNok = By.xpath("//div//small[@class='invalid display-block ng-scope']");

    By locatorBtnValidarRut= By.xpath("//button//span[text()='Validar RUT']");

    By labelTramoRenta= By.xpath("//label[text()='Tramo de renta']");

    By locatorSelectRenta= By.xpath("//div[@placeholder='Selecciona...']");

    By locatorPrimeraRenta= By.xpath("//div[@role='option']//span[contains(text(),'500.000')]");
    //By locatorBtnSimular= By.xpath("//button[@ng-click='simulaCredito.simular()']//span[1]");
    By locatorBtnSimular= By.xpath("//div//button//span[contains(text(),'SIMULAR')]");


    By locatorMontoSolicitado = By.xpath("//div[@ng-class='activeOpcion1']//p[text()='Monto solicitado']");
    By locatorDatosPersonales = By.xpath("//h3[text()='Datos personales']");
    By locatorTxtBoldIncapacidad = By.xpath("//span//b[text()='Incapacidad']");

    By locatorBtnVolverSimular= By.xpath("//button[text()='Volver a simular' and position()=1]");




    ///Crear el ejecutor de javascript para el scrolling via evento
    private JavascriptExecutor js;




    //Método que realicen las acciones del sitio
    public SimulationPage(WebDriver driver) {
        super(driver);
    }

    public void clickBtnProductosYServicios(){
        esperarXSegundos(10);
        click(esperarPorElementoAClickear(locatorBtnProductos));
    }
    public void clickBtnCreditoConsumo(){
        click(esperarPorElementoAClickear(locatorCreditoConsumo));
    }
    public void clickBtnSimConsumo(){
        click(esperarPorElementoAClickear(locatorSimularCredito));
    }

    public void inputRut(String rut) {
        esperarPresenciaWebElement(By.xpath(locatorIframeBchSim));
        entraFrame((locatorIframeBchSim));
        agregarTexto(esperarPresenciaWebElement(locatorInputRut), rut);
    }

    public void entraFrame(String elemento){
        WebElement iframe = driver.findElement(By.xpath(elemento));
        driver.switchTo().frame(iframe);
    }

    public void validaTextoRutNok(String rutNok){
        String claseRutNok= getElementClass();
        Assertions.assertEquals(claseRutNok, rutNok);
    }

    public String getElementClass() {
        esperarXSegundos(5000);
        return obtenerClaseElemento((locatorIndicaRutNok));
    }

    public void clickBtnValidarRut(){
        click(esperarPorElementoAClickear(locatorBtnValidarRut));
    }

    public void validaRutSimulacion(){
        clickBtnValidarRut();
        String txtTramo= obtenerTexto(labelTramoRenta);
        Assertions.assertEquals(txtTramo, "Tramo de renta");
    }

    public void validaSimulacionOk(String montoSolicitado) throws InterruptedException {
        click(esperarPorElementoAClickear(locatorBtnValidarRut));
        Thread.sleep(5000);
        click(esperarPorElementoAClickear(locatorSelectRenta));
        click(esperarPorElementoAClickear(locatorPrimeraRenta));
        Thread.sleep(5000);
        WebElement txtIncap = driver.findElement(locatorTxtBoldIncapacidad);
        this.js = (JavascriptExecutor) driver;
        this.js.executeScript("arguments[0].scrollIntoView(true);",txtIncap);
        Thread.sleep(5000);
        click(locatorBtnSimular);
        Thread.sleep(30000);
        String txtMontoSolicitado = obtenerTexto((locatorMontoSolicitado));
        Assertions.assertEquals(txtMontoSolicitado, montoSolicitado);

    }


    public void volverASimular(String datosPersonales) throws InterruptedException {
        click(esperarPorElementoAClickear(locatorBtnValidarRut));
        Thread.sleep(5000);
        click(esperarPorElementoAClickear(locatorSelectRenta));
        click(esperarPorElementoAClickear(locatorPrimeraRenta));
        Thread.sleep(5000);
        WebElement txtIncap = driver.findElement(locatorTxtBoldIncapacidad);
        this.js = (JavascriptExecutor) driver;
        this.js.executeScript("arguments[0].scrollIntoView(true);",txtIncap);
        Thread.sleep(5000);
        click(locatorBtnSimular);
        Thread.sleep(30000);
        WebElement txtMontoSol = driver.findElement(locatorMontoSolicitado);
        this.js.executeScript("arguments[0].scrollIntoView(true);",txtMontoSol);
        Thread.sleep(1000);
        click(locatorBtnVolverSimular);
        Thread.sleep(30000);

        String txtDatosPersonales = obtenerTexto((locatorDatosPersonales));
        Assertions.assertEquals(txtDatosPersonales, datosPersonales);

    }


}
