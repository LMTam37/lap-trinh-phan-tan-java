package lab1.exercise5;

import java.io.File;

public class DivideLength {

    public long[] splitLength(File file, int noParts) {
        long[] parts = new long[noParts];
        long file_len = file.length();
        long part_len = file_len / noParts;
        long remains = file_len % noParts;
        for (int i = 0; i < noParts; i++) {
            parts[i] = part_len;
        }
        parts[noParts - 1] += remains;

        return parts;
    }

    public static void main(String[] args) {
        DivideLength dv =new DivideLength();
        File file = new File("build.gradle");
        long []x = dv.splitLength(file,2);
        for (long a:x)
            System.out.println(a);
    }

}
