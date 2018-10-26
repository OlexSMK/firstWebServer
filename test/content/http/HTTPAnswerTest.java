package content.http;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class HTTPAnswerTest {
    @Test
    public void emptyAnswerTest(){
        ArrayList<String>  expectedAnswer = new ArrayList<>();
        expectedAnswer.add("HTTP/2.0");
        expectedAnswer.add("Data : Test");
        expectedAnswer.add("Data2 : Test2");


        HTTPAnswer testAnswer = new HTTPAnswer("2.0","Data : Test","Data2 : Test2");
        Assert.assertEquals("Empty response",expectedAnswer,testAnswer.generate());

    }
    @Test
    public void InvalidRequestTest(){
        ArrayList<String>  expectedAnswer = new ArrayList<>();
        expectedAnswer.add("HTTP/1.1 400 Bad Request");

        HTTPAnswer testAnswer = new HTTPAnswer();
        testAnswer.setInvalidRequest("1.1");
        Assert.assertEquals("Bad Request response",expectedAnswer,testAnswer.generate());

    }

    @Test
    public void OKTest(){
        ArrayList<String>  expectedAnswer = new ArrayList<>();
        expectedAnswer.add("HTTP/1.1 200 OK");

        HTTPAnswer testAnswer = new HTTPAnswer();
        testAnswer.setOK("1.1");
        Assert.assertEquals("Empty response",expectedAnswer,testAnswer.generate());

    }

    @Test
    public void wrongCommandTest(){
        ArrayList<String>  expectedAnswer = new ArrayList<>();
        expectedAnswer.add("HTTP/1.1 405 Method Not Allowed");

        HTTPAnswer testAnswer = new HTTPAnswer();
        testAnswer.setWrongCommand("1.1");
        Assert.assertEquals("Empty response",expectedAnswer,testAnswer.generate());

    }

    @Test
    public void NotFoundTest(){
        ArrayList<String>  expectedAnswer = new ArrayList<>();
        expectedAnswer.add("HTTP/1.1 404 Not Found");

        HTTPAnswer testAnswer = new HTTPAnswer();
        testAnswer.setNotFound("1.1");
        Assert.assertEquals("Empty response",expectedAnswer,testAnswer.generate());

    }
}
