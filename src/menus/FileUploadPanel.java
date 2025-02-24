package src.menus;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class FileUploadPanel extends JPanel {
    private JLabel label;
    private String filePath;
    private ActionListener callback;

    public FileUploadPanel(ActionListener callback) {
        this.callback = callback;

        setPreferredSize(new Dimension(400, 100));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createDashedBorder(Color.GRAY, 3, 5));
        setLayout(new GridBagLayout());

        label = new JLabel("Klik di sini untuk mengupload file");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath = selectedFile.getAbsolutePath();
                    label.setText(selectedFile.getName());
                    System.out.println("Path file: " + filePath);

                    if (callback != null) {
                        callback.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "file_uploaded"));
                    }
                }
            }
        });
    }
    public String getFilePath() {
        return filePath;
    }
}
