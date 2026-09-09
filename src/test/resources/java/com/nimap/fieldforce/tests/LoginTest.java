package com.nimap.fieldforce.tests;

import com.nimap.fieldforce.pages.DashboardPage;
import com.nimap.fieldforce.pages.LoginPage;
import com.nimap.fieldforce.utils.CsvDataProvider;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return CsvDataProvider.readCsv(
                "src/test/resources/testdata/login_data.csv"
        );
    }

    @Test(dataProvider = "loginData")
    public void testLogin(
            String emailOrMobile,
            String password,
            String expectedResult) {

        LoginPage loginPage = new LoginPage(driver).open();

        loginPage.login(emailOrMobile, password);

        if ("SUCCESS".equalsIgnoreCase(expectedResult)) {

            DashboardPage dashboard = new DashboardPage(driver);

            Assert.assertTrue(
                    dashboard.isLoaded(),
                    "Expected successful login to reach Dashboard for: "
                            + emailOrMobile
            );

        } else {

            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "Expected login error for: "
                            + emailOrMobile
            );
        }
    }
}