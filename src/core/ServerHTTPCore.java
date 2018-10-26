package core;

import content.http.HTTPAnswer;
import content.http.HTTPHeader;
import content.http.HTTPRequest;
import content.local.LocalResource;

import java.io.*;
import java.util.ArrayList;

public class ServerHTTPCore {
    private BufferedWriter serverOutput;
    private BufferedReader serverInput;
    private File resourceLocation;

    public void setServerInput(BufferedReader in){
        serverInput = in;
    }
    public void setServerOutput(BufferedWriter out){
        serverOutput = out;
    }
    public void setResourceLocation(String path) throws FileNotFoundException {
        resourceLocation = new File(path);
        if(!resourceLocation.isDirectory()){
            throw new FileNotFoundException("Resource should point to folder");
        }
    }

    private void writeLine(String line) throws IOException{
        serverOutput.write(line);
        serverOutput.write(HTTPHeader.HTTP_EOL);

    }

    private String getLocalPath(String resourcePath){
        StringBuilder localPath = new StringBuilder(resourceLocation.getPath());
        localPath.append(File.separator);
        localPath.append(resourcePath.replaceFirst("/","").replaceAll("/","\\"+File.separator));
        return localPath.toString();
    }

    public void process() throws IOException {
        if( serverInput  == null){
            throw new NullPointerException("Input not init");
        }
        if( serverOutput == null){
            throw new NullPointerException("Output not init");
        }
        if( resourceLocation  == null){
            throw new NullPointerException("Resource directory not init");
        }
        ArrayList<String> requestStr = new ArrayList<>();
        boolean readMore = true;
        while (readMore) {
            String value;
            value = serverInput.readLine();
            readMore = (value != null) && !HTTPHeader.isEndOfHeader(value);
            if (readMore) {
                requestStr.add(value);
            }
        }
        HTTPRequest request = new HTTPRequest();
        LocalResource requested = new LocalResource();
        request.parse(requestStr);
        HTTPAnswer answer = new HTTPAnswer("1.1", "Server: My WebServer");
        if (request.invalidContent()) {
            answer.setNotFound();
        }
        else if (request.isUnsuportedCommad()) {
            answer.setInvalidRequest();
        }
        else{
            requested.set(getLocalPath(request.getRequestResource()));
            if (requested.isAsseable()) {
                answer.setOK();
            } else {
                answer.setNotFound();
            }
        }
        ;
        for (String line : answer.generate()) {
            writeLine(line);
        }
        serverOutput.write(HTTPHeader.HTTP_EOL);
        for (String line : requested) {
            writeLine(line);
        }
        serverOutput.flush();
    }
}
