package persistence;

import model.Category;
import model.ExerciseGoal;
import model.GoalTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Testing for the JsonWriterTest Class
class JsonWriterTest extends JsonTest {
    // NOTE: the inspiration for the structure of the JsonWriterTest class was based off of the given example
    @Test
    void testWriterInvalidFile() {
        try {
            GoalTracker gt = new GoalTracker("Your Personalized Fitness Tracker");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGoalTracker() {
        try {
            GoalTracker gt = new GoalTracker("Your Personalized Fitness Tracker");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGoalTracker.json");
            writer.open();
            writer.write(gt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGoalTracker.json");
            gt= reader.read();
            assertEquals("Your Personalized Fitness Tracker", gt.getName());
            assertEquals(0, gt.getListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGoalTracker() {
        try {
            GoalTracker gt = new GoalTracker("Your Personalized Fitness Tracker");
            gt.addGoal(new ExerciseGoal("deadlift", 1, 10, Category.STRENGTH));
            gt.addGoal(new ExerciseGoal("curls", 5, 100, Category.HYPERTROPHY));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGoalTracker.json");
            writer.open();
            writer.write(gt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGoalTracker.json");
            gt = reader.read();
            assertEquals("Your Personalized Fitness Tracker", gt.getName());
            List<ExerciseGoal> goalTracker = gt.getGoalList();
            assertEquals(2, goalTracker.size());
            checkExerciseGoal("deadlift", 1, 10, Category.STRENGTH, goalTracker.get(0));
            checkExerciseGoal("curls", 5, 100, Category.HYPERTROPHY, goalTracker.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}