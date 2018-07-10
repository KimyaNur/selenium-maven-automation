package webtable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Applicant {
	WebDriver driver;
	String url = "https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8";
	Map<Integer,String> map =new HashMap<>();

	//1) goto https://forms.zohopublic.com/murodil/report/Applicants/reportperma/
	 //* DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8 
	@BeforeClass 
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}

	
//	  2) Create a HashMap 
//	  3) change row number to 100, read all data on first page and put uniquID as a KEY and
//	  Applicant info as a Value to a map. applicants.put(29,"Amer, Sal-all@dsfdsf.com-554-434-4324-130000")
	@Test(priority =1)
	public void readScore() throws InterruptedException {
		driver.findElement(By.xpath("//select[@id='recPerPage']")).click();
		driver.findElement(By.xpath("//option[.='100']")).click();
		
		printTableData("reportTab");		
		
		// 4) Click on next page , repeat step 3 
		driver.findElement(By.xpath("//a[@class='nxtArrow']")).click();
		
		 
		//  5) Repeat step 4 for all pages 
		printTableData("reportTab");		
		
		//6) print count of items in a map. and assert it is matching with a number at the buttom
		//System.out.println(map);
		Assert.assertEquals("112", driver.findElement(By.xpath("//span[@id='total']")).getText());
		
	}
	
	public  void  printTableData(String id) throws InterruptedException {
		int rowsCount = driver.findElements(By.xpath("//table[@id='" + id + "']/tbody/tr")).size();
		int colsCount = driver.findElements(By.xpath("//table[@id='" + id + "']/thead/tr/th")).size();

		String tdData="";

		for (int rowNum = 1; rowNum <= 100; rowNum++) {
			String st=new String();
			for (int col = 1; col <= colsCount; col++) {
				String xpath = "//table[@id='" + id + "']/tbody/tr[" + rowNum + "]/td[" + col + "]";
				Thread.sleep(1000);
				tdData = driver.findElement(By.xpath(xpath)).getText();
				st+=(" "+tdData);
				//System.out.println(st);
			}
			String[] str=st.split(" ");
			String value="";

			for(int j=1; j<str.length-1; j++) {
				value=value+str[1+j];
				//System.out.println(value);
			}
			int a=Integer.parseInt(str[1]);
			map.put(a, value);
			//System.out.println(map);
		}
		
		System.out.println();
	}
	
	
	
	
	
	@AfterClass
	public void tearDown() {
	   //driver.quit();
	}
	
	
	

}
