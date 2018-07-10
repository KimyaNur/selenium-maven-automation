package Mentoring;

public class mockaroo1 {

}
	/*
	
	
	

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.HashSet;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	
	
	
		WebDriver driver;

		@BeforeClass
		public void setUp() {
			System.out.println("Setting up WebDriver in BeforeClass...");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().fullscreen();
		}

		@Test
		public void mainTest() throws InterruptedException, IOException {
			// Navigate to mockaroo.com
			driver.get("https://mockaroo.com/");

			// 3. Assert title is correct
			assertTrue(driver.getTitle().toLowerCase().contains("mockaroo"));

			// 4. Assert Mockaroo and realistic data generator are displayed
			assertEquals(driver.findElement(By.xpath("//div[@class='brand']")).getText(), "mockaroo");
			assertEquals(driver.findElement(By.xpath("//div[@class='tagline']")).getText(), "realistic data generator");

			// 5. Remote all existing fields by clicking on x icon link
			List<WebElement> checkboxes = driver.findElements(By.xpath("//a[contains(@class,'close')]"));
			for (WebElement wb : checkboxes) {
				wb.click();
			}

			// 6. Assert that ‘Field Name’ , ‘Type’, ‘Options’ labels are displayed
			List<WebElement> nameTypeOptions = driver
					.findElements(By.xpath("//div[contains(@class,'column column-header column')] "));
			for (WebElement wb1 : nameTypeOptions) {
				assertTrue(wb1.isDisplayed());
			}

			// 7. Assert that ‘Add another field’ button is enabled.
			assertTrue(driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']"))
					.isEnabled());

			// 8. Assert that default number of rows is 1000.
			int rowsAct = Integer.parseInt(driver.findElement(By.xpath("//input[@id='num_rows']")).getAttribute("value"));
			assertEquals(rowsAct, 1000);

			// 9. Assert that default format selection is CSV
			Select formatDrop1 = new Select(driver.findElement(By.xpath("//select[@id='schema_file_format']")));
			assertEquals(formatDrop1.getFirstSelectedOption().getText(), "CSV");

			// 10. Assert that Line Ending is Unix(LF)
			Select lineEndingDrop = new Select(driver.findElement(By.xpath("//*[@id=\"schema_line_ending\"]")));
			assertEquals(lineEndingDrop.getFirstSelectedOption().getText(), "Unix (LF)");

			// 11. Assert that header checkbox is checked and BOM is unchecked
			assertTrue(driver.findElement(By.id("schema_include_header")).isSelected());
			assertFalse(driver.findElement(By.id("schema_bom")).isSelected());

			// 12. Click on ‘Add another field’ and enter name “City”
			driver.findElement(By.xpath("//*[@id=\"schema_form\"]/div[2]/div[3]/div[2]/a")).click();
			driver.findElement(By.xpath("//div[@id='fields']/div[7]/div[2]/input")).sendKeys("City");

			// 13. Click on Choose type and assert that Choose a Type dialog box is
			// displayed
			driver.findElement(By.xpath("//*[@id=\"fields\"]/div[7]/div[3]/input[3]")).click();
			Thread.sleep(2000);
			assertTrue(driver.findElement(By.xpath("//h3[@class='modal-title'][.='Choose a Type']")).isDisplayed());

			// 14. Search for “city” and click on City on search results.
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='type_search_field']")).sendKeys("City");
			driver.findElement(By.xpath("//div[@class='type-name'][.='City']")).click();

			// 15. Repeat steps 12-14 with field name and type “Country”
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
			driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[2]/input")).clear();
			driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[2]/input")).sendKeys("Country");

			driver.findElement(By.xpath("//div[@id=\"fields\"]/div[8]/div[3]/input[3]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='type_search_field']")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='type_search_field']")).sendKeys("Country");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='type-name'][.='Country']")).click();

			// 16. Click on Download Data.
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id='download']")).click();

			// 17. Open the downloaded file using BufferedReader
			String fileName = System.getProperty("user.home") + "/Downloads/MOCK_DATA.csv";
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			// 18. Assert that first row is matching with Field names that we selected.
			String expected = "City,Country";
			String actual = br.readLine();// Not only we get the first rowm but we also skip it, because it's not an
											// actual city or country
			assertEquals(actual, expected);

			// 19. Assert that there are 1000 records
			int lineCount = 0;
			while (br.readLine() != null) {
				lineCount++;
			}
			assertEquals(lineCount, 1000);
			br.close();// close the resource

			// 20. From file add all Cities to Cities ArrayList
			List<String> cityList = new ArrayList<>();
			List<String> countryList = new ArrayList<>();

			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			String[] arrLine = null;
			while ((line = br.readLine()) != null) {
				arrLine = line.split(",");
				cityList.add(arrLine[0]);
				countryList.add(arrLine[1]);
			}
			br.close();// close the Buffered Reader
			System.out.println("Lines of City List: " + cityList.size());
			System.out.println("Lines of Country List: " + countryList.size());

			// 22. Sort all cities and find the city with the longest name and shortest name
			Collections.sort(cityList, new Comparator<String>() {
				public int compare(String s1, String s2) {
					return s1.length() - s2.length();
				}
			});

			Collections.sort(countryList, new Comparator<String>() {
				public int compare(String s1, String s2) {
					return s1.length() - s2.length();
				}
			});

			System.out.println("Shortest city is " + cityList.get(0));
			System.out.println("Shortest country is " + cityList.get(cityList.size() - 1));
			System.out.println("Longest city is " + countryList.get(0));
			System.out.println("Longest country is " + countryList.get(cityList.size() - 1));

			// 23. In Countries ArrayList, find how many times each Country is mentioned.
			Set<String> countryNum = new HashSet<String>(countryList);

			for (Iterator<String> iter = countryNum.iterator(); iter.hasNext();) {// Obviously, an easier way to do this is
																					// using Collections.frequency
				String ctryName = iter.next();
				int count = 0;
				for (int j = 0; j < countryList.size(); j++) {
					if (ctryName.equals(countryList.get(j))) {
						count++;
					}
				}
				System.out.println(ctryName + "-" + count);
			}

			// 24. From file add all Cities to citiesSet HashSet
			Set<String> citySet = new HashSet<String>(cityList);

			// 25. Count how many unique cities are in Cities ArrayList and assert that it
			// is matching with the count of citiesSet HashSet.
			List<String> cityListCopy = new ArrayList<>(cityList);

			for (Iterator<String> iter1 = cityList.iterator(); iter1.hasNext();) {
				String ctry1 = iter1.next();
				boolean check = false;

				for (Iterator<String> iter2 = cityListCopy.iterator(); iter2.hasNext();) {
					String ctry2 = iter2.next();
					if (ctry2.equals(ctry1)) {
						if (!check) {
							check = true;
							continue;
						}
						iter2.remove();
					}

				}
			}

			assertEquals(citySet.size(), cityListCopy.size());

			// 26. Add all Countries to countrySet HashSet
			Set<String> countrySet = new HashSet<String>(countryList);

			// 27. Count how many unique cities are in Countries ArrayList and assert that
			// it is matching with the count of countrySet HashSet.
			List<String> countryListCopy = new ArrayList<>(countryList);

			for (Iterator<String> iter1 = countryList.iterator(); iter1.hasNext();) {
				String ctry1 = iter1.next();
				boolean check = false;

				for (Iterator<String> iter2 = countryListCopy.iterator(); iter2.hasNext();) {
					String ctry2 = iter2.next();
					if (ctry2.equals(ctry1)) {
						if (!check) {
							check = true;
							continue;
						}
						iter2.remove();
					}

				}
			}

			assertEquals(countrySet.size(), countryListCopy.size());

		}
	
	}
	 * 
	 *public class MockarooDataValidation {
    WebDriver driver;

    @BeforeClass // runs once for all tests
    public void setUp() {
	System.out.println("Setting up WebDriver in BeforeClass...");
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	// driver.manage().window().fullscreen();
    }
    @AfterClass
    public void tearDown() {
	driver.close();
    }

    
    @Test
    public void dataValidation() throws InterruptedException, IOException {
	//==============================  #2  ========================================

	driver.get("https://mockaroo.com");
	//==============================  #3  ========================================

	assertEquals(driver.getTitle(),
		"Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel");
	//==============================  #4  ========================================

	assertEquals(driver.findElement(By.xpath("//div[@class='brand']")).getText(), "mockaroo");
	assertEquals(driver.findElement(By.xpath("//div[@class='tagline']")).getText(), "realistic data generator");
	
	//==============================  #5  ========================================

	for (int i = 6; i >= 1; i--) {
	    driver.findElement(By.xpath("(//a[@class='close remove-field remove_nested_fields'])[" + i + "]")).click();
	}
	
	//==============================  #6  ========================================

	assertEquals(driver.findElement(By.xpath("//div[@class = 'column column-header column-name']")).getText(),
		"Field Name");
	assertEquals(driver.findElement(By.xpath("//div[@class = 'column column-header column-type']")).getText(),
		"Type");
	assertEquals(driver.findElement(By.xpath("//div[@class = 'column column-header column-options']")).getText(),
		"Options");
	//==============================  #7  ========================================

	assertTrue(driver.findElement(By.xpath("//a[@class = 'btn btn-default add-column-btn add_nested_fields']"))
		.isEnabled());
	//==============================  #8  ========================================

	assertEquals(driver.findElement(By.id("num_rows")).getAttribute("value"), "1000");
	
	//==============================  #9  ========================================

	Select select = new Select(driver.findElement(By.id("schema_file_format")));
	assertEquals(select.getFirstSelectedOption().getText(), "CSV");
	
	//==============================  #10 ========================================

	Select select1 = new Select(driver.findElement(By.id("schema_line_ending")));
	assertEquals(select1.getFirstSelectedOption().getText(), "Unix (LF)");
	
	//==============================  #11 ========================================


	assertTrue(driver.findElement(By.id("schema_include_header")).isSelected());
	assertFalse(driver.findElement(By.id("schema_bom")).isSelected());
	
	//==============================  #12  ========================================

	driver.findElement(By.xpath("//a[@class = 'btn btn-default add-column-btn add_nested_fields']"))
		.sendKeys(Keys.ENTER + "city");
	
	//==============================  #13  ========================================
	
	driver.findElement(By.xpath("//div[@id='fields']/div[7]/div[3]/input[3]")).click();
	Thread.sleep(1000);
	assertEquals(driver.findElement(By.xpath("//div[@id='type_dialog']/div/div/div[1]/h3")).getText(),
		"Choose a Type");
	
	//==============================  #14  ========================================
	
	driver.findElement(By.id("type_search_field")).sendKeys("city");
	driver.findElement(By.className("type-name")).click();
	
	//==============================  #15  ========================================

	driver.findElement(By.xpath("//a[@class = 'btn btn-default add-column-btn add_nested_fields']"))
		.sendKeys(Keys.ENTER + "country");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[3]/input[3]")).click();
	Thread.sleep(1000);

	driver.findElement(By.id("type_search_field")).clear();
	driver.findElement(By.id("type_search_field")).sendKeys("country");
	driver.findElement(By.xpath("//div[@class = 'type-name' and .='Country']")).click();
	Thread.sleep(1000);
	
	//==============================  #16  ========================================

	driver.findElement(By.id("download")).click();

	//==============================  #17  ========================================
	Thread.sleep(2000);
	BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\kote4\\Downloads\\MOCK_DATA.csv"));
	List<String> data = new ArrayList<>();
	String temp = bf.readLine();
	while (temp != null) {
	    data.add(temp);
	    temp = bf.readLine();
	}
	//==============================  #18 ========================================

	assertEquals(data.get(0), "city,country");
	
	//==============================  #19  ========================================

	data.remove(0);
	assertEquals(data.size(), 1000);

	//==============================  #20  ========================================

	List<String> cities = new ArrayList<>();
	for (String str : data) {
	    cities.add(str.substring(0, str.indexOf(",")));
	}

	//==============================  #21  ========================================

	List<String> countries = new ArrayList<>();
	for (String str : data) {
	    countries.add(str.substring(str.indexOf(",") + 1));
	}

	//==============================  #22  ========================================

	Collections.sort(cities);
	String cityShort = cities.get(0);
	String cityLong = cities.get(0);
	for (int i = 1; i < cities.size(); i++) {
	    if (cityShort.length() > cities.get(i).length()) {
		cityShort = cities.get(i);
	    }
	    if (cities.get(i).length() > cityLong.length()) {
		cityLong = cities.get(i);
		}
	}
	System.out.println("The city with shortest name in the list is: "+cityShort);
	System.out.println("The city with longest name in the list is: "+cityLong);

	//==============================  #23  ========================================

	SortedSet<String> sortedCountry = new TreeSet<>(countries);
	for (String str : sortedCountry) {
		System.out.println(str + " was listed " + Collections.frequency(countries, str)+" times");
	}
	
	//==============================  #24  ========================================
	
	Set<String> citiesSet = new HashSet<>(cities);	
	
	//==============================  #25  ========================================

	int uniqueCityCount = 0;
	for (int i = 0; i < cities.size(); i++) {
		if (i == cities.lastIndexOf(cities.get(i)))
			uniqueCityCount++;
	}
	assertEquals(uniqueCityCount, citiesSet.size());
	//==============================  #26   ========================================
	
	Set<String> countrySet = new HashSet<>(countries);
	
	//==============================  #27   ========================================

	int uniqueCountryCount = 0;
	for (int i = 0; i < countries.size(); i++) {
		if (i == countries.lastIndexOf(countries.get(i)))
			uniqueCountryCount++;
	}
	assertEquals(uniqueCountryCount, countrySet.size());
    }
}
	 */


