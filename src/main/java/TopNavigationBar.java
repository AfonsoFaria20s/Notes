import com.google.zxing.WriterException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TopNavigationBar extends JPanel implements ActionListener {
    public static WriteFileDisplay wfd = new WriteFileDisplay();
    public static ShowPath sp = new ShowPath();
    public static Notes nt = new Notes();
    public static FilesInfoDisplay fid = new FilesInfoDisplay();
    public static QRCode qrCode = new QRCode();

    static File file_;

    public void createAndShowGUI() {
        this.setPreferredSize(new Dimension(0, 30));
        this.setLayout(new BorderLayout());
        this.add(addJMenuBar(), BorderLayout.NORTH);
    }

    static JFrame f = new JFrame("New File");
    static JPanel p = new JPanel();
    static JTextField tf = new JTextField();
    static JButton create = new JButton();
    public static String fileName = "";
    static String _filePath = "";

    public void showAndCreateFile() {
        f.setSize(250,150);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        p.setSize(f.getSize());
        p.setLayout(new BorderLayout());

        tf.setPreferredSize(new Dimension(200,50));

        create.setPreferredSize(new Dimension(100,50));
        create.setText("Create");
        create.addActionListener(e -> {
            try {
                PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\Utilizador\\Notas\\"+tf.getText()+".txt"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            fileName = tf.getText()+".txt";
            f.dispose();
        });

        f.add(p);
        p.add(tf, BorderLayout.CENTER);
        p.add(create, BorderLayout.SOUTH);
    }

    static JMenuBar mb;

    static JMenu file;
    static JMenu edit;
    static JMenu tools;

    // file JMenu
    static JMenuItem open;
    static JMenuItem new_;
    static JMenuItem save;
    static JMenuItem closeFile;

    // edit JMenu
    static JMenuItem selectAll;
    static JMenuItem remove;
    static JMenuItem clearAll;
    static JSeparator sep;
    static JMenuItem copy;
    static JMenuItem paste;

    // tools JMenu
    static JMenuItem clearUI;
    static JMenuItem createQRCode;

    public JMenuBar addJMenuBar() {
        mb = new JMenuBar();
        mb.setPreferredSize(new Dimension(0, 30));

        file = new JMenu("Files");
        edit = new JMenu("Edit");
        tools = new JMenu("Tools");

        open = new JMenuItem("Open");
        open.setIcon(new ImageIcon("src/main/resources/icons/open.png"));
        open.addActionListener(this);

        new_ = new JMenuItem("New");
        new_.setIcon(new ImageIcon("src/main/resources/icons/new.png"));
        new_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAndCreateFile();
            }
        });
        save = new JMenuItem("Save");
        save.setIcon(new ImageIcon("src/main/resources/icons/save.png"));
        save.addActionListener(e -> {
            try {
                saveFile();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        selectAll = new JMenuItem("Select All");
        selectAll.setIcon(new ImageIcon("src/main/resources/icons/selection.png"));
        selectAll.addActionListener(e -> {
            wfd.selectText();
        });
        remove = new JMenuItem("Remove");
        remove.setIcon(new ImageIcon("src/main/resources/icons/remove.png"));
        remove.addActionListener(e -> {
            try {
                wfd.removeText();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        clearAll = new JMenuItem("Clear All");
        clearAll.setIcon(new ImageIcon("src/main/resources/icons/clearAll.png"));
        clearAll.addActionListener(e -> {
            wfd.removeAllText();
        });
        sep = new JSeparator();

        copy = new JMenuItem("Copy");
        copy.setIcon(new ImageIcon("src/main/resources/icons/copy.png"));
        copy.addActionListener(e -> {
            copyToClipboard(wfd.getText());
        });
        paste = new JMenuItem("Paste");
        paste.setIcon(new ImageIcon("src/main/resources/icons/paste.png"));
        paste.addActionListener(e -> {
            wfd.pasteText();
        });
        closeFile = new JMenuItem("Close File");
        closeFile.setIcon(new ImageIcon("src/main/resources/icons/close.png"));
        closeFile.addActionListener(e -> {
            clearPath();
            fid.setTitleT("");
        });

        clearUI = new JMenuItem("Clear UI");
        clearUI.setIcon(new ImageIcon("src/main/resources/icons/clearUI.png"));
        clearUI.addActionListener(e -> {
            fid.setTitleT("");
        });

        createQRCode = new JMenuItem("Create QR Code");
        createQRCode.addActionListener(e -> {
            try {
                qrCode.generate(wfd.getText());
            } catch (Exception E) {
                E.printStackTrace();
            }
        });

        tools.add(clearUI);
        tools.add(createQRCode);

        edit.add(copy);
        edit.add(paste);
        edit.add(sep);
        edit.add(remove);
        edit.add(selectAll);
        edit.add(clearAll);

        file.add(new_);
        file.add(open);
        file.add(save);
        file.add(closeFile);

        mb.add(file);
        mb.add(edit);
        mb.add(tools);
        add(mb);
        return mb;
    }
    static void copyToClipboard(String text) {
        java.awt.Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new java.awt.datatransfer.StringSelection(text), null);
    }

    public void actionPerformed(ActionEvent e) { //Open file explorer
        if (e.getSource() == open) {

            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("C:\\Users\\Utilizador\\Notas"));
            File f;

            int i = fc.showOpenDialog(this);

            if (i == JFileChooser.APPROVE_OPTION) {
                f = fc.getSelectedFile();
                String filepath = f.getPath();

                try {
                    BufferedReader br = new BufferedReader(new FileReader(filepath));
                    String s1 = "", s2 = "";

                    while ((s1 = br.readLine()) != null) {
                        s2 += s1 + "\n";
                    }

                    WriteFileDisplay.setText(s2);
                    br.close();

                    fileName = f.getName();

                    _filePath = f.getPath();
                    sp.showPath();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void saveFile() throws IOException, InterruptedException {
        try {
            String str = wfd.getText();
            FileWriter fw = new FileWriter(_filePath);
            fw.write(str);
            fw.flush();
        } catch (IOException e) {
            fid.setTitleT("File Not Found!!");
        }
    }
    public void clearPath() {
        _filePath = "";
        WriteFileDisplay.setText("");
        sp.clearPath();
    }

    public String getPath() {
        return _filePath;
    }
}