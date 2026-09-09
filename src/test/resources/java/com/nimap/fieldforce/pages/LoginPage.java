package com.nimap.fieldforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private static final String URL =
            "https://test.fieldforceconnect.com/auth/login";

    private final By emailOrMobileInput =
            By.xpath("//input[@placeholder='Enter Email or Mobile Number']");

    private final By passwordInput =
            By.xpath("//input[@placeholder='Password']");

    private final By signInButton =
            By.xpath("//button[normalize-space()='Sign In']");

    private final By errorMessage =
            By.xpath("//*[contains(@class,'error') or contains(@class,'toast')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(URL);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                emailOrMobileInput));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                emailOrMobileInput));

        return this;
    }

    public void enterEmailOrMobile(String value) {
        type(emailOrMobileInput, value);
    }

    public void enterPassword(String value) {
        type(passwordInput, value);
    }

    public boolean isSignInEnabled() {
        return driver.findElement(signInButton).isEnabled();
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.presenceOfElementLocated(signInButton));

        if (isSignInEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(signInButton));
            driver.findElement(signInButton).click();
        }
    }

    public void login(String emailOrMobile, String password) {
        enterEmailOrMobile(emailOrMobile);
        enterPassword(password);
        clickSignIn();
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}