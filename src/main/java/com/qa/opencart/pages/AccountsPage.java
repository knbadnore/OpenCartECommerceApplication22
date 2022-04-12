package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.Constants;
import com.qa.opencart.Utils.ElementUtils;

public class AccountsPage {
	private ElementUtils elementUtil;
	private WebDriver driver;

	private By accSectionHeader = By.cssSelector("div#content h2");
	private By logo = By.cssSelector("div#logo a");
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.cssSelector("div#search >.form-control");
	private By searchBtn = By.xpath("//span[@class='input-group-btn']//button[@type='button']");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtils(driver);

	}

	public String getAccountPageTitle() {

		return elementUtil.waitForTitle(10, Constants.ACCOUNT_PAGE_TITLE);
	}

	public String getAccountPageHeader() {
		return elementUtil.doGetText(logo);
	}

	public List<String> getAccountSectionList() {
		List<String> accountSectionValueList = new ArrayList<String>();
		List<WebElement> weList = elementUtil.waitForVisibilityOfElements(accSectionHeader, 5);
		for (WebElement l : weList) {
			accountSectionValueList.add(l.getText());

		}
		Collections.sort(accountSectionValueList);
		return accountSectionValueList;
	}

	public Boolean logoutExists() {
		return elementUtil.doIsDisplayed(logoutLink);
	}

	public SearchResultPage doSearch(String product) {
		elementUtil.doSendKeys(searchField, product);
		elementUtil.doClick(searchBtn);
		return new SearchResultPage(driver);
	}
}
