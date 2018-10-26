package common;

public class Configuration {
    private String  hostName;
    private Integer portNumber;
    private String resourceLocation;

    public Configuration(String host, int port, String path) {
        this.hostName = host;
        this.portNumber = port;
        this.resourceLocation = path;
    }

    public void setHostName(String host) {
        hostName = host;
    }

    public void setPortNumber(int port){
        portNumber = port;
    }

    public String getHostName(){
        return hostName;
    }

    public Integer getPortNumber(){
        return portNumber;
    }
    public void setResourceLocation(String path){
        resourceLocation =path;
    }
    public String getResourceLocation(){
        return resourceLocation;
    }

}
