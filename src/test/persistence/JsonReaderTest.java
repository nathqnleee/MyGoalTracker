package persistence;

import model.Category;
import model.ExerciseGoal;
import model.GoalTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Testing for the JsonReaderTest Class
public class JsonReaderTest extends JsonTest {
    // NOTE: the inspiration for the structure of the JsonReaderTest class was based off of the given example
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GoalTracker gt = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGoalTracker() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGoalTracker.json");
        try {
            GoalTracker gt = reader.read();
            assertEquals("Your Personalized Fitness Tracker", gt.getName());
            assertEquals(0, gt.getListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGoalTracker() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGoalTracker.json");
        try {
            GoalTracker gt = reader.read();
            assertEquals("Your Personalized Fitness Tracker", gt.getName());
            List<ExerciseGoal> goalTracker = gt.getGoalList();
            assertEquals(2, gt.getListSize());
            checkExerciseGoal("bench", 1, 10, Category.STRENGTH, goalTracker.get(0));
            checkExerciseGoal("flies", 10, 30, Category.HYPERTROPHY, goalTracker.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
