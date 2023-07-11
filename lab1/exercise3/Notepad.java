package lab1.exercise3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;

public class Notepad extends JFrame implements ActionListener {
	private JMenuBar menuBar;
	private JMenu fileMenu, editMenu, helpMenu;
	private JMenuItem iNew, iOpen, iSave, iPrint, iExit;
	private JTextArea taContent;

	public Notepad() {
		setSize(1150, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		createMenuBar();

		add(new JScrollPane(taContent = new JTextArea()));
	}

	private void createMenuBar() {
		setJMenuBar(menuBar = new JMenuBar());
		menuBar.add(fileMenu = new JMenu("File"));
		menuBar.add(editMenu = new JMenu("Edit"));
		menuBar.add(helpMenu = new JMenu("Help"));

		fileMenu.add(iNew = new JMenuItem("Tập tin mới"));
		fileMenu.add(iOpen = new JMenuItem("Mở tập tin"));
		iOpen.addActionListener(this);
		fileMenu.add(iSave = new JMenuItem("Lưu tập tin"));
		fileMenu.add(iPrint = new JMenuItem("In ra máy"));
		fileMenu.add(iExit = new JMenuItem("Thoát"));

		iNew.setMnemonic('T');
		iNew.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
		iOpen.setMnemonic('M');
		iOpen.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
		iSave.setMnemonic('L');
		iSave.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
		iPrint.setMnemonic('I');
		iPrint.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
		iExit.setMnemonic('T');
		iExit.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_DOWN_MASK));
	}

	public static void main(String[] args) {
		new Notepad().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(iOpen)) {
			JFileChooser fc = new JFileChooser();
			fc.showDialog(this,"Open");
			File selectedFile = fc.getSelectedFile();
			try {
				String fileName = selectedFile.getAbsolutePath();
				fileLoading fl = new fileLoading(fileName, taContent);
				fl.execute();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
