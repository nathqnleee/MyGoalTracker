package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Testing class for the ExerciseGoal class
public class ExerciseGoalTest {
    ExerciseGoal testGoal;

    @BeforeEach
    public void setup() {
        testGoal = new ExerciseGoal("Bench Press", 3, 225, Category.HYPERTROPHY);
    }

    @Test
    public void testConstructor() {
        assertEquals("Bench Press", testGoal.getName());
        assertEquals(3, testGoal.getReps());
        assertEquals(225, testGoal.getWeight());
        assertEquals(Category.HYPERTROPHY, testGoal.getCategory());
    }
}
