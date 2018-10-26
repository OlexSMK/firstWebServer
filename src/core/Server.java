package core;

import common.Configuration;
import content.http.*;
import content.local.LocalResource;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    private Configuration serverConfig;


    public Server(String host, int port, String path) {
        this.serverConfig = new Configuration(host,port,path);
    }

    public Server(){
        this.serverConfig = new Configuration("localhost",3000,".");
    }

    public void setPort(int port){
        serverConfig.setPortNumber(port);
    }
    public void setWebAppPath(String path) {
        serverConfig.setResourceLocation(path);
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(serverConfig.getPortNumber());) {
            ServerHTTPCore httpCore = new ServerHTTPCore();
            httpCore.setResourceLocation(serverConfig.getResourceLocation());

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {


                    httpCore.setServerInput(bufferedReader);
                    httpCore.setServerOutput(bufferedWriter);
                    httpCore.process();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
