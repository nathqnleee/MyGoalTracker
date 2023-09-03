package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Goal tracker application
public class TrackerApp {

    // NOTE: the inspiration for the structure of the TrackerApp class was based off of the given TellerApp example
    private static final String JSON_STORE = "./data/workroom.json";
    private GoalTracker goalTracker;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private EventLog el;

    // EFFECTS: runs the tracker application
    public TrackerApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        goalTracker = new GoalTracker("Your Personalized Fitness Tracker");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: allows application to take in user input and process it
    private void runTracker() {
        boolean continueApplication = true;
        String command = null;
        input = new Scanner(System.in);

        while (continueApplication) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                continueApplication = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nKeep up the good work!!");
        el = EventLog.getInstance();
        printLog(el);


    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addGoal();
        } else if (command.equals("v")) {
            viewGoal();
        } else if (command.equals("f")) {
            viewLatestGoal();
        } else if (command.equals("s")) {
            saveGoalTracker();
        } else if (command.equals("l")) {
            loadGoalTracker();
        } else if (command.equals("c")) {
            countGoals();
        } else {
            System.out.println("Option is not available...");
        }
    }

    // EFFECTS: displays goal list options to user
    private void displayMenu() {
        System.out.println("\nSelect From:");
        System.out.println("\ta: add goal");
        System.out.println("\tf: find latest goal");
        System.out.println("\tv: view goals");
        System.out.println("\tc: count all goals");
        System.out.println("\ts: save goal tracker to file");
        System.out.println("\tl: load goal tracker from file");
        System.out.println("\tq: quit");
    }

    // EFFECTS: counts number of goals in all categories
    private void countGoals() {
        System.out.println(goalTracker.getListSize());
    }

    // MODIFIES: this
    // EFFECTS: allows user to add goal to selected goal list
    private void addGoal() {
        Category category = readCategory();
        System.out.println("Enter name of exercise:");
        String name = input.next();
        System.out.println("Enter rep goal:");
        int repGoal = input.nextInt();
        System.out.println("Enter weight goal:");
        double weightGoal = input.nextDouble();

        goalTracker.addGoal(new ExerciseGoal(name, repGoal, weightGoal, category));
    }

    // EFFECTS: prompts user to select an exercise category and returns it
    private Category readCategory() {
        System.out.println("Please select a category for your thingy");

        int menuLabel = 1;
        for (Category c : Category.values()) {
            System.out.println(menuLabel + ": " + c);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return Category.values()[menuSelection - 1];
    }


    // EFFECTS: allows user to view all goals in selected goal list
    //          and outputs the number of goals in the goal tracker
    private void viewGoal() {
        List<ExerciseGoal> goalList = goalTracker.getExerciseList();
        int goalCount = 0;

        for (ExerciseGoal goal : goalList) {
            System.out.println("Name: " + goal.getName());
            System.out.println("Category: " + goal.getCategory());
            System.out.println("Rep Goal: " + goal.getReps());
            System.out.println("Weight Goal: " + goal.getWeight());
            System.out.println(" ");

            goalCount++;
        }

        System.out.println("Total goal count: " + goalCount);
    }

    // EFFECTS: allows user to view the latest goal from selected goal list
    private void viewLatestGoal() {
        System.out.println("Enter name of exercise:");
        String name = input.next();
        List<String> nameList = new ArrayList<>();

        for (ExerciseGoal goal : goalTracker.getExerciseList()) {
            nameList.add(goal.getName());
        }

        if (nameList.contains(name)) {
            ExerciseGoal latestGoal = goalTracker.findLatestGoal(name);

            System.out.println("Your latest goal for this exercise: ");
            System.out.println("Exercise Name: " + latestGoal.getName());
            System.out.println("Category: " + latestGoal.getCategory());
            System.out.println("Rep Goal: " + latestGoal.getReps());
            System.out.println("Weight Goal: " + latestGoal.getWeight());
        } else {
            System.out.println("You have no goals for this exercise");
        }
    }

    // EFFECTS: saves the GoalTracker to file
    private void saveGoalTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(goalTracker);
            jsonWriter.close();
            System.out.println("Saved " + goalTracker.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads GoalTracker from file
    private void loadGoalTracker() {
        try {
            goalTracker = jsonReader.read();
            System.out.println("Loaded " + goalTracker.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
