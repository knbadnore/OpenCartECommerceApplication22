package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.Constants;
import com.qa.opencart.Utils.ElementUtils;

public class LoginPage {

	private ElementUtils elementUtil;
	private WebDriver driver;

//1. Private By locators
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgetPwdLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By register = By.linkText("Register");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

//2. public Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtils(driver);

	}

// 3. Public page actions
	public String getTitle() {
		return elementUtil.waitForTitle(5, Constants.LOGIN_PAGE_TITLE);
	}

	public String getURL() {
		return elementUtil.getPageURL();
	}

	public boolean forgotPasswordLink() {
		return elementUtil.doIsDisplayed(forgetPwdLink);
	}

	public AccountsPage login(String un, String pwd) {
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);

		return new AccountsPage(driver);

	}

	public boolean loginWithWrongData(String un, String pwd) {
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);

		return elementUtil.doIsDisplayed(loginErrorMsg);

	}

	public RegisterPage navigateToRegister() {
		elementUtil.doClick(register);
		return new RegisterPage(driver);
	}

}
