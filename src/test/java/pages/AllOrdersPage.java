package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllOrdersPage {

	public AllOrdersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[.='Web Orders']")
	public WebElement webOrders;
	
	@FindBy(css=".login_info")
	public WebElement welcomeMsg;
	
	@FindBy(xpath="//h2[contains(.,'List of All Orders')]")
	public WebElement listOfAllOrders;
	
	@FindBy(linkText="View all orders")
	public WebElement viewAllOrders;
	
	@FindBy(linkText="View all products")
	public WebElement ViewAllProducts;
	
	@FindBy(linkText="Order")
	public WebElement orderTab;
	
	@FindBy(id="ctl00_logout")
	public WebElement logoutLink;
	
	public void logout() {
		logoutLink.click();
	}
	
	
	
	
	
	
	
	
//	public void login(String uid,String pwd) {
//		
//	}
	
}
