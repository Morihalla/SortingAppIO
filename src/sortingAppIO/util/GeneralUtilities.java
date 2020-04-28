package sortingAppIO.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeneralUtilities {

    //Get the files' extension
    static String getExtension(File file) {
        return file.getName().substring(file.getName().lastIndexOf('.') + 1);
    }

    //Print dashed line between outputs
    static void printDashedLine() {
        System.out.println("-".repeat(100));
    }

    //Create summary-folder and -file
    static void createSummary(File[] files) {
        try {
            Path path = Paths.get("summary.txt");
            Files.createDirectory(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            List<String> header = new ArrayList<>();
            header.add("name" +
                    " ".repeat(getLongestFileLenght(files)) + "|\tReadable\t|\tWritable\t|");
            Files.write(path, header, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }

    //Iterate through files to calculate longest size of filename
    static int getLongestFileLenght(File[] files) {
        int i = 0;
        int temp = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                for (File file1 : file.listFiles()) {
                    temp = (file1.getName().length()) - (file.getName().length() + 1);
                    i = Math.max(temp, i);
                }
            } else {
                temp = file.getName().length();
            }
            i = Math.max(temp, i);
        }
        return i;
    }
}
