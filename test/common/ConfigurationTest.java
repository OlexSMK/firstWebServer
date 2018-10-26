package common;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationTest {
    @Test
    public void testConfiguration(){
        Configuration testCfg = new Configuration("1",2,"3");
        Assert.assertEquals("Host","1",testCfg.getHostName());
        Assert.assertEquals("Port",new Integer(2),testCfg.getPortNumber());
        Assert.assertEquals("Resource","3",testCfg.getResourceLocation());
        testCfg.setHostName("abc");
        Assert.assertEquals("Changed Host","abc",testCfg.getHostName());
        testCfg.setPortNumber(99);
        Assert.assertEquals("Changed Port",new Integer(99),testCfg.getPortNumber());
        testCfg.setResourceLocation("qwerty");
        Assert.assertEquals("Changed Resource","qwerty",testCfg.getResourceLocation());


    }
}
