package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtils;

public class ProductInfoPage {
	WebDriver driver;
	ElementUtils elementUtils;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails li img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li" );
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By successsMessage = By.cssSelector("div.alert.alert-success.alert-dismissible");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);

	}

	public String getProductHeader() {
		return elementUtils.doGetText(productHeader);
	}

	public int getProductImagesCount() {
		return elementUtils.getElements(productImages).size();
	}

	public Map<String, String> getProductInfo() {
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("name", getProductHeader());
		List<WebElement> metaList = elementUtils.getElements(productMetaData);
		System.out.println("META LIST---->>> " +metaList.size());
		for (WebElement e : metaList) {
			String[] meta = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();

			infoMap.put(metaKey, metaValue);

		}
		List<WebElement> priceList = elementUtils.getElements(productPriceData);
		System.out.println("PRICELIST " + priceList.size());
		String price = priceList.get(0).getText().trim();
		String exPrice = priceList.get(1).getText().trim();
		infoMap.put("price", price);
		infoMap.put("exprice", exPrice);

		return infoMap;
	}

	public void selectquantity(String qnty) {
		elementUtils.doSendKeys(quantity, qnty);
	}

	public void doClick() {
		elementUtils.doClick(addToCartBtn);

	}

	public String successMessage() {
		return elementUtils.doGetText(successsMessage);
	}

}
