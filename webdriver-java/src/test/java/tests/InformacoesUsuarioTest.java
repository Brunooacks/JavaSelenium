package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Utils;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")

public class InformacoesUsuarioTest {

    private WebDriver navegador ;

    @Rule

    public TestName test = new TestName();

    @Before
        public void Setup(){

        navegador = Utils.createChrome();

        //  Aguardar  a exibicao do link Sign In
        WebElement linkSignin = navegador.findElement(By.linkText("Sign in"));
        linkSignin.click();

        // Identificando o formulario de login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com name "login " que esta dentro do formulario de id "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        // Digitar no campo com name "password" que esta dentro do formulario de id "signinbox" o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link com o texto "sign in"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Validar que dentro do elemento com class "me"  esta o texto "Hi, Julio"
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, Julio", textoNoElementoMe);

        // Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar no link que possui o texto "More data about you"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();


    }

// notacao para utilizacao do Junit
   @Test
        public void testAdicionarUmaInformacaoAdicionalDoUsuarioPositivo(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) {

        // Clicar no botao atraves do seu  xpath //button[@data-target="addmoredata"] ou //div[@id="moredata"]//button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a popup onde esta o formulario de id addmoredata
        WebElement popupAddmoreData = navegador.findElement(By.id("addmoredata"));

        // Na combo de name "type" escolhe a opcao "phone"
        WebElement campoType = popupAddmoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //No campo de name "contact" digitar "+5521999990000"
        popupAddmoreData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link de text "SAVE" que esta no popup
        popupAddmoreData.findElement(By.linkText("SAVE")).click();

        //  Na mensagem de id "toast-container" validar que o texto

        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);

       Screenshot.tirar(navegador, "/Usuários/bruno_oliveira/Documentos/new/" + Generator.dataHoraParaArquivo()
               + test.getMethodName() + ".png");
       
    }

    @Test
        public void removerUmContatoDeUmUsuario(){
        // Clicar no elemento pelo seu xpath //span[text()="NUMBERPHONE"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"11941049091\"]/following-sibling::a")).click();
        // Confirmar a janela java script
        navegador.switchTo().alert().accept();
        // validar que a mensagem apresentada foi "Rest in peace, dear phone!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        // print da validacao

        Screenshot.tirar(navegador, "/Usuários/bruno_oliveira/taskit/" + Generator.dataHoraParaArquivo()
                + test.getMethodName() + ".jpeg");

        // Aguardar ate 10 segundo para que a janela desapareca utilizando espera explicitas
        WebDriverWait aguardar = new WebDriverWait(navegador, 10 );
        //Definicao da esperar explicita
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
        // Clicar no link com texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

     @After
       public void TearDown(){
            // Fechar o navegador
             navegador.quit();
        }
    }