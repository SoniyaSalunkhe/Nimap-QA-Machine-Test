package com.nimap.fieldforce.tests;

import com.nimap.fieldforce.pages.AddCustomerPage;
import com.nimap.fieldforce.pages.DashboardPage;
import com.nimap.fieldforce.pages.LoginPage;
import com.nimap.fieldforce.utils.CsvDataProvider;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends BaseTest {

    private static final String VALID_EMAIL =
            "soniyasalunkhe2003@gmail.com";

    private static final String VALID_PASSWORD =
            "Soniya@06";

    @DataProvider(name = "customerData")
    public Object[][] customerData() {

        Object[][] data = CsvDataProvider.readCsv(
                "src/test/resources/testdata/customer_data.csv"
        );

        System.out.println("Total customer test data rows: " + data.length);

        for (Object[] row : data) {
            System.out.println(
                    "Customer: " + row[0]
                    + " | Email: " + row[1]
                    + " | Mobile: " + row[2]
            );
        }

        return data;
    }

    @Test(dataProvider = "customerData")
    public void testAddCustomer(
            String customerName,
            String email,
            String mobile) {

        // 1. Open Login page
        LoginPage loginPage =
                new LoginPage(driver).open();

        // 2. Login
        loginPage.login(
                VALID_EMAIL,
                VALID_PASSWORD
        );

        // 3. Verify Dashboard
        DashboardPage dashboard =
                new DashboardPage(driver);

        Assert.assertTrue(
                dashboard.isLoaded(),
                "Login failed - Dashboard was not loaded"
        );

        System.out.println(
                "Login successful - Dashboard loaded"
        );

        // 4. My Customer
        dashboard.goToMyCustomers();

        System.out.println(
                "My Customer page opened"
        );

        // 5. Manage → New Customer
        dashboard.clickNewCustomer();

        System.out.println(
                "New Customer page opened"
        );

        // 6. Create Add Customer page object
        AddCustomerPage addCustomerPage =
                new AddCustomerPage(driver);

        // 7. Enter customer details
        addCustomerPage.enterCustomerName(customerName);

        addCustomerPage.enterEmail(email);

        addCustomerPage.enterMobile(mobile);

        // 8. Save
        addCustomerPage.clickSave();

        System.out.println(
                "Customer form submitted for: " + customerName
        );
    }
}