package lab1.exercise5;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.List;

import static lab1.exercise5.Constants.BLOCK_SIZE;

public class SplitFile extends SwingWorker<Void, Integer> {
    private File sourceFile;
    private String destFolder;
    private int numSplitFile;
    private JProgressBar progressBar;
    private long fileSize;
    private long totalBytesRead = 0l;

    public SplitFile(File sourceFile, String destFolder, int numSplitFile, JProgressBar progressBar) {
        this.sourceFile = sourceFile;
        this.destFolder = destFolder;
        this.numSplitFile = numSplitFile;
        this.progressBar = progressBar;
    }

    public void split(File in_file, int noParts, String outFolder) throws Exception {
        fileSize = in_file.length();
        String outFile = outFolder + "/" + in_file.getName() + "-";
        DivideLength dv = new DivideLength();
        long[] parts = dv.splitLength(in_file, noParts);
        for (int i = 0; i < noParts; i++) {
            readPart(in_file, i * parts[i] - i, parts[i], outFile + i + ".vvh");
        }
    }

    private void readPart(File in_file, long start, long length, String outFilePath) throws Exception {
        byte[] buffer = new byte[BLOCK_SIZE];

        try (RandomAccessFile raf = new RandomAccessFile(in_file, "r")) {
            FileOutputStream fos = new FileOutputStream(outFilePath, true);
            int len = 0;
            raf.seek(start);
            long read = 0;
            while ((len = raf.read(buffer, 0, BLOCK_SIZE)) != -1) {
                if (read + len >= length) {
                    fos.write(buffer, 0, (int) (length - read));
                    break;
                } else
                    fos.write(buffer, 0, len);
                read += len;
                totalBytesRead += len;
                int progess = (int) ((double) totalBytesRead / fileSize * 100.0);
                publish(progess);
            }
            fos.close();
        }
    }

    @Override
    protected Void doInBackground() {
        try {
            split(sourceFile, numSplitFile, destFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        int percent = chunks.get(chunks.size() - 1);
        progressBar.setValue(percent);
    }

    @Override
    protected void done() {
        progressBar.setValue(100);
        JOptionPane.showMessageDialog(null, "Done split file");
    }
    //    public static void main(String[] args) throws Exception {
//        SplitFile sp = new SplitFile();
//        File file = new File("D:\\data\\sample.jpg");
//        sp.split(file, 2, "D:\\data");
//        System.out.println("DONE");
//    }

}
