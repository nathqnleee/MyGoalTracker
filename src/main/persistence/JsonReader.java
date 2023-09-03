package persistence;

import model.Category;
import model.GoalTracker;
import model.ExerciseGoal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads GoalTracker from JSON data stored in file
public class JsonReader {
    // NOTE: the inspiration for the structure of the JsonReader class was based off of the given example
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads GoalTracker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GoalTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses GoalTracker from JSON object and returns it
    private GoalTracker parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        GoalTracker gt = new GoalTracker(name);
        addGoals(gt, jsonObject);
        return gt;
    }

    // MODIFIES: gt
    // EFFECTS: parses goals from JSON object and adds them to GoalTracker
    private void addGoals(GoalTracker gt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("goalList");
        for (Object json : jsonArray) {
            JSONObject nextGoal = (JSONObject) json;
            addGoal(gt, nextGoal);
        }
    }

    // MODIFIES: gt
    // EFFECTS: parses goals from JSON object and adds it to GoalTracker
    private void addGoal(GoalTracker gt, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int reps = jsonObject.getInt("rep goal");
        double weight = jsonObject.getInt("weight goal");
        Category category = Category.valueOf(jsonObject.getString("category"));
        ExerciseGoal goal = new ExerciseGoal(name, reps, weight, category);
        gt.addGoal(goal);
    }
}
