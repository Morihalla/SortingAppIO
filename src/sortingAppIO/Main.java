package sortingAppIO;

import sortingAppIO.util.GeneralUtilities;
import sortingAppIO.util.SortingUtilities;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //Make fileslist from chosen directory, iterate, and show directories and files
        File[] files = new File("D:\\Musique").listFiles();
        System.out.println("Files to sort ...");
        SortingUtilities.showFiles(files);

        GeneralUtilities.printDashedLine();

        SortingUtilities.createMainDirectory();

        GeneralUtilities.printDashedLine();

        //Make necessary directories
//        SortingUtilities.createDirectories(files);

        GeneralUtilities.printDashedLine();

        //Create and write Summary
        SortingUtilities.writeSummary(files);

    }
}
