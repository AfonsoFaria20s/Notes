import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class WriteFileDisplay extends JPanel {
    public static JTextArea jta;
    public static WriteFileDisplay wfd = new WriteFileDisplay();
    public static Notes nt = new Notes();
    public static TopNavigationBar tnb = new TopNavigationBar();

    public void createAndShowGUI() {
        this.setBackground(new Color(0x5111FF00, true));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        jta = new JTextArea();
        jta.setBackground(new Color(0xFFFFFFFF, true));
        this.add(jta);
    }

    public static void setText(String txt) {
        jta.setText(txt);
    }
    public String getText() {
        return jta.getText();
    }
    public void selectText() {
        jta.selectAll();
    }
    public void removeText() throws IOException {
        jta.replaceSelection("");
    }
    public void removeAllText() {
        jta.setText("");
    }
    public void pasteText() {
        jta.paste();
    }
}
