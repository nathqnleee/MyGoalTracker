package persistence;

import model.Category;
import model.ExerciseGoal;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Testing for the JsonTest Class
public class JsonTest {
    // NOTE: the inspiration for the structure of the JsonTest class was based off of the given example
    protected void checkExerciseGoal(String exerciseName,
                                     int repGoal, double weightGoal, Category category, ExerciseGoal goal) {
        assertEquals(exerciseName, goal.getName());
        assertEquals(repGoal, goal.getReps());
        assertEquals(weightGoal, goal.getWeight());
        assertEquals(category, goal.getCategory());
    }
}