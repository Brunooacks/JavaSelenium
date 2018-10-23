package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestesNegativos {

    private WebDriver navegador ;


    @Before
    public void Setup(){
        // Configuracao do webdriver e setar qual driver abrir
        System.setProperty("webdriver.chrome.driver" , "/usr/local/bin/chromedriver");
        navegador = new ChromeDriver();
        //Definir um timeout de forma implicita
        navegador.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // Tela maximizada
        // Windowns navegador.manage().window().maximize();
        navegador.manage().window().setSize(new Dimension(1280, 800));

        // Navegando para a pagina do globo.com
        navegador.get("http://www.juliodelima.com.br/taskit/");
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuarioNegativo() {


        //  Aguardar  a exibicao do link Sign In
        WebElement linkSignin = navegador.findElement(By.linkText("Sign in"));
        linkSignin.click();

        // Identificando o formulario de login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com name "login " que esta dentro do formulario de id "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0002");

        // Digitar no campo com name "password" que esta dentro do formulario de id "signinbox" o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link com o texto "sign in"

        navegador.findElement(By.linkText("SIGN IN")).click();

        // Validar que dentro do elemento com class "me"  esta o texto "Hi, Julio"
        //   WebElement me = navegador.findElement(By.className("toast rounded"));
        //   String textoNoElementoMe = me.getText();
        //   assertEquals("Maybe you brain dropped the password or login in some place!", textoNoElementoMe);

    }

    @After
    public void TearDown(){
        // Fechar o navegador
        navegador.quit();
    }
}

