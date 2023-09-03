package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an exercise goal storing exercise name as well as goals for reps completed and weights used (in pounds)
public class ExerciseGoal implements Writable {
    private String name;
    private int reps;
    private double weight;

    private Category category;

    // REQUIRES: length of exerciseName is non-zero, weightGoal is non-negative
    // EFFECTS: creates exercise goal by setting name to exerciseName, reps to repGoal and weight to weightGoal
    public ExerciseGoal(String exerciseName, int repGoal, double weightGoal, Category category) {
        this.name = exerciseName;
        this.reps = repGoal;
        this.weight = weightGoal;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    public Category getCategory() {
        return category;
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject sendToJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rep goal", reps);
        json.put("weight goal", weight);
        json.put("category", category);
        return json;
    }
}
