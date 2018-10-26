import content.local.LocalResource;
import core.Server;

import java.io.IOException;

public class WebServer {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3000);
        server.setWebAppPath("resource/webapp");
        server.start();
//        LocalResource lr = new LocalResource();
//        lr.set("resource\\webapp\\index.html");
//        for(String s:lr)
//        {System.out.println(s);}
    }
}
