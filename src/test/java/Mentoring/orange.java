package Mentoring;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class orange {

	
WebDriver driver;
	
	String firstName; 
	String lastName; 
	String companyName; 
	String jobTitle; 
	String phoneNumber; 
	Faker data = new Faker();
	
	@BeforeClass
	public void setUpMethod() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.get("https://www.orangehrm.com/");
		driver.manage().window().maximize();
		
	}
	@BeforeMethod()
	public void Forms() {
		firstName=data.name().firstName();
		lastName=data.name().lastName();
		companyName = "Cyberbek";
		jobTitle = "IT sofware";
		phoneNumber = data.phoneNumber().cellPhone();
		
	}
//	public void requestaQuote() {
//		driver.findElement(By.xpath(" //a[@class='btn-orange btn btn--primary '][.='Request a Quote'] ")).click();
//		//selecting the package: select either professional or Enterprise
//		driver.findElement(By.xpath("//*[@id='Form_request_Package']")).sendKeys("Prosessional" + Keys.ENTER);
//		//finding first name: from the parent to the child  
//		driver.findElement(By.xpath("//div[@class='middleColumn']/input[@name='FirstName']")).sendKeys(firstName);
//		//finding last name classic and most used xpath for finding names 
//		driver.findElement(By.xpath("//input[@id='Form_request_LastName']")).sendKeys(lastName);
//		//finding Company name 
//		driver.findElement(By.xpath("//*[@id='Form_request_CompanyName']")).sendKeys(companyName);
//		//finding industry
//		driver.findElement(By.xpath(" //select [@name='Industry']/option[7]")).click();
//		//find the job title 
//		driver.findElement(By.xpath("//input[@name='JobTitle']")).sendKeys(jobTitle);
//		//find the phone number 
//		driver.findElement(By.xpath("//*[@id='Form_request_ContactPhone']")).sendKeys(phoneNumber);
//		
//	}
	
	public void requestaQuote() {
		driver.findElement(By.xpath(" //a[@class='btn-orange btn btn--primary '][.='Request a Quote'] ")).click();
		//selecting the package: select either professional or Enterprise
		driver.findElement(By.xpath("//*[@id='Form_request_Package']")).sendKeys("Prosessional" + Keys.ENTER);
		//finding first name: from the parent to the child  
		driver.findElement(By.xpath("//div[@class='middleColumn']/input[@name='FirstName']")).sendKeys(firstName);
		//finding last name classic and most used xpath for finding names 
		driver.findElement(By.xpath("//input[@id='Form_request_LastName']")).sendKeys(lastName);
		//finding Company name 
		driver.findElement(By.xpath("//*[@id='Form_request_CompanyName']")).sendKeys(companyName);
		//finding industry
		driver.findElement(By.xpath(" //select [@name='Industry']/option[7]")).click();
		//find the phone number 
		driver.findElement(By.xpath("//*[@id='Form_request_ContactPhone']")).sendKeys(phoneNumber);
		
	
}
}
