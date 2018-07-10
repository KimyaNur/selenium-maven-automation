package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {


	public OrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//ul[@id='ctl00_menu']//a)[3]")
	public WebElement orders;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_ddlProduct")
	public WebElement product;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_txtQuantity")
	public WebElement quantity;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_txtName")
	public WebElement  customername;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement street;

	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement city;

	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zipCode;
	
	@FindBy(xpath="(//table[@id='ctl00_MainContent_fmwOrder_cardList']/tbody/tr/td)[1]")
    public WebElement cardType1;
	
	@FindBy(xpath="(//table[@id='ctl00_MainContent_fmwOrder_cardList']/tbody/tr/td)[2]")
    public WebElement cardType2;

	@FindBy(xpath="(//table[@id='ctl00_MainContent_fmwOrder_cardList']/tbody/tr/td)[3]")
    public WebElement cardType3;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox6")
	public WebElement cardNo;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox1")
	public WebElement expiredate;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_InsertButton")
	public WebElement processClick;
	
	@FindBy(xpath="(//ul[@id='ctl00_menu']//a)[1]")
	public WebElement Allorders;

	@FindBy(xpath="//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td")
	public List<WebElement> allRow;
	
	
}
