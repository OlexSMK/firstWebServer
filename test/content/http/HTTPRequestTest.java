package content.http;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class HTTPRequestTest {
    @Test
    public void initialStateTest(){
        HTTPRequest testRequest = new HTTPRequest();
        Assert.assertFalse("Yet command supported",testRequest.isUnsuportedCommad());
        Assert.assertEquals("Nothing generate",null,testRequest.generate());
        Assert.assertEquals("Empty command",null,testRequest.getRequestCommand());
        Assert.assertEquals("Empty resource",null,testRequest.getRequestResource());
    }

    @Test
    public void validRequestTest(){
        ArrayList<String> validRequest = new ArrayList<String>();
        validRequest.add("GET abc HTTP/1.1");
        validRequest.add("Data: Test");

        HTTPRequest testRequest = new HTTPRequest();
        testRequest.parse(validRequest);
        Assert.assertFalse("Header valid",testRequest.invalidContent());
        Assert.assertFalse("Command supported",testRequest.isUnsuportedCommad());
        Assert.assertEquals("Command","GET",testRequest.getRequestCommand());
        Assert.assertEquals("Resource","abc",testRequest.getRequestResource());
        Assert.assertEquals("Generated header = initial",validRequest,testRequest.generate());
    }
    @Test
    public void unsupportedCommandTest(){
        ArrayList<String> validRequest = new ArrayList<String>();
        validRequest.add("POST xyz HTTP/1.1");
        validRequest.add("Data: Test");

        HTTPRequest testRequest = new HTTPRequest();
        testRequest.parse(validRequest);
        Assert.assertFalse("Header valid",testRequest.invalidContent());
        Assert.assertTrue("Command not supported",testRequest.isUnsuportedCommad());
        Assert.assertEquals("Command","POST",testRequest.getRequestCommand());
        Assert.assertEquals("Resource","xyz",testRequest.getRequestResource());
        Assert.assertEquals("Generated header = initial",validRequest,testRequest.generate());
    }
    @Test
    public void invalidRequestTest(){
        ArrayList<String> validRequest = new ArrayList<String>();
        validRequest.add("SOMETHING_WRONG HTTP/1.1");
        validRequest.add("Data: Test");

        HTTPRequest testRequest = new HTTPRequest();
        testRequest.parse(validRequest);
        Assert.assertTrue("Header invalid",testRequest.invalidContent());
        Assert.assertFalse("Command supported",testRequest.isUnsuportedCommad());
        Assert.assertEquals("No command",null,testRequest.getRequestCommand());
        Assert.assertEquals("No resource",null,testRequest.getRequestResource());
        Assert.assertEquals("Nothing generate",null,testRequest.generate());
    }
}
