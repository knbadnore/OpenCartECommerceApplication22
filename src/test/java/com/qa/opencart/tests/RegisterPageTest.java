package com.qa.opencart.tests;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Utils.Constants;
import com.qa.opencart.Utils.ElementUtils;
import com.qa.opencart.Utils.ExcelUtils;
import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.pages.LoginPage;

public class RegisterPageTest extends BaseTest {
	@BeforeClass
	public void registerPageSetup() {
		registerPage = loginPage.navigateToRegister();
	}

	@DataProvider
	public Object[][] getTestDataRegistration() {
		Object[][] regData = ExcelUtils.getTestData("register");
		return regData;

	}

	public static String generateEmailId() {
		Random random = new Random();
		String email = "testautomation" + random.nextInt(100) + "@gmail.com";
		return email;
	}

	@Test(dataProvider = "getTestDataRegistration")
	public void doRegister(String firstName, String lastName, String telephone, String password, String subscribe) {

		Assert.assertTrue(registerPage.createAccount(firstName, lastName, generateEmailId(), telephone, password,
				password, subscribe));
	}
}
