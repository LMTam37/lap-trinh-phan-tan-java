package lab1.exercise5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class File_Split_Join_GUI extends JFrame implements ActionListener {
    private JTabbedPane tabbedPane;
    private SplitPanel splitPanel;
    private JoinPanel joinPanel;

    public File_Split_Join_GUI() {
        setTitle("Split and Join application");
        setSize(600, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.add(tabbedPane = new JTabbedPane());

        tabbedPane.add("Split", splitPanel = new SplitPanel());
        tabbedPane.add("Join", joinPanel = new JoinPanel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
