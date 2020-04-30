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
    public static void createMainDirectory(String pathName) {
        File file = new File(pathName);
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
    public static void createDirectories(File[] files, String pathWrite) {
        for (File file : files) {

            //Check if it's another directory or a file
            if (file.isDirectory()) {
                String name = GeneralUtilities.getFirstLetter(file);
                if (name.matches("[0-9]")) {
                    name = "0-9";
                } else if (name.matches("-")) {
                    name = "- Divers -";
                }

                //Create directory
                file = new File(pathWrite + "/" + name);
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
    public static void writeSummary(File[] files, String pathTo) throws IOException {

        //Check and create summary if not present
        Path path = Paths.get(pathTo + "/" +  "summary.txt");
        if (Files.notExists(path)) {
            GeneralUtilities.createSummary(files,pathTo);
            System.out.println("Summary created");
            GeneralUtilities.printDashedLine();
            writeSummary(files,pathTo);
        } else {
            for (File file : files) {

                //Write directory to summary
                if (file.isDirectory()) {

                    List<String> dirName = new ArrayList<>();
                    dirName.add("\n");
                    dirName.add(file.getName() + ":");
                    dirName.add("-".repeat(file.getName().length() + 1));
                    dirName.add("\n");
                    Files.write(path, dirName, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
                    writeSummary(file.listFiles(),pathTo);
                } else {

                    //Write the file's info
                    List<String> fileName = new ArrayList<>();
                    fileName.add(file.getName());
                    Files.write(path, fileName, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
                }
            }
        }
    }

    //Move files
    public static void moveFiles(File[] files,String pathWrite) {
        for (File file : files) {
            if (file.isDirectory()) {
                try {
                    if (file.isDirectory()) {
                        String firstLetter = GeneralUtilities.getFirstLetter(file);
                        if (firstLetter.matches("[0-9]")) {
                            firstLetter = "0-9";
                        } else if (firstLetter.matches("-")) {
                            firstLetter = "- Divers -";
                        }

                        Path source = file.toPath();
                        Path destination = Paths.get(pathWrite + "/" + firstLetter + "/" + file.getName());
                        Files.move(source, destination);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

