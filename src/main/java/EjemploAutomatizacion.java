import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class EjemploAutomatizacion {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Configurar el WebDriver. En este caso, para Chrome.
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromeDriver/chromedriver.exe"); // Reemplaza con la ruta donde está tu chromedriver
        driver = new ChromeDriver();
    }

    @Test
    public void testAbrirGoogle() {
        // Navegar a una página web
        driver.get("https://www.google.com");

        // Verificar el título de la página
        String tituloEsperado = "Google";
        assertEquals(tituloEsperado, driver.getTitle());
    }

    @Test
    public void testBuscarEnGoogle() {
        // Navegar a la página de Google
        driver.get("https://www.google.com");

        // Ingresar texto en el campo de búsqueda
        String textoBusqueda = "Selenium WebDriver";
        driver.findElement(By.name("q")).sendKeys(textoBusqueda + Keys.ENTER);

        // Verificar que el primer resultado contenga el texto de la búsqueda
        assertTrue(driver.findElement(By.cssSelector("#search h3")).getText().contains("WebDriver"));
    }

    @Test
    public void testClicEnPrimerResultado() {
        // Navegar a la página de Google
        driver.get("https://www.google.com");

        // Ingresar texto en el campo de búsqueda
        String textoBusqueda = "Selenium WebDriver";
        WebElement campoBusqueda = driver.findElement(By.name("q"));
        campoBusqueda.sendKeys(textoBusqueda + Keys.ENTER);

        // Esperar un momento para que se carguen los resultados (opcional)
        try {
            Thread.sleep(2000); // Pausa de 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hacer clic en el primer resultado de búsqueda
        WebElement primerResultado = driver.findElement(By.cssSelector(".g h3"));
        primerResultado.click();

        // Verificar que la URL del primer resultado contiene "selenium"
        assertTrue(driver.getCurrentUrl().contains("selenium"));
    }

    @AfterEach
    public void tearDown() {
        // Cerrar el navegador al finalizar cada prueba
        if (driver != null) {
            driver.quit();
        }
    }
}
