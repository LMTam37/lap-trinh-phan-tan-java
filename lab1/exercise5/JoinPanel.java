package lab1.exercise5;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JoinPanel extends JPanel implements ActionListener {
    private JButton btnAddFile;
    private JButton btnAddFolder;
    private JButton btnJoin;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtOutput;
    private ArrayList<File> splitFiles = new ArrayList<File>();

    public JoinPanel() {
        super(new BorderLayout());
        String[] cols = {"No.", "File Path", "size"};
        table = new JTable(tableModel = new DefaultTableModel(cols, 0));
        this.add(new JScrollPane(table));


        Box b, b1, b2;
        this.add(b = Box.createVerticalBox(), BorderLayout.SOUTH);
        b.add(b1 = Box.createHorizontalBox());
        b.add(b2 = Box.createHorizontalBox());
        b1.add(new JLabel("output file dir: "));
        b1.add(txtOutput = new JTextField());
        b2.add(btnAddFile = new JButton("Add File"));
        b2.add(Box.createHorizontalStrut(10));
        b2.add(btnAddFolder = new JButton("Add Folder"));
        b2.add(btnJoin = new JButton("Join"));

        btnAddFile.addActionListener(this);
        btnAddFolder.addActionListener(this);
        btnJoin.addActionListener(this);
//        TableColumnModel colModel = table.getColumnModel();
//        TableColumn col1 = colModel.getColumn(0);
//        TableColumn col3 = colModel.getColumn(2);
//        col1.setMinWidth(10);
//        col3.setWidth(20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        JFileChooser fc = new JFileChooser();
        if (o.equals(btnAddFile)) {
            fc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(".vvh");
                }

                @Override
                public String getDescription() {
                    return "VVH files (*.vvh";
                }
            });
            fc.setMultiSelectionEnabled(true);

            if (fc.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
                File[] files = fc.getSelectedFiles();
                int i = 1;
                for (File file : files) {
                    if (file.getName().endsWith(".vvh")) {
                        splitFiles.add(file);
                        String[] row = {
                                (i++) + "", file.getAbsolutePath(), file.length() + ""
                        };
                        tableModel.addRow(row);
                    }
                }
            }

        } else if (o.equals(btnAddFolder)) {
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setMultiSelectionEnabled(false);
            if (fc.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
                File selFolder = fc.getSelectedFile();
                File[] files = selFolder.listFiles();
                int i = 1;
                for (File file : files) {
                    if (file.getName().endsWith(".vvh")) {
                        splitFiles.add(file);
                        String[] row = {
                                (i++) + "", file.getAbsolutePath(), file.length() + ""
                        };
                        tableModel.addRow(row);
                    }
                }
            }
        }  else if (o.equals(btnJoin)) {
            String dest = txtOutput.getText();
            JoinFile task = new JoinFile(dest, splitFiles.toArray(new File[splitFiles.size()]));
            task.execute();
        }
    }
}
