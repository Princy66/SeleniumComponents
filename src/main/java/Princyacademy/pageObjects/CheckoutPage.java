package Princyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Princyacademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	@FindBy(css = ".hero-primary")
	WebElement message;
	By results = By.cssSelector(".ta-results");


	
	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitforElementToAppear(results);
		selectCountry.click();
		

	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
