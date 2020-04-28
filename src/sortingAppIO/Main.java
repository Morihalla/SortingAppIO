package sortingAppIO;

import sortingAppIO.util.GeneralUtilities;
import sortingAppIO.util.SortingUtilities;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        //Make fileslist from chosen directory, iterate, and show directories and files
        File[] files = new File("D:\\Musique\\0-9").listFiles();
        System.out.println("Files to sort ...");
        SortingUtilities.showFiles(files);

        GeneralUtilities.printDashedLine();

        SortingUtilities.createMainDirectory();

        GeneralUtilities.printDashedLine();

        //Make necessary directories
        SortingUtilities.createDirectories(files);

        GeneralUtilities.printDashedLine();

    }
}
