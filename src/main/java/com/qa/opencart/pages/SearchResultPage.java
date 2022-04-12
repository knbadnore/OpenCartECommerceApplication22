package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtils;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtils elementUtils;

	private By searchItemResultList = By.cssSelector("div.product-thumb");
	private By resultItem = By.cssSelector("div.product-thumb  h4 a");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}

	public int resultCount() {
		return elementUtils.getElements(searchItemResultList).size();
	}

	public ProductInfoPage selectResultFromSearch(String productName) {
		List<WebElement> resultItemList = elementUtils.getElements(resultItem);
		
		for (WebElement we : resultItemList) {
			System.out.println(we.getText());
			if (we.getText().equals(productName)) {

				we.click();
				break;
			}

		}
		return new ProductInfoPage(driver);

	}
}
