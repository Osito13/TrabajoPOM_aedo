package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends ClaseBase {
    //Centralizar los By
    By localizadorIniciarSesion = By.xpath("//button[@data-testid='login-button']");
    By localizadorUsername = By.id("login-username");
    By localizadorPass  = By.id("login-password");
    By localizadorLoginButton = By.xpath("//button[@data-testid='login-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Acciones
    public void login(String user,String pass){
        agregarTexto(esperarPresenciaWebElement(localizadorUsername),user);
        agregarTexto(esperarPresenciaWebElement(localizadorPass),pass);
        click(esperarPorElementoAClickear(localizadorLoginButton));
    }

    public String obtenerMensajeErrorLogin(){
        return "";
    }
}
