package lesson4;

import java.io.*;


public class FillLogFile {

    private final File FILE = new File(System.getProperty("user.dir") + "\\log.txt");
    private FileWriter wrt;

    public FillLogFile() throws IOException {
        if (FILE.exists()) {
            wrt = new FileWriter(FILE,true);
        } else  {
            FILE.createNewFile();
            wrt = new FileWriter(FILE,true);
        }

    }

    public boolean saveFile(String str)  throws IOException {
        try {
            wrt.write(str + System.lineSeparator());
            return true;
        }catch (IOException e) {
            return false;
        }
    }

    public void close() throws IOException{
        wrt.close();
    }

}
