package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Testing for the GoalTracker class
public class GoalTrackerTest {
    private GoalTracker testTracker;
    private ExerciseGoal testGoal1;
    private ExerciseGoal testGoal2;
    private ExerciseGoal testGoal3;

    private ExerciseGoal testGoal4;

    @BeforeEach
    public void setup() {
        testTracker = new GoalTracker("Test Tracker");
        testGoal1 = new ExerciseGoal("Bench", 7, 225, Category.HYPERTROPHY);
        testGoal2 = new ExerciseGoal("Squat", 10, 315, Category.ENDURANCE);
        testGoal3 = new ExerciseGoal("Deadlift", 7, 315, Category.STRENGTH);
        testGoal4 = new ExerciseGoal("Bench", 10, 300, Category.HYPERTROPHY);

    }

    @Test
    public void testConstructor() {
        assertEquals(0, testTracker.getListSize());
        assertEquals("Test Tracker", testTracker.getName());
    }

    @Test
    public void addGoalTest() {
        testTracker.addGoal(testGoal1);
        assertEquals(1, testTracker.getListSize());
        assertTrue(testTracker.getExerciseList().contains(testGoal1));
    }

    @Test
    public void addGoalMultipleTest() {
        testTracker.addGoal(testGoal1);
        testTracker.addGoal(testGoal2);
        testTracker.addGoal(testGoal3);
        assertEquals(3, testTracker.getListSize());
        assertTrue(testTracker.getExerciseList().contains(testGoal1));
        assertTrue(testTracker.getExerciseList().contains(testGoal2));
        assertTrue(testTracker.getExerciseList().contains(testGoal3));
    }

    @Test
    public void addSameGoalMultipleTest() {
        testTracker.addGoal(testGoal1);
        testTracker.addGoal(testGoal1);
        testTracker.addGoal(testGoal1);
        assertEquals(3, testTracker.getListSize());
        assertTrue(testTracker.getExerciseList().contains(testGoal1));
        assertFalse(testTracker.getExerciseList().contains(testGoal2));
        assertFalse(testTracker.getExerciseList().contains(testGoal3));
    }

    @Test
    public void removeGoalTest() {
        testTracker.addGoal(testGoal1);
        testTracker.removeGoal(testGoal1);
        assertEquals(0, testTracker.getListSize());
        assertFalse(testTracker.getExerciseList().contains(testGoal1));
    }

    @Test
    public void removeGoalMultiple() {
        testTracker.addGoal(testGoal1);
        testTracker.addGoal(testGoal2);
        testTracker.addGoal(testGoal3);
        testTracker.removeGoal(testGoal2);
        testTracker.removeGoal(testGoal3);
        assertEquals(1, testTracker.getListSize());
        assertTrue(testTracker.getExerciseList().contains(testGoal1));
        assertFalse(testTracker.getExerciseList().contains(testGoal2));
        assertFalse(testTracker.getExerciseList().contains(testGoal3));
    }

    @Test
    public void removeSameGoalMultiple() {
        testTracker.addGoal(testGoal1);
        testTracker.removeGoal(testGoal1);
        testTracker.removeGoal(testGoal1);
        assertEquals(0, testTracker.getListSize());
        assertFalse(testTracker.getExerciseList().contains(testGoal1));
    }

    @Test
    public void findLatestGoalOneEntryTest() {
        testTracker.addGoal(testGoal1);
        assertEquals(testGoal1, testTracker.findLatestGoal("Bench"));
    }

    @Test
    public void findLatestGoalTwoEntryTest() {
        testTracker.addGoal(testGoal1);
        testTracker.addGoal(testGoal4);
        assertEquals(testGoal4, testTracker.findLatestGoal("Bench"));
    }

    @Test
    public void findLatestGoalSameEntryTest() {
        testTracker.addGoal(testGoal1);
        testTracker.addGoal(testGoal1);
        testTracker.addGoal(testGoal1);
        assertEquals(testGoal1, testTracker.findLatestGoal("Bench"));
    }

    @Test
    public void findLatestGoalDifferentExerciseTest() {
        testTracker.addGoal(testGoal2);
        testTracker.addGoal(testGoal1);
        testTracker.addGoal(testGoal3);
        testTracker.addGoal(testGoal4);
        assertEquals(testGoal4, testTracker.findLatestGoal("Bench"));
    }

    @Test
    public void findLatestGoalNoEntryTest() {
        assertEquals(null, testTracker.findLatestGoal("Bench"));
    }
}
