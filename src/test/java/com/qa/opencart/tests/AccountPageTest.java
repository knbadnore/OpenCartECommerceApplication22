package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.apache.hc.core5.util.Asserts;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.Utils.Constants;
import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.pages.AccountsPage;

public class AccountPageTest extends BaseTest {

	@BeforeTest
	public void accountPageSetUp() {
		accountPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void getTitle() {
		Assert.assertEquals(accountPage.getAccountPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void getAccountLogo() {
		String logo = accountPage.getAccountPageHeader();
		Assert.assertEquals(logo, Constants.ACCOUNT_PAGE_HEADER);

	}

	@Test
	public void accountSectionsListTest() {
		List<String> list = accountPage.getAccountSectionList();
		list.stream().forEach(e -> System.out.println(e));
		Collections.sort(Constants.EXPECTED_ACCOUNT_SECTION_LIST);
		Assert.assertEquals(list, Constants.EXPECTED_ACCOUNT_SECTION_LIST);
	}

	@Test
	public void logoutDisplayedTest() {
		Assert.assertTrue(accountPage.logoutExists());
	}

}