package Princyacademy.AbstractComponents;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Princyacademy.pageObjects.CartPage;
import Princyacademy.pageObjects.OrderPage;

public class AbstractComponents {
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;

	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
			}

	public void waitforElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		

	}
	
	public void waitforWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		

	}
	
	public void waitforElementToDisappear(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	//	wait.until(ExpectedConditions.invisibilityOf(element));
		

	}
	
	
	// Go to Cart
	//driver.findElement(By.xpath("")).click();
	
	public CartPage goToCart() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;

		
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;

		
	}

}
