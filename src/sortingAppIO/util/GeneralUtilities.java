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

public class GeneralUtilities {

    //Print dashed line between outputs
    public static void printDashedLine() {
        System.out.println("-".repeat(100));
    }

    //Create summary-folder and -file
    static void createSummary(File[] files) {
        try {
            Path path = Paths.get("summary.txt");
            Files.createFile(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            List<String> header = new ArrayList<>();
            header.add("name");
            Files.write(path, header, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }
}
