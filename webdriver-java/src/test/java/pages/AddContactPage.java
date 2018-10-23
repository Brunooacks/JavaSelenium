package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage{
    public AddContactPage(WebDriver navegador) {
        super(navegador);
    }

    public AddContactPage escolherTipoDeContato(String tipo){
        WebElement campoType = navegador.findElement(By.id("addmoredata")).findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);
        return this;
    }

    public AddContactPage digitarContato(String contato){
        navegador.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contato);
        return this;
    }

    public Mepage clicarSalvar(){
        navegador.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();
        return new Mepage(navegador);
    }

    public Mepage adicionarContato(String tipo, String contato){
        escolherTipoDeContato(tipo);
        digitarContato(contato);
        clicarSalvar();
        return new Mepage(navegador);
    }
}
