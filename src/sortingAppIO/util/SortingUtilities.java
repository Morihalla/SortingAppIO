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

public class SortingUtilities extends GeneralUtilities {

    //Print out filenames and directories
    public static void showFiles(File[] files) {
        for (File file : files) {
            //Check if it's another directory or a file
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                //Reiterate inside the directory
                showFiles(file.listFiles());
                GeneralUtilities.printDashedLine();
            } else {
                //Print out filenames
                System.out.println("File: " + file.getName());
            }
        }
    }

    //Create empty destination folder
    public static void createMainDirectory() {
        File file = new File("D:\\Musique\\- sorted -");
        if (!file.exists()) {
            //Check if directory is created.
            if (file.mkdir()) {
                System.out.println(file + " is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    //Get Directory-names
    public static void createDirectories(File[] files) {
        for (File file : files) {
            //Check if it's another directory or a file
            if (file.isDirectory()) {
                String name = file.getName().substring(0, 1);
                if (name.matches("[0-9]")) {
                    name = "0-9";
                }

                //Create directory
                file = new File("D:\\Musique\\- sorted -\\" + name);
                if (!file.exists()) {
                    //Check if directory is created.
                    if (file.mkdir()) {
                        System.out.println(name + "-directory is created!");
                    } else {
                        System.out.println("Failed to create directory!");
                    }
                }
            }
        }
    }


    // Create and write summary
    public static void writeSummary(File[] files, int FREESPACE) throws IOException {

        //Check and create summary if not present
        Path path = Paths.get("sorted_folder/summary/summary.txt");
        if (Files.notExists(path)) {
            GeneralUtilities.createSummary(files);
            System.out.println("Summary created");
            GeneralUtilities.printDashedLine();
            writeSummary(files, FREESPACE);
        } else {
            for (File file : files) {

                //Write directory to summary
                if (file.isDirectory()) {

                    //Print-out reusable strings
                    String dirFreeSpace = " ".repeat(FREESPACE - file.getName().length() + 3) + "|"; // Calculate Directory-ouliner
                    String emptyColumn = "\t".repeat(3) + "|"; //Empty column
                    List<String> dirName = new ArrayList<>();
                    dirName.add(" ".repeat(FREESPACE + 4) + "|" + emptyColumn.repeat(2));
                    dirName.add(file.getName() + ":" + dirFreeSpace + emptyColumn.repeat(2));
                    dirName.add("-".repeat(file.getName().length() + 1) + dirFreeSpace + emptyColumn.repeat(2));
                    Files.write(path, dirName, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
                    writeSummary(file.listFiles(), FREESPACE);
                } else {

                    //Print-out reusable strings
                    String fileFreeSpace = " ".repeat(FREESPACE - file.getName().length()); // Calculate File-outliner
                    String yes = "\t" + " ".repeat(4) + "x\t\t" + "|";
                    String no = "\t" + " ".repeat(4) + "/\t\t" + "|";

                    //Write the file's info
                    List<String> fileName = new ArrayList<>();
                    Files.write(path, fileName, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
                }
            }
        }
    }

//    static void clearEmptyDirs(File[] files) throws IOException {
//        for (File file : files) {
//            // check if folder file is a real folder
//            if (file.isDirectory()) {
//                clearEmptyDirs(file.listFiles());
//            }
//            //Remove empty folders
//            Files.delete(Paths.get(String.valueOf(file)));
//
//            System.out.println(file.getName() + " was succesfully deleted.");
//        }
//    }

}
