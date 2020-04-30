package sortingAppIO;

import sortingAppIO.util.GeneralUtilities;
import sortingAppIO.util.SortingUtilities;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner kbd = new Scanner(System.in);

        //Make fileslist from chosen directory, iterate, and show directories and files

        System.out.println("Where do I get the files? ->");
        String pathRead = kbd.nextLine();

        File[] files = new File(pathRead).listFiles();
        System.out.println("Files to sort ...");
        SortingUtilities.showFiles(files);

        System.out.println("Where do I put the summary and sorted files? ->");
        String pathWrite = kbd.nextLine();

        SortingUtilities.createMainDirectory(pathWrite);

        //Make necessary directories
        SortingUtilities.createDirectories(files,pathWrite);

        GeneralUtilities.printDashedLine();

        //Create and write Summary
        SortingUtilities.writeSummary(files,pathWrite);

        SortingUtilities.moveFiles(files,pathWrite);

        kbd.close();

    }
}
