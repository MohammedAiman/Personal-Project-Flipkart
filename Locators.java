package Flipkart;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Locators {
	
	WebDriver driver;

	public Locators(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "//span[contains(text(),'✕')]")
	WebElement CloseButton;
	
	@FindBy(xpath = "//header/div[1]/div[2]/form[1]/div[1]/div[1]/input[1]")
	WebElement SearchBox;

	@FindBy(xpath = "//body/div[@id='container']/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/a[1]/div[1]/div[1]/div[1]/img[1]")
	WebElement FirstBook;
	
	
	@FindBy(xpath = "//*[@class='VU-ZEz']")
	WebElement FirstBookName;
	
	
	@FindBy(xpath = "//*[@class='Nx9bqj CxhGGd']")
	WebElement FirstBookPrice;
	
	@FindBy(xpath = "//*[@class='XQDdHH']")
	List<WebElement> FirstBookRating;
	
	
}
