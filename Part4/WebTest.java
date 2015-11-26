package Part4;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebTest extends TestCase  {
	
	public void testTwoPlacePrecisionTemp(){
		assertEquals(Convert("212"),"212 Farenheit = 100 Celsius");
		assertEquals(Convert("0"),"0 Farenheit = -17.78 Celsius");
		assertEquals(Convert("37"),"37 Farenheit = 2.78 Celsius");
	}
	
	public void testDecimalInput(){
		assertEquals(Convert("32.37"),"32.37 Farenheit = 0.21 Celsius");
	}
	
	public void testOnePlacePrecisionTemp(){
		assertEquals(Convert("-1"),"-1 Farenheit = -18.3 Celsius");
		assertEquals(Convert("422"),"0 Farenheit = 216.7 Celsius");
	}
	
	public void testBadTemp(){
		assertEquals(Convert("e"),"Need to enter a valid temperature!Got a NumberFormatException on e");
	}
    
	public void testValidLogin(){
		 WaitAMinute();
		 assertEquals(Login("andy","apple"), "Online temperature conversion calculator");
	     assertEquals(Login("bob","bathtub"), "Online temperature conversion calculator");
	     assertEquals(Login("charley","china"), "Online temperature conversion calculator");
	}
	
	public void testLeadingWhiteSpaceUsername(){
		WaitAMinute();
		assertEquals(Login("  andy","apple"), "Online temperature conversion calculator");
	}
	
	public void testTrailingWhiteSpaceUsername(){
		WaitAMinute();
		assertEquals(Login("andy  ","apple"), "Online temperature conversion calculator");
	}
	
	public void testLeadingWhiteSpacePassword(){
		WaitAMinute();
		assertEquals(Login("andy","  apple"), "Online temperature conversion calculator");
	}
	
	public void testTrailingWhiteSpacePassword(){
		WaitAMinute();
		assertEquals(Login("andy","apple  "), "Online temperature conversion calculator");
	}
	
	public void testValidLoginCaseInsensitiveUsername(){
		WaitAMinute();
      assertEquals(Login("Andy","apple"), "Online temperature conversion calculator");
      assertEquals(Login("ANDY","apple"), "Online temperature conversion calculator");
      assertEquals(Login("AndY","apple"), "Online temperature conversion calculator");
	}
	
	
	
	public void testCaseSensitivePasswords(){
		WaitAMinute();
	      assertEquals(Login("bob","Bathtub"), "Bad Login");
		}
	
	public void testLockout(){
		WaitAMinute();
		assertEquals(Login("charley","wrong"), "Bad Login");
		assertEquals(Login("charley","wrong2"), "Bad Login");
		assertEquals(Login("charley","wrong3"), "Frequent Login");
	}
	
	public void testStayLockedOut(){
		WaitAMinute();
		assertEquals(Login("charley","wrong"), "Bad Login");
		assertEquals(Login("charley","wrong2"), "Bad Login");
		assertEquals(Login("charley","wrong3"), "Frequent Login");
		assertEquals(Login("charley","china"), "Frequent Login");
	}
	
	public void testNotLockedOutOnSuccess(){
		WaitAMinute();
		assertEquals(Login("charley","china"), "Online temperature conversion calculator");
		assertEquals(Login("charley","china"), "Online temperature conversion calculator");
		assertEquals(Login("charley","china"), "Online temperature conversion calculator");
	}
	
	public void testUnlockedAfterMinute(){
		WaitAMinute();
		Login("charley","wrong");
		Login("charley","wrong");
		Login("charley","wrong");
		WaitAMinute();
		assertEquals(Login("charley","china"), "Online temperature conversion calculator");
	}
	
	private  String Login(String name, String pass) {
        
    	// Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new HtmlUnitDriver();

        // And now use this to visit Google
        driver.get("http://apt-public.appspot.com/testing-lab-login.html");

        // Find the text input element by its name
        WebElement username = driver.findElement(By.name("userId"));

        username.clear();
        
        // Enter something to search for
        username.sendKeys(name);
        
     // Find the text input element by its name
        WebElement password = driver.findElement(By.name("userPassword"));

        password.clear();
        
        // Enter something to search for
        password.sendKeys(pass);

        // Now submit the form. WebDriver will find the form for us from the element
        username.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        String ans = driver.getTitle();
        


        driver.quit();
        
        return ans;
    }
	
	private String Convert(String temp){
		
		 WebDriver driver = new HtmlUnitDriver();

	        // And now use this to visit Google
	        driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");

	        // Find the text input element by its name
	        WebElement username = driver.findElement(By.name("farenheitTemperature"));

	        username.clear();
	        
	        // Enter something to search for
	        username.sendKeys(temp);
	        
	        // Now submit the form. WebDriver will find the form for us from the element
	        username.submit();

	        // Check the output
	        
	        String ans = driver.findElement(By.tagName("h2")).getText();
	        
	        System.out.println(driver.getCurrentUrl());

	        driver.quit();
	        
	        return ans;
		
	}
	
	private void WaitAMinute(){
		try {
	        
        	for(int i = 0; i<60; i++){
        		Thread.sleep(1000);
        		System.out.println(i);
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
    
}
