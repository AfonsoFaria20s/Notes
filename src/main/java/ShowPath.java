import javax.swing.*;
import java.awt.*;

public class ShowPath extends JPanel {
    private static TopNavigationBar tnb = new TopNavigationBar();
    private static ShowPath sp = new ShowPath();
    private static Notes nt = new Notes();

    static JLabel path = new JLabel();

    public void createAndShowGUI() {
        this.setPreferredSize(new Dimension(0, 30));
        this.setBackground(new Color(0xCD555555, true));
        this.setLayout(new BorderLayout());
        this.add(path);
    }
    public void showPath() {
        path.setText(tnb.getPath());
        path.setFont(new Font("Arial",Font.PLAIN,15));
        path.setHorizontalAlignment(SwingConstants.CENTER);
        path.validate();
    }
    public void clearPath() {
        path.setText("");
    }
}
