package content.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HTTPHeader {
    private String protocolVersion;
    private HashMap<String,String> headerValues;
    private boolean isValidHeader   = false;
    public static final String HTTP_START ="HTTP/";
    public static final String HTTP_EOL = "\r\n";


    public static boolean isEndOfHeader(String line){
        return line.isEmpty();
    }

    public boolean invalidContent(){
        return !isValidHeader;
    }

    public void invalidate(){
        isValidHeader = false;
    }
    private boolean parseVersion(String version){
        if(version.startsWith(HTTP_START)){
            protocolVersion = version.replaceAll(HTTP_START,"");
            return true;
        }
        return false;
    }
    private boolean parseValue(String value){
        if (value.contains(": ") && !value.startsWith(": ")){
            String[] values = value.split(": ",2);
            headerValues.put(values[0],values[1]);
            return true;
        }
        return false;
    }

    public void parse(ArrayList<String> header){
        if(header == null || header.isEmpty()){
            isValidHeader = false;
            return;
        }
        int count = 0;
        protocolVersion = null;
        headerValues = new HashMap<>();
        for (String line: header){
            if(count ==0){
                isValidHeader = parseVersion(line);
            }
            else {
                isValidHeader = parseValue(line);
            }
            if(!isValidHeader){
                break;
            }
            count++;
        }

    }

    public ArrayList<String> generate(){
        ArrayList<String> output = null;
        if(isValidHeader){
             output = new ArrayList<>();
             StringBuilder line = new StringBuilder(HTTP_START);
             line.append(protocolVersion);
             output.add(line.toString());
            for (Map.Entry<String,String> value:headerValues.entrySet()) {
                StringBuilder line2 = new StringBuilder(value.getKey());
                line2.append(": ");
                line2.append(value.getValue());
                output.add(line2.toString());
            }
        }
        return output;
    }
}
