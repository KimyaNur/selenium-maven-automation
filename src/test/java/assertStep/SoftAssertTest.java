package assertStep;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertTest {		

	SoftAssert softAssert= new SoftAssert();
	
	
	@Test
	public void test1() {
		int i=10;
		int j=5;
		System.out.println("first assertion");
		softAssert.assertEquals(i,j,"i and j are not equal");
		
		System.out.println("second assertion");
		softAssert.assertNotEquals(i,j);
	
		System.out.println("third assertion");
		softAssert.assertTrue(i>j);
		
		softAssert.assertAll();
	}



	
}
