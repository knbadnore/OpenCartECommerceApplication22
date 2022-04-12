package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.Constants;
import com.qa.opencart.Utils.ElementUtils;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtils elementUtils;
	private By firstName = By.cssSelector("input[name='firstname']");
	private By lastName = By.cssSelector("input#input-lastname:nth-of-type(1)");
	private By email = By.cssSelector("input#input-email");
	private By telephone = By.cssSelector("input#input-telephone");
	private By password = By.cssSelector("input#input-password");
	private By confirmPass = By.cssSelector("input#input-confirm");
	private By suscribeYes = By.xpath("(//label[@class=\"radio-inline\" ]//input[@type='radio'])[1]");
	private By subscribeNo = By.xpath("(//label[@class=\"radio-inline\" ]//input[@type='radio'])[2]");
	private By iAgreeCheckbox = By.xpath("//input[@type=\"checkbox\" ] [@name=\"agree\"]");
	private By continueBtn = By.xpath("//input[@type='submit']");
	private By successMessage = By.cssSelector("#content h1");
	private By registerLink = By.xpath("//a[@class='list-group-item' and text()='Register']");
	private By logoutLink = By.xpath(
			"//a[contains(@href,\"https://demo.opencart.com/index.php?route=account/logout\")][@class='list-group-item']");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}

	public boolean createAccount(String firstName, String lastName,String email, String telephone, String password,
			String confirmPassword, String subscribe) {
		elementUtils.doSendKeys(this.firstName, firstName);
		elementUtils.doSendKeys(this.lastName, lastName);
		elementUtils.doSendKeys(this.email, email);
		elementUtils.doSendKeys(this.telephone, telephone);
		
		elementUtils.doSendKeys(this.password, password);
		elementUtils.doSendKeys(this.confirmPass, confirmPassword);

		if (subscribe.equalsIgnoreCase("yes"))
			elementUtils.doClick(suscribeYes);
		else
			elementUtils.doClick(subscribeNo);

		elementUtils.doClick(iAgreeCheckbox);
		elementUtils.doClick(continueBtn);

		String successMsg = elementUtils.waitForElementVisible(successMessage, 5).getText();
		if (successMsg.equals(Constants.REGISTER_SUCCESS_MSG)) {
			elementUtils.doClick(logoutLink);
			elementUtils.doClick(registerLink);
			return true;
		} else
			return false;
	}
}
