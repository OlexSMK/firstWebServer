package content.http;

import java.util.ArrayList;
import java.util.StringJoiner;

public class HTTPAnswer extends HTTPHeader {
    private String returnCode;
    private String returnMessage;

    private void addHeader(String protocol, String... values) {
        ArrayList<String> header = new ArrayList<>();
        if (protocol != null) {
            StringBuilder fullProtocol = new StringBuilder();
            fullProtocol.append(HTTPHeader.HTTP_START);
            fullProtocol.append(protocol);
            header.add(fullProtocol.toString());
        }
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                header.add(values[i]);
            }
        }
        ;
        super.parse(header);
    }

    public HTTPAnswer(){
        super();
    }

    public HTTPAnswer(String protocol, String... values){
        super();
        addHeader(protocol,values);
    }

    public void setInvalidRequest(){
        returnCode="400";
        returnMessage="Bad Request";
    }

    public void setInvalidRequest(String protocol, String... values){
        addHeader(protocol,values);
        returnCode="400";
        returnMessage="Bad Request";

    }

    public void setOK(String protocol, String... values){
        addHeader(protocol,values);
        returnCode="200";
        returnMessage="OK";
    }
    public void setOK(){
        returnCode="200";
        returnMessage="OK";
    }

    public void setWrongCommand(String protocol, String... values){
        addHeader(protocol,values);
        returnCode="405";
        returnMessage="Method Not Allowed";
    }
    public void setWrongCommand(){
        returnCode="405";
        returnMessage="Method Not Allowed";
    }
    public void setNotFound(String protocol, String... values){
        addHeader(protocol,values);
        returnCode="404";
        returnMessage="Not Found";
    }
    public void setNotFound(){
        returnCode="404";
        returnMessage="Not Found";
    }

    @Override
    public ArrayList<String> generate() {
            if(super.invalidContent()){
                return null;
            }
            ArrayList<String> result = super.generate();
            StringJoiner header = new StringJoiner(" ");
            header.add(result.get(0));
            if(returnCode != null){
                header.add(returnCode);
            }
            if(returnMessage!=null){
                header.add(returnMessage);
            }
            result.set(0,header.toString());
            return result;
    }
}
