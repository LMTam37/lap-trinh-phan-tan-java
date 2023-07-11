package lab1.exercise5;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static lab1.exercise5.Constants.BLOCK_SIZE;

public class JoinFile  extends SwingWorker<Void, Integer> {
    private String destFile;
    private File[] files;

    public JoinFile(String destFile, File[] files) {
        this.destFile = destFile;
        this.files = files;
    }

    public void join(String destFile, File... files)  throws Exception{
        try (FileOutputStream fos = new FileOutputStream(destFile, true)) {
            for (File file : files) {
                System.out.println("joining "+file.getAbsolutePath());
                try (FileInputStream fis = new FileInputStream(file)) {
                    int len;
                    byte[] buffer = new byte[BLOCK_SIZE];
                    while ((len = fis.read(buffer, 0, BLOCK_SIZE)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                }
            }
        }
    }

    @Override
    protected Void doInBackground() throws Exception{
        join(destFile, files);
        return null;
    }

    @Override
    protected void done() {
        JOptionPane.showMessageDialog(null,"Done join file");
    }

//        public static void main(String[] args) throws Exception{
//        new JoinFile("T:\\data\\sample.pnj", files).join(destFile,files);
//        System.out.println("DONE");
//    }
}

