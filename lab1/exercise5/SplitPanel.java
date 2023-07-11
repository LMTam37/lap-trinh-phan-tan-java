package lab1.exercise5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SplitPanel extends JPanel implements ActionListener {
    private JLabel lblSource, lblDest, lblNumFile;
    private JTextField txtSource;
    private JTextField txtDest;
    private JTextField txtNumFile;
    private JButton btnSource, btnDest, btnSplit;
    private JProgressBar progressBar;
    private File sourceFile;
    private String destFolder;
    public SplitPanel() {
        super(new BorderLayout());
        Box b, b1, b2, b3, b4, b5;
        b = Box.createVerticalBox();
        add(b);
        b.add(Box.createVerticalStrut(50));
        b.add(b1 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(15));
        b.add(b2 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(15));
        b.add(b3 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(15));
        b.add(b4 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(15));
        b.add(b5 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(50));

        b1.add(lblSource = new JLabel("Input File:"));
        b1.add(txtSource = new JTextField());
        b1.add(btnSource = new JButton("..."));

        b2.add(lblDest = new JLabel("Output Folder:"));
        b2.add(txtDest = new JTextField());
        b2.add(btnDest = new JButton("..."));

        b3.add(lblNumFile = new JLabel("Enter number of files to split into:"));
        b3.add(txtNumFile = new JTextField());

        b4.add(btnSplit = new JButton("Spilt lts"));

        b5.add(progressBar = new JProgressBar());
        progressBar.setStringPainted(true);
        btnSource.addActionListener(this);
        btnDest.addActionListener(this);
        btnSplit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        JFileChooser fc = new JFileChooser();
        if (o.equals(btnSource)) {
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            sourceFile = getSelectFile(fc);
            txtSource.setText(sourceFile.getAbsolutePath());
        } else if (o.equals(btnDest)) {
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            destFolder = getSelectFile(fc).getAbsolutePath();
            txtDest.setText(destFolder);
        } else if (o.equals(btnSplit)) {
            int numSplitFile = Integer.parseInt(txtNumFile.getText());
            SplitFile task = new SplitFile(sourceFile, destFolder, numSplitFile,progressBar);
            task.execute();
        }
    }

    public File getSelectFile(JFileChooser fc) {
        File file = null;
        if (fc.showDialog(this, "Open") == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        return file;
    }

}
