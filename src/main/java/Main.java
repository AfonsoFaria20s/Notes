import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static Notes notes = new Notes();
    private static FilesInfoDisplay fld = new FilesInfoDisplay();

    public static void main(String[] args) throws IOException {
        try {
            notes.createAndShowGUI();
        } catch (Exception e) {
            File file = new File("C:\\Users\\Utilizador\\Desktop\\error.txt");
            FileWriter fw = new FileWriter(file);
            fw.write(e.toString());
            fw.flush();
        }
    }
}
