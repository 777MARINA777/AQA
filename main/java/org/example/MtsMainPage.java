package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MtsMainPage {
    private final WebDriver driver;
    private final WebDriverWait  wait;

    //Поля главного блока онлайн пополнение без комиссии
    private final By cookieButton = By.xpath("//*[@id='cookie-agree']");
    private final By blockTitle = By.xpath("//div[@class='pay__wrapper']");
    private final By paymentLogos = By.xpath("//div[@class='pay__partners']//img");
    private final By ServiceLink = By.xpath("//*[text()='Подробнее о сервисе']");
    private final By serviceDropdown = By.xpath("//button[@class='select__header']");
    private final By paymentIframe = By.xpath("//iframe[contains(@class, 'payment')]");

    //Поля блока "Услуги связи"
    private final By phoneInput = By.xpath("//*[@id='connection-phone']");
    private final By sumInput = By.xpath("//*[@id='connection-sum']");
    private final By emailInput = By.xpath("//*[@id='connection-email']");
    private final By bankCardContinueButton = By.xpath("//form[@id='pay-connection']");

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
    private final By debtSumInput = By.xpath("//*[@id='score-arrears']");
    private final By debtEmailInput = By.xpath("//*[@id='arrears-email']");

    public MtsMainPage(WebDriver driver, WebDriverWait  wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void acceptCookie() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            button.click();
        } catch (TimeoutException ignored) {
        }

    }

    public String getBlockTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle)).getText();
    }

    public List<WebElement> getPaymentLogos() {
        return driver.findElements(paymentLogos);
    }

    public void clickMoreAboutService() {
        driver.findElement(ServiceLink).click();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
    }

    public void selectService(String serviceName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(serviceDropdown));
        if (!dropdown.getText().contains(serviceName)) {
            dropdown.click();
            By optionLocator = By.xpath("//ul[@class='select__list']//p[text()='" + serviceName + "']");
            wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
        }
    }

    public String[] getPlaceholders() {
        return new String[]{
                driver.findElement(phoneInput).getAttribute("placeholder"),
                driver.findElement(sumInput).getAttribute("placeholder"),
                driver.findElement(emailInput).getAttribute("placeholder"),
                driver.findElement(bankCardContinueButton).getAttribute("placeholder"),
        };
    }

    public String[] getInternetPlaceholders() {
        return new String[]{
                driver.findElement(internetPhoneInput).getAttribute("placeholder"),
        driver.findElement(internetSumInput).getAttribute("placeholder"),
        driver.findElement(internetEmailInput).getAttribute("placeholder"),
    };
    }

    public String[] getInstalmentPlaceholders() {
        return new String[]{
                driver.findElement(instalmentAccountNumberInput).getAttribute("placeholder"),
        driver.findElement(internetEmailInput).getAttribute("placeholder"),
        driver.findElement(instalmentSumInput).getAttribute("placeholder"),
        };
    }

    public String[] getdebtPlaceholders() {
        return new String[]{
                driver.findElement(debtAccountNumberInput).getAttribute("placeholder"),
        driver.findElement(debtEmailInput).getAttribute("placeholder"),
        driver.findElement(debtSumInput).getAttribute("placeholder"),
        };
    }

    public PaymentModalPage fillAndSubmitConnectionForm(String phone, String sum, String email) {
        selectService("Услуги связи");
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(sumInput).sendKeys(sum);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(bankCardContinueButton).click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentIframe));
        driver.switchTo().frame(iframe);
        return new PaymentModalPage(driver,wait);
    }
}
