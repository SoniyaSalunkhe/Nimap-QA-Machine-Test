package com.nimap.fieldforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCustomerPage extends BasePage {

    private final By customerNameInput =
            By.name("LeadName");

    private final By emailInput =
            By.name("Email");

    private final By mobileInput =
            By.name("MobileNo");

    private final By saveButton =
            By.xpath("//span[normalize-space()='Save']/parent::button");

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    public void enterCustomerName(String customerName) {
        type(customerNameInput, customerName);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterMobile(String mobile) {
        type(mobileInput, mobile);
    }

    public void clickSave() {
        click(saveButton);
    }
}