package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// Represents a list of exercise goals
public class GoalTracker implements Writable {

    private String name;
    private List<ExerciseGoal> goalList;

    // EFFECTS: goalList is set to an empty list
    public GoalTracker(String name) {
        this.name = name;
        goalList = new ArrayList<>();
    }

    // EFFECTS: returns an unmodifiable list of ExerciseGoal in this GoalTracker
    public List<ExerciseGoal> getExerciseList() {
        return Collections.unmodifiableList(goalList);
    }

    // MODIFIES: this
    // EFFECTS: adds a ExerciseGoal to the goalList of a GoalTracker
    public void addGoal(ExerciseGoal goal) {
        goalList.add(goal);
        EventLog.getInstance().logEvent(new Event("Added new exercise goal to exercise goal list"));
    }

    // MODIFIES: this
    // EFFECTS: removes a ExerciseGoal from the goalList of a GoalTracker
    public void removeGoal(ExerciseGoal goal) {
        goalList.remove(goal);
        EventLog.getInstance().logEvent(new Event("Removed exercise goal from exercise goal list"));
    }

    // REQUIRES: length of exerciseName is non-zero
    // EFFECTS: returns the last inputted goal with the inputted exerciseName
    public ExerciseGoal findLatestGoal(String exerciseName) {
        List<ExerciseGoal> exerciseList = this.getGoalList();
        ExerciseGoal currentLatestGoal = null;

        for (int i = 0; i < this.getListSize(); i++) {
            if (Objects.equals(exerciseList.get(i).getName(), exerciseName)) {
                currentLatestGoal = exerciseList.get(i);
            }
        }

        return currentLatestGoal;
    }

    public int getListSize() {
        EventLog.getInstance().logEvent(new Event("Counted all goals in exercise goal list"));
        return goalList.size();
    }

    public List<ExerciseGoal> getGoalList() {
        return goalList;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject sendToJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("goalList", goalListToJson());
        return json;
    }

    // EFFECTS: returns goals in the GoalTracker as a JSON array
    private JSONArray goalListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ExerciseGoal goal : goalList) {
            jsonArray.put(goal.sendToJson());
        }

        return jsonArray;
    }
}
