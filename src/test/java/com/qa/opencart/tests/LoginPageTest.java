package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Utils.Constants;
import com.qa.opencart.basetest.BaseTest;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getTitle();
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Test

	public void loginPageURLTest() {
		String actualURL = loginPage.getURL();
		Assert.assertTrue(actualURL.contains(Constants.LOGIN_PAGE_URL));

	}

	@Test
	public void forgotPasswordLink() {
		loginPage.forgotPasswordLink();
		Assert.assertTrue(loginPage.forgotPasswordLink());
	}

	@Test
	public void loginTest() {
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] loginNegativeData() {
		return new Object[][] { { "test@gmail.com", "123" }, { " ", " " } };
	}

	@Test(dataProvider = "loginNegativeData")
	public void loginWithWrongData(String un, String pwd) {
		loginPage.loginWithWrongData(un, pwd);
	}

}
