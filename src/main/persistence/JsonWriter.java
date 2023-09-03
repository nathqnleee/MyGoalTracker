package persistence;

import model.GoalTracker;
import org.json.JSONObject;
import java.io.*;

// Represents a writer that writes JSON representation of GoalTracker to file
public class JsonWriter {
    // NOTE: the inspiration for the structure of the JsonWriter class was based off of the given example
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of GoalTracker to file
    public void write(GoalTracker gt) {
        JSONObject json = gt.sendToJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
