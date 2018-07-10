package pomdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.OrderPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrderTests {
	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrdersPage;
	ProductsPage productsPage;
	OrderPage orderpage;
	Faker fk = new Faker();
	String userId = "Tester";
	String password = "test";

	@BeforeClass
	public void setUp() {
		System.out.println("Setting up WebDriver in beforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLoginPage(driver);
	}

	@Test(description = "Veriy labels and tab links are displayed ", priority = 1)
	public void labelsVerication() {
		assertEquals(driver.getTitle(), "Web Orders Login", "LoginPage is not displayed. Application is down.");
		// loginPage.userName.sendKeys(userId);
		// loginPage.password.sendKeys(password);
		// loginPage.loginButton.click();
		loginPage.login(userId, password);
		allOrdersPage = new AllOrdersPage(driver);
		assertTrue(allOrdersPage.webOrders.isDisplayed(), "Web Orders is not displayed");
		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(), "List Of All Orders label is not displayed");
		assertEquals(allOrdersPage.welcomeMsg.getText().replace(" | Logout", ""), "Welcome, " + userId + "!");
		assertTrue(allOrdersPage.viewAllOrders.isDisplayed(), "viewAllOrders is not displayed");
		assertTrue(allOrdersPage.orderTab.isDisplayed(), "orderTab is not displayed");
	}

	@Test(description = "Verify Default Products and prices", priority = 2)
	public void availableProductsTest() {
		assertEquals(driver.getTitle(), "Web Orders Login", "LoginPage is not displayed. Application is down.");
		loginPage.login(userId, password);
		allOrdersPage = new AllOrdersPage(driver);
		allOrdersPage.ViewAllProducts.click();
		productsPage = new ProductsPage(driver);
		List<String> expProducts = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actProducts = new ArrayList<>();

		// productsPage.productNames.forEach(elem->actProducts.add(elem.getText()));
		for (WebElement prod : productsPage.productNames) {
			actProducts.add(prod.getText());
		}
		assertEquals(actProducts, expProducts);

		for (WebElement row : productsPage.productsRows) {
			System.out.println(row.getText());
			String[] prodData = row.getText().split(" ");
			switch (prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1], "$100");
				assertEquals(prodData[2], "8%");
				break;
			case "FamilyAlbum":
				assertEquals(prodData[1], "$80");
				assertEquals(prodData[2], "15%");
				break;
			case "ScreenSaver":
				assertEquals(prodData[1], "$20");
				assertEquals(prodData[2], "10%");
				break;
			}
		}
	}

	@Test(description="order page",priority=3)
	public void orderPage() {
		loginPage.login(userId, password);
		orderpage=new OrderPage(driver);
		orderpage.orders.click();
		orderpage.product.click();
		orderpage.quantity.clear();
		orderpage.quantity.sendKeys("1");
		String cname=fk.name().fullName();
		orderpage.customername.sendKeys(cname); 
		String adress=fk.address().streetAddress();
		orderpage.street.sendKeys(adress);
		String adresscity=fk.address().city();
		orderpage.city.sendKeys(adresscity);
		String zip=fk.address().zipCode().substring(0,5);
		orderpage.zipCode.sendKeys(zip);
		int number=fk.number().numberBetween(1, 3);
		String cardName="";
		switch (number) {
		case 1:
			cardName="Visa";
			orderpage.cardType1.click();
			break;
        case 2:
        	cardName="MasterCard";
        	orderpage.cardType2.click();
			break;
        case 3:
        	cardName="American Express";
        	orderpage.cardType3.click();
	         break;		
		}
		String cardN=fk.number().digits(12);
		orderpage.cardNo.sendKeys(cardN);
		String date=fk.number().numberBetween(10, 12)+"/"+fk.number().numberBetween(18, 99);
		orderpage.expiredate.sendKeys(date);
		orderpage.processClick.click();
		orderpage.Allorders.click();
		
		List<String> expOrders = Arrays.asList("",cname,"MyMoney", "1", "07/09/2018",
				adress,adresscity," ",zip,cardName,cardN,date,"");
		List<String> actOrders = new ArrayList<>();

		
		for (WebElement prod : orderpage.allRow) {
			actOrders.add(prod.getText());
		}
		int a=actOrders.lastIndexOf(",");
		
		System.out.println(actOrders);
		System.out.println(expOrders);
		assertEquals(actOrders, expOrders);
	}

	@AfterMethod
	public void logout() {
		// allOrdersPage.logout();
		// allOrdersPage.logoutLink.click();
	}

	@AfterClass
	public void tearDown() {
		// driver.close();
	}

}
