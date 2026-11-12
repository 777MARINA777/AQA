import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class MtsTests {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.get("https://www.mts.by/");
    }

    @Test
    @DisplayName("Закрытие окна cookie")
    @Order(1)
    public void cookieTest() {
        try {
            WebElement cookieButton = wait.until(
                    ExpectedConditions.elementToBeClickable
                            (By.xpath("//*[@id='cookie-agree']")));
            cookieButton.click();
        } catch (TimeoutException ignored) {
        }
    }

    @Test
    @DisplayName("Проверка названия блока")
    @Order(2)
    void testBlockTitle() {
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pay__wrapper']/h2")));
    }

    @Test
    @DisplayName("Проверка наличия логотипов платёжных систем")
    @Order(3)
    void testPaymentLogos() {
        List<WebElement> logos = driver.findElements(By.xpath("//div[@class='pay__partners']//img"));
        assertEquals(5, logos.size(), "Всего: 5 логотипов");
        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(), "Не отображается логотип: " + logo.getAttribute("alt"));
        }
    }

    @Test
    @DisplayName("Проверка ссылки: Подробнее о сервисе")
    @Order(4)
    void testLink() {
        WebElement link = driver.findElement(By.xpath("//*[text()= 'Подробнее о сервисе']"));
        link.click();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));

        String currentUrl = driver.getCurrentUrl();
        assertTrue(
                currentUrl.contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Открылась неверная страница: " + currentUrl);
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pay__wrapper']/h2")));
    }

    @Test
    @DisplayName("Заполнение полей")
    @Order(5)
    void testPaymentForm() {
        WebElement serviceSelector = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='select__now']")));
        if (!serviceSelector.getText().contains("Услуги связи")) {
            serviceSelector.click();
            driver.findElement(By.xpath("//p[text()='Услуги связи']")).click();
        }
        WebElement phoneInput = driver.findElement(By.xpath("//*[@id='connection-phone']"));
        phoneInput.sendKeys("297777777");
        WebElement sumInput = driver.findElement(By.xpath("//*[@id='connection-sum']"));
        sumInput.sendKeys("5");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id='connection-email']"));
        emailInput.sendKeys("marina_bratikova@mail.ru");
        WebElement buttoncontinue = driver.findElement(By.xpath("//form[@id='pay-connection']//button[text()='Продолжить']"));
        buttoncontinue.click();
        WebElement paymentIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//iframe[@class='payment-widget-iframe']")
        ));
        assertTrue(paymentIframe.isDisplayed(), "Платёжное окно после нажатия «Продолжить» не появилось");
    }

    @AfterAll
    @Order(6)
    static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
