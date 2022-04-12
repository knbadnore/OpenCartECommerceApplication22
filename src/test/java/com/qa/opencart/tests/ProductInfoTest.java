package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.basetest.BaseTest;

public class ProductInfoTest extends BaseTest {
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void productInfoSetup() {
		accountPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] searchProducts() {
		return new Object[][] { { "iMac" }, { "Macbook" },{"Iphone"}};
	}

	@Test(dataProvider = "searchProducts")
	public void searchTest(String productName) {
		searchResultPage = accountPage.doSearch(productName);
		Assert.assertTrue(searchResultPage.resultCount() >0);

	}

	@Test(enabled = false)
	public void productInfoTest() {
		searchResultPage = accountPage.doSearch("iMac");
		productInfoPage = searchResultPage.selectResultFromSearch("iMac");
		Map<String, String> mapInfo = productInfoPage.getProductInfo();
		mapInfo.forEach((k, v) -> System.out.println(k + " : " + v));

		softAssert.assertEquals(mapInfo.get("name"), "iMac");
		softAssert.assertEquals(mapInfo.get("Brand"), "Apple");
		softAssert.assertEquals(mapInfo.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(mapInfo.get("price"), "$100.00");
		softAssert.assertAll();

	}

}
