package com.nimap.fieldforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends BasePage {

    private final By dashboardText =
            By.xpath("//h4[normalize-space()=\"Today's Stats\"]");

    private final By manageButton =
            By.xpath("//button[normalize-space()='Manage']");

    private final By newCustomerOption =
            By.xpath("//span[normalize-space()='New Customer']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isDisplayed(dashboardText);
    }

    public void goToMyCustomers() {

        // The application's actual My Customer route is /customers.
        driver.get("https://test.fieldforceconnect.com/customers");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.urlContains("/customers")
        );
    }

    public void clickManage() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.elementToBeClickable(manageButton)
        );

        driver.findElement(manageButton).click();
    }

    public void clickNewCustomer() {

        clickManage();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.elementToBeClickable(newCustomerOption)
        );

        driver.findElement(newCustomerOption).click();
    }
}