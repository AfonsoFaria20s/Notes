import javax.swing.*;
import java.awt.*;

public class Notes extends JFrame {
    private static FilesInfoDisplay fld = new FilesInfoDisplay();
    private static WriteFileDisplay wfld = new WriteFileDisplay();
    private static TopNavigationBar tnb = new TopNavigationBar();
    private static ShowPath sp = new ShowPath();
    private static Notes nt = new Notes();

    public void createAndShowGUI() {
        fld.createAndShowJPanel();
        wfld.createAndShowGUI();
        tnb.createAndShowGUI();
        sp.createAndShowGUI();
        this.setTitle("Notes");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(fld, BorderLayout.WEST);
        this.add(wfld);
        this.add(tnb, BorderLayout.NORTH);
        this.add(sp, BorderLayout.AFTER_LAST_LINE);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(nt);
    }
}
