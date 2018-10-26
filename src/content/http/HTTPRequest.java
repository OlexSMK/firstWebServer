package content.http;

import java.util.ArrayList;
import java.util.StringJoiner;

public class HTTPRequest extends HTTPHeader {
    private String requestCommand;
    private String requestResource;
    private int unssuportedCommandFlag = -1;
    final private static String[] acceptedCommandList = new String[]{"GET"};

    private void validateCommand(){
        for (String cmd: acceptedCommandList) {
            if (requestCommand.equals(cmd)){
                unssuportedCommandFlag = 0;
                break;
            }
            else{
                unssuportedCommandFlag = 1;
            }
        }
    }
    public boolean isUnsuportedCommad(){
        return unssuportedCommandFlag == 1;
    }

    @Override
    public void parse(ArrayList<String> request) {
        requestCommand=null;
        requestResource=null;
        if(request == null || request.isEmpty()){
            super.invalidate();
            unssuportedCommandFlag = -1;
            return;
        }

        String[] firstLine = request.get(0).split(" ",3);
        if(firstLine.length <3){
            super.invalidate();
            unssuportedCommandFlag = -1;
            return;
        }else{
            requestCommand= firstLine[0];
            requestResource= firstLine[1];
        }
        validateCommand();
        ArrayList<String> header = new ArrayList<>();
        header.add(firstLine[2]);
        header.addAll(request.subList(1,request.size()));
        super.parse(header);
    }

    public String getRequestCommand(){
        return requestCommand;
    }

    public String getRequestResource(){
        return requestResource;
    }

    @Override
    public ArrayList<String> generate() {
        if(super.invalidContent()){
            return null;
        }
        ArrayList<String> result = super.generate();
        StringJoiner header = new StringJoiner(" ");
        header.add(requestCommand);
        header.add(requestResource);
        header.add(result.get(0));
        result.set(0,header.toString());
        return result;
    }
}
