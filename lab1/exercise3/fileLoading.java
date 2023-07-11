package lab1.exercise3;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class fileLoading extends SwingWorker<Long, Long> {
	private String filename;
	private JTextArea taContent;

	public fileLoading(String filename, JTextArea taContent) {
		this.filename = filename;
		this.taContent = taContent;
	}

	@Override
	protected Long doInBackground() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filename));

		String line = null;

		while ((line = br.readLine()) != null) {
			taContent.append(line + "\n");
		}
		br.close();
		return null;
	}

}
