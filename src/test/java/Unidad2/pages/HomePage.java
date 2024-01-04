package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ClaseBase {

    //CENTRALIZAR LOCALIZADOR
    By localizadorRegistrarte = By.xpath("//button[contains(text(),'Reg')]");
    By localizadorIniciarSesion = By.xpath("//button[@data-testid='login-button']");
    By locatorUsername = By.xpath("//button[@data-testid='user-widget-link']");


    //Método que realicen las acciones del sitio
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void irARegistrarte(){
        click(esperarPorElementoAClickear(localizadorRegistrarte));
    }
    public void irAIniciarSesion(){
        click(esperarPorElementoAClickear(localizadorIniciarSesion));
    }

    public String getUsername() {
        esperarXSegundos(5000);
       return obtenerAtributoAriaLabel((locatorUsername));
    }
}
