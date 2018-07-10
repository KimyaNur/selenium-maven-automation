package webelement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class amazonWarmUp {

	WebDriver driver;
	List<WebElement> price;
	List<WebElement> description;

	@BeforeClass /// runs once for all test
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=wooden+spoon");
	}

	@Test
	public void WebElementsPrice() {

		price = driver.findElements(By.xpath("//span[@class='sx-price sx-price-large']"));
		for (WebElement each : price) {
			if (!each.getText().isEmpty())
				System.out.println(each.getText());
		}

	}

	@Test
	public void WebElementsDescription() {

		// List<WebElement> description =driver.findElements(By.xpath("//h2[@class='a-size-base s-in lines-access-title a-text-normal']"));
		description = driver.findElements(By.tagName("h2"));
		for (WebElement each : description) {
			if (!each.getText().isEmpty())
				System.out.println(each.getText());
		}
		System.out.println();
	}
	@Test
	public void writePrDsc() {
		for(int i = 0; i < description.size(); i++) {
			System.out.println(description.get(i).getText());
			System.out.println(price.get(i).getText());
			System.out.println("---------------");
		}
		
	}

	@Test
	public void writePriceDescription() throws InterruptedException {
      List<WebElement> wholeItems = driver.findElements(By.xpath("//div[@class='s-item-container']"));
		
		System.out.println("wholeItems.size():" + wholeItems.size());
		
		
		for (int i = 0; i < wholeItems.size(); i++) {
			if(wholeItems.get(i).getText().isEmpty()) continue;
			
			String desXpath = "(//div[@class='s-item-container'])[" + (i+1) + "]//h2";
			String priceXpath = "(//div[@class='s-item-container'])[" + (i+1) + "]//span[@class='sx-price sx-price-large']";
			
			System.out.println(driver.findElement(By.xpath(desXpath)).getText());
			System.out.println(driver.findElement(By.xpath(priceXpath)).getText());
			System.out.println("------------");
			
		}
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}

}
