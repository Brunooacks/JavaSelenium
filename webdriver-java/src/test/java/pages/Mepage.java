package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Mepage extends BasePage{


    public Mepage(WebDriver navegador) {
        super(navegador);
    }

    public Mepage clicarAbaMoreDataAboutYou(){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
        return this;
    }

    public AddContactPage clicarNoBotaoAddMoreDataAboutYou(){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        return new AddContactPage(navegador);

    }
}
