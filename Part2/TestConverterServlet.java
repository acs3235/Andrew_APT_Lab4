package Part2;

import junit.framework.*;
import Part2.TestingLabConverterServlet;
import PartDos.TemperatureServlet;

import com.mockobjects.servlet.*;

public class TestConverterServlet extends TestCase {
	
	public void test_null_parameter() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
	    MockHttpServletRequest request = 
	      new MockHttpServletRequest();
	    MockHttpServletResponse response = 
	      new MockHttpServletResponse();
	    
	    //request.setupAddParameter("farenheitTemperature", "boo!");
	    response.setExpectedContentType("text/html");
	    s.doGet(request,response);
	    response.verify();
	    //System.out.println(response.getOutputStreamContents().toString());
	    //System.out.println("<html><head><title>No Temperature</title></head><body><h2>Need to enter a temperature!</h2></body></html>\r\n");
	    assertTrue("<html><head><title>No Temperature</title></head><body><h2>Need to enter a temperature!</h2></body></html>\r\n".equals(response.getOutputStreamContents().toString()));
	  }
	
	public void test_bad_parameter() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
	    MockHttpServletRequest request = 
	      new MockHttpServletRequest();
	    MockHttpServletResponse response = 
	      new MockHttpServletResponse();
	    
	    request.setupAddParameter("farenheitTemperature", "boo!");
	    response.setExpectedContentType("text/html");
	    s.doGet(request,response);
	    response.verify();
	    //System.out.println(response.getOutputStreamContents().toString());
	    String expected_string = "<html><head><title>Bad Temperature</title></head><body><h2>Need to enter a valid temperature!Got a NumberFormatException on boo!</h2></body></html>\r\n";
	    //System.out.println(expected_string);
	    assertTrue(expected_string.equals(response.getOutputStreamContents().toString()));
	  }
	
	public void test_boil() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
	    MockHttpServletRequest request = 
	      new MockHttpServletRequest();
	    MockHttpServletResponse response = 
	      new MockHttpServletResponse();
	    
	    request.setupAddParameter("farenheitTemperature", "212");
	    response.setExpectedContentType("text/html");
	    s.doGet(request,response);
	    response.verify();
	    //System.out.println(response.getOutputStreamContents().toString());
	    String expected_string ="<html><head><title>Temperature Converter Result</title></head><body><h2>212 Farenheit = 100 Celsius </h2>\r\n<p><h3>The temperature in Austin is 451 degrees Farenheit</h3>\r\n</body></html>\r\n";
	    //System.out.println(expected_string);
	    assertTrue(expected_string.equals(response.getOutputStreamContents().toString()));
	  }

}
