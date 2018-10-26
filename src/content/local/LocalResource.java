package content.local;

import java.io.*;
import java.util.Iterator;

public class LocalResource implements Iterable<String>{
    private File localResourceFile;
    private String resourcePath;

    public void set(String path) {
        resourcePath = path;
        localResourceFile = new File(path);
    }

    public boolean isAsseable() {
        boolean result = false;
        if (localResourceFile != null) {
            return localResourceFile.isFile() && localResourceFile.canRead();
        }
        return result;
    }

    public BufferedReader getContentStream() {
        if (!isAsseable()){
            return null;
        };
        BufferedReader resourceReader =null;
        try{
            resourceReader = new BufferedReader(new InputStreamReader(new FileInputStream(localResourceFile)));
        }catch (IOException e){

        };
        return resourceReader;
    }
    @Override
    public Iterator iterator() {
        return new localResourceIterator();
    }

    public class localResourceIterator implements Iterator<String> {
        BufferedReader lrReader;
        String line    = null;
        @Override
        public boolean hasNext() {
            boolean next = isAsseable();
            if(next){
                if(line == null){
                    lrReader = getContentStream();
                    }
                 try {
                     line = lrReader.readLine();
                 }
                 catch (IOException e)
                 {
                     line = null;
                 }

            }
            return next && line != null;
            //implement...
        }

        @Override
        public String next() {
            return line;
        }


    }
}