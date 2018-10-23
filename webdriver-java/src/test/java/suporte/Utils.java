package suporte;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static final String USERNAME = "brunooliveiradas1";
    public static final String AUTOMATE_KEY = "5tsuDTzzuoyYuiqryEW5";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome() {

        // Configuracao do webdriver e setar qual driver abrir
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
         WebDriver navegador = new ChromeDriver();
        //Definir um timeout de forma implicita
        navegador.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // Tela maximizada
        // Windowns navegador.manage().window().maximize();
        navegador.manage().window().setSize(new Dimension(1280, 800));

        // Navegando para a pagina do globo.com
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;
    }

    public static WebDriver creatBrowserStack(){

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "69.0");
        caps.setCapability("os", "OS X");
        caps.setCapability("os_version", "High Sierra");
        caps.setCapability("resolution", "1280x800");

        WebDriver navegador = null;

        try {
             navegador = new RemoteWebDriver(new URL(URL), caps);
             navegador.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
             navegador.get("http://www.juliodelima.com.br/taskit/");
        } catch (MalformedURLException e) {
            System.out.println("Houveram problemas na url: " + e.getMessage());
        }

        return navegador;

    }
}
