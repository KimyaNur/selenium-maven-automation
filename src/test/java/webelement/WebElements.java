package webelement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {

	WebDriver driver;

	@BeforeClass /// runs once for all test
	public void setUp() {
		System.out.println("Setting up WebDriver in beforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Ignore
	@Test
	public void WebElement() {

		driver.get(
				"https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");
		WebElement email = driver.findElement(By.name("Email"));

		String value = email.getAttribute("value");
		String maxLength = email.getAttribute("maxLength");
		String type = email.getAttribute("type");
		String tag = email.getTagName();
		boolean b = email.isEnabled();

		System.out.println("value: " + value + "\n" + "maxLength: " + maxLength + "\n" + "type: " + type + "\n"
				+ "tag: " + tag + "isEnable " + b);

		assertEquals(value, "youremail@mail.com");
		email.clear();
		email.sendKeys("another@email.com");

		WebElement country = driver.findElement(By.id("Address_Country"));
		Select selectCountry = new Select(country);

		System.out.println(selectCountry.getFirstSelectedOption().getText());
		selectCountry.selectByIndex(67);
		System.out.println();

		// generate stale element exception

		WebElement cSalary = driver.findElement(By.name("Number"));
		assertEquals(cSalary.isDisplayed(), true);

		// driver.get("//http.google.com")
		driver.findElement(By.xpath("//em[.=' Next ']")).click();
		// cSalary.sendKeys("123456");
	}

	@Ignore
	@Test
	public void myLinks() {
		driver.get("http://github.com");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int numberOfLinksOnGithub = links.size();
		System.out.println("number of links " + numberOfLinksOnGithub);

		// print of each link
		List<WebElement> linksName = driver.findElements(By.tagName("a"));
		for (WebElement webElement : linksName) {
			if (!webElement.getText().isEmpty())
				System.out.println(webElement.getText());
		}

		List<String> linksNameString = new ArrayList<>();
		for (WebElement each : linksName) {
			if (!each.getText().isEmpty())
				linksNameString.add(each.getText());
		}
		System.out.println(linksNameString);

	}

	/*
	 * navigate * Find all input boxes and assign to List of webelements -> 2 Find
	 * all drop down boxes and assign to another List of webelements -> 3 Find all
	 * check boxes and assign to another List of webelements -> 9 Find all radio
	 * boxes and assign to another List of webelements -> 9 Find all buttons and
	 * assign to another List of webelements -> 1 assert each one’s count
	 *
	 */

	@Test
	public void SeleniumWebElementsForm() {
		driver.navigate().to(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");

		List<WebElement> boxesLinks = driver.findElements(By.xpath("//input[@type='text']")); //// input[@type='text']
		System.out.println(boxesLinks.size());
		List<WebElement> dropDownBoxes = driver.findElements(By.tagName("select"));
		System.out.println(dropDownBoxes.size());

		List<WebElement> check = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(check.size());

		List<WebElement> radio = driver.findElements(By.xpath("//input[@type='radio']"));
		System.out.println(radio.size());

		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		System.out.println(buttons.size());

		assertEquals(boxesLinks.size(), 2);
		assertEquals(dropDownBoxes.size(), 3);
		assertEquals(check.size(), 9);
		assertEquals(radio.size(), 9);
		assertEquals(buttons.size(), 1, "message will showif it fails");

	}

	@Ignore
	@Test
	public void slideShow() throws InterruptedException {
		driver.get("https://www.hbloom.com/Gifts/birthday-flowers");
		List<WebElement> images = driver.findElements(By.tagName("img"));
		List<String> srcs = new ArrayList<>();

		for (WebElement flower : images) {
			srcs.add(flower.getAttribute("src"));
		}

		for (String link : srcs) {
			driver.get(link);
			Thread.sleep(1234);
		}

	}

	@Test
	public void loop() throws InterruptedException {
		/*
		 * Homework: Loop through each inputbox and enter random names Loop through each
		 * dropDown and randomly select by index Loop through each checkBoxes and select
		 * each one Loop through each radioButton and click one by one by waiting one
		 * second intervals click all buttons
		 */
		driver.navigate().to(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		Faker faker = new Faker();
		List<WebElement> boxesLinks = driver.findElements(By.xpath("//input[@type='text']")); //// input[@type='text']
		for (WebElement each : boxesLinks) {
			each.sendKeys(faker.name().firstName());
		}

		List<WebElement> dropDownBoxes = driver.findElements(By.tagName("select"));

		Select selection;
		for (WebElement each : dropDownBoxes) {
			selection = new Select(each);
			selection.selectByIndex(faker.number().numberBetween(1, 4));
		}

		 List<WebElement> check = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement each : check) {
			if(faker.number().numberBetween(0, 2)==1)  each.click();
		}
		
//		 List<WebElement> radio = driver.findElements(By.xpath("//input[@type='radio']"));
		 List<WebElement> radio1 =driver.findElements(By.xpath("//input[@name='MatrixChoice1_row1']"));
		 List<WebElement> radio2 =driver.findElements(By.xpath("//input[@name='MatrixChoice1_row2']"));
		 List<WebElement> radio3 =driver.findElements(By.xpath("//input[@name='MatrixChoice1_row3']"));
		 
			 for (WebElement each : radio1) {	
				int rate = faker.number().numberBetween(1,4);
				switch(rate) {
					case 1:driver.findElement(By.xpath("//input[@id='MatrixChoice1_row1_column1']")).click();
						break;
					case 2:driver.findElement(By.xpath("//input[@id='MatrixChoice1_row1_column2']")).click();
						break;
					case 3:driver.findElement(By.xpath("//input[@id='MatrixChoice1_row1_column3']")).click();
						break;
				}		
			 }Thread.sleep(1000);
			 for (WebElement each : radio2) {	
					int rate = faker.number().numberBetween(1,4);
					switch(rate) {
					case 1:driver.findElement(By.xpath("//input[@id='MatrixChoice1_row2_column1']")).click();
						break;
					case 2:driver.findElement(By.xpath("//input[@id='MatrixChoice1_row2_column2']")).click();
						break;
					case 3:driver.findElement(By.xpath("//input[@id='MatrixChoice1_row2_column3']")).click();
						break;
					}		
				 }Thread.sleep(1000);
			 for (WebElement each : radio3) {	
					int rate = faker.number().numberBetween(1,4);
					switch(rate) {
					case 1:driver.findElement(By.xpath("//input[@id='MatrixChoice1_row3_column1']")).click();
						break;
					case 2:driver.findElement(By.xpath("//input[@id='MatrixChoice1_row3_column2']")).click();
						break;
					case 3:driver.findElement(By.xpath("//input[@id='MatrixChoice1_row3_column3']")).click();
						break;
					}		
				 }
		
			 List<WebElement> buttons = driver.findElements(By.tagName("button"));
			 buttons.get(0).click();

	}

}
