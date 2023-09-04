import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FilesInfoDisplay extends JPanel {
    private static Notes notes = new Notes();
    private static TopNavigationBar tnb = new TopNavigationBar();

    static JLabel icon = new JLabel();
    static JLabel title = new JLabel();

    public void createAndShowJPanel() {
        this.setPreferredSize(new Dimension(200,0));
        this.setVisible(true);
        this.setBackground(new Color(0x96B4B4B4, true));
        this.setAlignmentX(SwingConstants.LEFT);
        this.setLayout(new BorderLayout());

        addWelcome();
    }
    public void addWelcome() {
        icon.setPreferredSize(new Dimension(0,100));
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setIcon(new ImageIcon("C:\\Users\\Utilizador\\IdeaProjects\\Notes\\src\\main\\resources\\notesIcon.png"));
        icon.setBackground(new Color(0,0,0,0));
        //icon.setBorder(new LineBorder(Color.BLACK,1));

        title.setPreferredSize(new Dimension(0,50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("Notes");
        title.setFont(new Font("Montserrat",1,15));
        title.setVerticalAlignment(SwingConstants.NORTH);
        title.setBackground(new Color(0,0,0,0));
        //title.setBorder(new LineBorder(Color.black,1));

        this.add(icon, BorderLayout.NORTH);
        this.add(title,BorderLayout.CENTER);
    }
    public void setBackgroundColor(Color c) {
        icon.setBackground(c);
        title.setBackground(c);
    }
    public void setTitleT(String t) {
        title.setText("<html><p align=\"center\">Notes<br><br>"+t+"</p></html>");
    }
}
