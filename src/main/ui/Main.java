package ui;

import java.io.FileNotFoundException;

// Class that acts as the starting point for the compilers execution
public class Main {

    // EFFECTS: creates an instance of the TrackerApp class
    // throws FileNotFoundException if an error occurs while trying to find the file
    public static void main(String[] args) {
        try {
            new TrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
