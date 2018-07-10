package Mentoring;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Primary {
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();

	    driver =new ChromeDriver();
		//fullscreen
		driver.manage().window().maximize();
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Step1
		String url="https://dice.com";
		driver.get(url);

	}
	
	@BeforeMethod
	public void setUp2() {
		String actualTitle = driver.getTitle();
        String expectedTitle = "Job Search for Technology Professionals | Dice.com";

        Assert.assertEquals(actualTitle, expectedTitle); 
        System.out.println("Loaded page Successfully");
	}
	
	@Test
	public void test1() {
		List<String> ListFromUtilities=Utilities.loadTitleToArrayList();
		for(int i=0; i<ListFromUtilities.size();i++) {
			String keyword=ListFromUtilities.get(i);
		driver.findElement(By.id("search-field-keyword")).clear();
        // clears the text box
        driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
        // Finds and types java developer in the keyword box.
        // Where and What

        String location = "22315";
        driver.findElement(By.id("search-field-location")).clear();
        driver.findElement(By.id("search-field-location")).sendKeys(location);

        driver.findElement(By.id("findTechJobs")).click();

        String count = driver.findElement(By.id("posiCountId")).getText();
        ListFromUtilities.set(i, keyword+"-"+Utilities.jobCount(count));
       
        
		}
	}

}
