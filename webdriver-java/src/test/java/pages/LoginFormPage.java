package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sun.rmi.runtime.Log;

//Modelo estrutural para cada acao na pagina
public class LoginFormPage extends BasePage {


    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage digitarLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        return this;
    }

    public LoginFormPage digitarSenha(String password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
        return this;
    }

    public SecretaPage clicarSigIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new SecretaPage(navegador);
    }

  // Modelo Funcional
      public SecretaPage fazerLogin(String login , String password){
        digitarLogin(login);
        digitarSenha(password);
        clicarSigIn();
        return new SecretaPage(navegador);
    }

}
