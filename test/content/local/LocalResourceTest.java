package content.local;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class LocalResourceTest {
    private static final int ETALON_SIZE =3;
    private static final String[] ETALON = new String[]{"111","222","333"};

    @Before
    public void beforeTest() throws IOException {
        File testFile = new  File("resource\\test_file.txt");
        testFile.createNewFile();
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testFile)));
        for(String s : ETALON){
            bf.write(s);
            bf.write("\n");
        }
        bf.close();
    }
    @Test
    public void IteartorTestWithData(){
        LocalResource lr = new LocalResource();
        lr.set("resource\\test_file.txt");
        Assert.assertTrue("Resource available", lr.isAsseable());
        int count = 0;
        for(String str: lr){
            Assert.assertTrue("Expected 3 string",count+1<=ETALON_SIZE );
            Assert.assertEquals("Line " + (count+1),ETALON[count],str);
            count++;
        }
        Assert.assertEquals("Expected lines count",ETALON_SIZE, count);
    }
    public void IteartorTestNoData(){
        LocalResource lr = new LocalResource();
        lr.set("resource\\no_such_file_exists");
        Assert.assertFalse("Resource unavailable", lr.isAsseable());
        int count = 0;
        for(String str: lr){
            count++;
        }
        Assert.assertEquals("Expect 0 string",0,count);
    }
    @After
    public void afterTest(){
        File testFile = new  File("resource\\test_file.txt");
        testFile.deleteOnExit();
    }
}
