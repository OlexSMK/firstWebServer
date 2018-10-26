package content.http;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class HTTPHeaderTest {
    @Test
    public void initialStateTest(){
        HTTPHeader test = new HTTPHeader();
        Assert.assertTrue("Not init yet",test.invalidContent());
        Assert.assertEquals("Nothing generate",null,test.generate());
    }

    @Test
    public void validHeaderTest(){
        ArrayList<String> validHeader = new ArrayList<String>();
        validHeader.add("HTTP/1.1");
        validHeader.add("Data: Test");

        HTTPHeader test = new HTTPHeader();
        test.parse(validHeader);
        Assert.assertFalse("Header now valid",test.invalidContent());
        Assert.assertEquals("Generated header = inital",validHeader,test.generate());
        test.invalidate();
        Assert.assertTrue("Header invalidated",test.invalidContent());
        Assert.assertEquals("No header after invalidation",null,test.generate());
        validHeader.remove(1);
        test.parse(validHeader);
        Assert.assertFalse("Header valid again",test.invalidContent());
        Assert.assertEquals("Generated header = initial again",validHeader,test.generate());

    }
    @Test
    public void invalidHeaderTest(){
        ArrayList<String> wrongHeader = new ArrayList<String>();
        wrongHeader.add("NOT A VERSION");

        HTTPHeader test = new HTTPHeader();
        test.parse(wrongHeader);
        Assert.assertTrue("Invalid header",test.invalidContent());
        Assert.assertEquals("No header for invalid",null,test.generate());
    }
    @Test
    public void endOfHeaderTest(){
        Assert.assertFalse("More data expected",HTTPHeader.isEndOfHeader("HTTP/1.1"));
        Assert.assertTrue("Is is end",HTTPHeader.isEndOfHeader(""));
    }
}
