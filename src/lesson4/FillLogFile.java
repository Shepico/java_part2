package lesson4;

import java.io.*;


public class FillLogFile {

    private final File FILE = new File(System.getProperty("user.dir") + "\\log.txt");
    private FileWriter wrt;
    //private Writer output;
    //private final String NAME_FILE = "log.txt";

    public FillLogFile() throws IOException {
        if (FILE.exists()) {
            //output = new BufferedWriter(new FileWriter(FILE)); //clears file every time
            //output.append("New Line!");
            wrt = new FileWriter(FILE,true);
        } else  {
            FILE.createNewFile();
            //output = new BufferedWriter(new FileWriter(FILE));
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
