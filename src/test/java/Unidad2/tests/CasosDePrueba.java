package Unidad2.tests;

import Unidad2.pages.HomePage;
import Unidad2.pages.LoginPage;
import Unidad2.pages.RegisterPage;
import Unidad2.pages.SimulationPage;
import Unidad2.utils.DataDriven;
import Unidad2.utils.PropertiesManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class CasosDePrueba {
    private WebDriver driver; //null
    private SimulationPage simPage;
    private RegisterPage registerPage;
    private LoginPage loginPage; //null
    private String browser = PropertiesManager.obtenerProperty("browser");
    private String propertyDriver = PropertiesManager.obtenerProperty("propertyDriver");
    private String rutaDriver = PropertiesManager.obtenerProperty("rutaDriver");
    private String url = PropertiesManager.obtenerProperty("url");
    private ArrayList<String> datosPrueba; //null

    @BeforeEach
    public void preparacionTests(){
        datosPrueba = new ArrayList<String>(); //arreglo tamaño 0
        simPage = new SimulationPage(driver);
        simPage.conexionDriver(browser,rutaDriver,propertyDriver);
        simPage.manejoEsperasElementosWeb(10);
        registerPage = new RegisterPage(simPage.getDriver());
        loginPage = new LoginPage(simPage.getDriver());
        simPage.maximizarBrowser();
        simPage.cargarURL(url);

    }

    @Test
    public void CP001_acceso_simulador(){
        simPage.clickBtnProductosYServicios();
        simPage.clickBtnCreditoConsumo();
        simPage.clickBtnSimConsumo();
        Assertions.assertTrue(true);
    }

    @Test
    public void CP002_validarInputRutInvalido(){
        datosPrueba = DataDriven.prepararData("CP002_valida_rut_invalido");
        simPage.clickBtnProductosYServicios();
        simPage.clickBtnCreditoConsumo();
        simPage.clickBtnSimConsumo();
        simPage.inputRut(datosPrueba.get(1));
        simPage.validaTextoRutNok(datosPrueba.get(2));

    }

    @Test
    public void CP003_validarInputRut_ok(){
        datosPrueba = DataDriven.prepararData("CP003_valida_rut_ok");
        simPage.clickBtnProductosYServicios();
        simPage.clickBtnCreditoConsumo();
        simPage.clickBtnSimConsumo();
        simPage.inputRut(datosPrueba.get(1));
        simPage.validaRutSimulacion();
        System.out.println("fin");
    }

    @Test
    public void CP004_validarSimulacion() throws InterruptedException {
        datosPrueba = DataDriven.prepararData("CP004_validarSimulacion");
        simPage.clickBtnProductosYServicios();
        simPage.clickBtnCreditoConsumo();
        simPage.clickBtnSimConsumo();
        simPage.inputRut(datosPrueba.get(1));
        simPage.validaSimulacionOk(datosPrueba.get(2));
    }

    @Test
    public void CP005_validarVolverASimular() throws InterruptedException {
        datosPrueba = DataDriven.prepararData("CP005_validarVolverSimular");
        simPage.clickBtnProductosYServicios();
        simPage.clickBtnCreditoConsumo();
        simPage.clickBtnSimConsumo();
        simPage.inputRut(datosPrueba.get(1));
        simPage.volverASimular(datosPrueba.get(2));

    }

    @AfterEach
    public void posEjecucion(){
        simPage.cerrarBrowser();
    }
}
