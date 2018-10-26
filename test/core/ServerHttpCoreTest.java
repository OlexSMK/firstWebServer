package core;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class ServerHttpCoreTest {
    @Before
    public void beforeTest(){

    }

    @Test
    public void checkOutputServerHttpCore() throws IOException {

        BufferedReader testIn = new BufferedReader( new InputStreamReader(new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes())));
        ByteArrayOutputStream testBuffer = new ByteArrayOutputStream();
        BufferedWriter testOut = new BufferedWriter(new OutputStreamWriter(testBuffer));

        ServerHTTPCore testCore = new ServerHTTPCore();
        testCore.setServerInput(testIn);
        testCore.setServerOutput(testOut);
        testCore.setResourceLocation("resource\\webapp");
        testCore.process();
        System.out.println(testBuffer.size());
        System.out.println(testBuffer.toString())
        ;
    }
}
