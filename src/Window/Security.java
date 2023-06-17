package Window;

import java.io.File;
import java.io.PrintWriter;

public class Security{
    private File file= new File("file1");
    public void gameOver(){
        try{
            PrintWriter pw = new PrintWriter(file);
            pw.print("Game over");
            pw.close();
        } catch (Exception e) {}
}
    public File getFile(){
        return this.file;
    }
}

