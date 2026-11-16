package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MtsMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //Поля главного блока онлайн пополнение без комиссии
    private final By cookieButton = By.xpath("//*[@id='cookie-agree']");
    private final By blockTitle = By.xpath("//div[@class='pay__wrapper']/h2");
    private final By paymentLogos = By.xpath("//div[@class='pay__partners']//img");
    private final By ServiceLink = By.xpath("//*[text()='Подробнее о сервисе']");
    private final By serviceDropdown = By.xpath("//button[@class='select__header']");
    private final By paymentIframe = By.xpath("//iframe[contains(@class, 'payment')]");

    //Поля блока "Услуги связи"
    private final By phoneInput = By.xpath("//*[@id='connection-phone']");
    private final By sumInput = By.xpath("//*[@id='connection-sum']");
    private final By emailInput = By.xpath("//*[@id='connection-email']");
    private final By bankCardContinueButton = By.xpath("//form[@id='pay-connection']//button[@data-method='card']");

    // Поля блока "Домашний интернет"
    private final By internetPhoneInput = By.xpath("//*[@id='internet-phone']");
    private final By internetSumInput = By.xpath("//input[@id='internet-sum']");
    private final By internetEmailInput = By.xpath("//*[@id='internet-email']");


    // Поля блока "Рассрочка"
    private final By instalmentAccountNumberInput = By.xpath("//*[@id='score-instalment']");
    private final By instalmentSumInput = By.xpath("//*[@id='instalment-sum']");
    private final By instalmentEmailInput = By.xpath("//*[@id='instalment-email']");

    // Поля блока "Задолженность"
    private final By debtAccountNumberInput = By.xpath("//*[@id='score-arrears']");
    private final By debtSumInput = By.xpath("//*[@id='arrears-sum']");
    private final By debtEmailInput = By.xpath("//*[@id='arrears-email']");

    public MtsMainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Закрыть окно уведомления о cookie")
    public void cookieButtonTest() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            button.click();
        } catch (TimeoutException ignored) {
        }

    }

    @Step("Проверить заголовок блока «Онлайн пополнение без комиссии»")
    public String getBlockTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle)).getText();
    }
    @Step("Проверить список логотипов платёжных систем")
    public List<WebElement> getPaymentLogos() {
        return driver.findElements(paymentLogos);
    }

    @Step("Проверка ссылки «Подробнее о сервисе» и дождаться перехода на страницу")
    public void clickServiceLink() {
        driver.findElement(ServiceLink).click();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
    }

    @Step("Выбор в  варианта оплаты: «{serviceName}»")
    public void selectService(String serviceName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(serviceDropdown));
        if (!dropdown.getText().contains(serviceName)) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", dropdown
            );
            dropdown.click();
            By optionLocator = By.xpath("//ul[@class='select__list']//p[text()='" + serviceName + "']");
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }
    }

    @Step("Плейсхолдеры полей для варианта «Услуги связи»")
    public String[] getPlaceholders() {
        return new String[]{
                driver.findElement(phoneInput).getAttribute("placeholder"),
                driver.findElement(sumInput).getAttribute("placeholder"),
                driver.findElement(emailInput).getAttribute("placeholder"),
        };
    }

    @Step("Плейсхолдеры полей для варианта «Домашний интернет»")
    public String[] getInternetPlaceholders() {
        return new String[]{
                driver.findElement(internetPhoneInput).getAttribute("placeholder"),
                driver.findElement(internetSumInput).getAttribute("placeholder"),
                driver.findElement(internetEmailInput).getAttribute("placeholder"),
        };
    }

    @Step("Плейсхолдеры полей для варианта «Рассрочка»")
    public String[] getInstalmentPlaceholders() {
        return new String[]{
                driver.findElement(instalmentAccountNumberInput).getAttribute("placeholder"),
                driver.findElement(instalmentSumInput).getAttribute("placeholder"),
                driver.findElement(instalmentEmailInput).getAttribute("placeholder"),
        };
    }

    @Step("Плейсхолдеры полей для варианта «Задолженность»")
    public String[] getDebtPlaceholders() {
        return new String[]{
                driver.findElement(debtAccountNumberInput).getAttribute("placeholder"),
                driver.findElement(debtSumInput).getAttribute("placeholder"),
                driver.findElement(debtEmailInput).getAttribute("placeholder"),
        };
    }

    @Step("Заполнить форму «Услуги связи» (телефон: {phone}, сумма: {sum}, email: {email}) и нажать «Продолжить»")
    public PaymentModalPage fillAndSubmitConnectionForm(String phone, String sum, String email) {
        selectService("Услуги связи");
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(sumInput).sendKeys(sum);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(bankCardContinueButton).click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentIframe));
        driver.switchTo().frame(iframe);
        return new PaymentModalPage(driver, wait);
    }
}
