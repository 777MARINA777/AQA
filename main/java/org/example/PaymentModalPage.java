package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PaymentModalPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By paymentAmount = By.xpath("//div[contains(@class, 'pay-description__cost')]/span");
    private final By paymentDescription = By.xpath("//div[contains(@class, 'pay-description__text')]/span");
    private final By payButton = By.xpath("//button[contains(@class, 'colored')]");

    private final By numberCard = By.xpath("//label[contains(text(), 'Номер карты')]");
    private final By cardValidityPeriod = By.xpath("//label[contains(text(), 'Срок действия')]");
    private final By cvcCard = By.xpath("//label[contains(text(), 'CVC')]");
    private final By firstnameLastnameCard = By.xpath("//div[@class='content ng-tns-c2312288139-3']");
    private final By iconsPaymentSystems = By.xpath("//img[contains(@src, 'payment-icons/card-types')]");

    public PaymentModalPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getPaymentAmountText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentAmount)).getText().trim();
    }

    public String getPaymentDescriptionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentDescription)).getText().trim();
    }

    public String getPayButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(payButton)).getText().trim();
    }

    public String getNumberCardText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(numberCard)).getText().trim();
    }

    public String getCardValidityPeriodText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardValidityPeriod)).getText().trim();
    }

    public String getCvcCardText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cvcCard)).getText().trim();
    }

    public String getFirstnameLastnameCardText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameLastnameCard)).getText().trim();
    }

    public boolean iconsPaymentSystems() {
        List<WebElement> icons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(iconsPaymentSystems));
        return !icons.isEmpty() && icons.stream().anyMatch(WebElement::isDisplayed);

    }

    public void closeModalAndReturnToMain() {
        driver.switchTo().defaultContent();
    }
}
