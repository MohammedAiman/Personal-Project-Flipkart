package Flipkart;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import test.java.chs_datavalidation.DataValidation_locators;
import test.java.chs_datavalidation.ExcelDataConfig;

public class eCommerce {
	public WebDriver driver;
	ExcelDataConfig exc;
	Locators lc;
	JavascriptExecutor js;


	@Test(priority = 1)
	public void OpenFlipkart()	throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\maiman\\New Chromedriver\\chromedriver.exe");

		//To avoid I/O exception
		try {
			exc = new ExcelDataConfig("C:\\Users\\maiman\\Downloads\\eCommerce.xlsx");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		//open browser and maximize it
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Initialize locators class
		lc = PageFactory.initElements(driver, Locators.class);
		
		//Open flipkart
		driver.get("https://flipkart.com");
		
		Thread.sleep(5000);
		
		//If login pop up appears, close it. Else ignore
		try {
            if (lc.CloseButton.isDisplayed()) {
                lc.CloseButton.click();
            }
        } catch (Exception e) {
            // Element not found, move forward
            System.out.println("No pop-up found");
        }
		
		//Enter Hindi Books and search
		lc.SearchBox.sendKeys("Hindi Books");
		lc.SearchBox.submit();
		
		Thread.sleep(3000);
		
		//Click on 1st result
		lc.FirstBook.click();
		
		// Get the current window handle
        String originalWindow = driver.getWindowHandle();

        // Wait for the new tab to open and get all window handles
        Set<String> allWindows = driver.getWindowHandles();
        while (allWindows.size() == 1) {
            allWindows = driver.getWindowHandles();
        }

        // Switch to the new tab of a book
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        //Extract the Name, Price and Rating of a book
        String BookName = lc.FirstBookName.getText();
        String BookPrice = lc.FirstBookPrice.getText();
        WebElement Rating = lc.FirstBookRating.get(0);
        String BookRating = Rating.getText();
 
        //Print the name, price and rating       
        System.out.println("Book name: "+BookName);
        System.out.println("Book Price: "+BookPrice);
        System.out.println("Book Rating: "+BookRating);
        
        //setting data in excel
       exc.setdata(0, 1, 1, BookName);
       exc.setdata(0, 2, 1, BookPrice);
       exc.setdata(0, 3, 1, BookRating);
		
       //Close all tabs
        driver.quit();
	}
	

}
