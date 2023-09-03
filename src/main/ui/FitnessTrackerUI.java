package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

import model.ExerciseGoal;
import model.GoalTracker;
import model.Category;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents the GUI for the fitness tracker application
public class FitnessTrackerUI extends JFrame {

    private GoalTracker goalTracker;
    private JTextField name;
    private JTextField repGoal;
    private JTextField weightGoal;
    private DefaultTableModel tableModel;
    private JTable table;
    private JFrame countFrame;
    private JLabel fitnessImage;
    private JFrame loadFrame;
    private JFrame saveFrame;
    private JLabel nameLabel;
    private JLabel repLabel;
    private JLabel weightLabel;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JPanel imagePanel;
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton countButton;

    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: sets up the FitnessTracker GUI
    public FitnessTrackerUI() {
        goalTracker = new GoalTracker("Your Goal Tracker");

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        createButtons();
        createTable();
        createPanel();
        createImage();

        add(imagePanel, BorderLayout.NORTH);
        add(panel, BorderLayout.AFTER_LAST_LINE);
        add(scrollPane, BorderLayout.CENTER);

        setTitle("Fitness Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons used to add, remove, save, load and count goals
    private void createButtons() {
        addButton = new JButton("Add Goal");
        addButton.addActionListener(new AddButtonListener());

        removeButton = new JButton("Remove Goal");
        removeButton.addActionListener(new RemoveButtonListener());

        saveButton = new JButton("Save Goal Tracker");
        saveButton.addActionListener(new SaveButtonListener());

        loadButton = new JButton("Load Goal Tracker");
        loadButton.addActionListener(new LoadButtonListener());

        countButton = new JButton("Get Total Number of Goals");
        countButton.addActionListener(new CountButtonListener());
    }

    // MODIFIES: this
    // EFFECTS: creates the table in which the ExerciseGoal fields are displayed
    private void createTable() {
        String[] columnNames = {"Name", "Rep Goal", "Weight Goal"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(table);
    }

    // MODIFIES: this
    // EFFECTS: creates the panel for the text and buttons for inputting, removing, saving and loading
    private void createPanel() {
        nameLabel = new JLabel("Exercise Name: ");
        name = new JTextField(10);

        repLabel = new JLabel("Rep Goal: ");
        repGoal = new JTextField(10);

        weightLabel = new JLabel("Weight Goal: ");
        weightGoal = new JTextField(10);

        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 3));
        panel.add(nameLabel);
        panel.add(name);
        panel.add(repLabel);
        panel.add(repGoal);
        panel.add(weightLabel);
        panel.add(weightGoal);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(countButton);
    }

    // MODIFIES: this
    // EFFECTS: displays an image of an exercise icon onto the GUI
    private void createImage() {
        try {
            fitnessImage = new JLabel();
            URL imageURL = new URL("https://cdn-icons-png.flaticon.com/128/2936/2936886.png");
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(imageURL));
            fitnessImage.setIcon(imageIcon);
            fitnessImage.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        imagePanel = new JPanel();
        imagePanel.add(fitnessImage);
    }

    // Represents the ActionListener for the adding functionality
    private class AddButtonListener implements ActionListener {
        // MODIFIES: this, goalTracker
        // EFFECTS: adds a user inputted goal into the table and the GoalTracker object
        public void actionPerformed(ActionEvent event) {
            String exerciseName = name.getText();
            int repGoalNumber = Integer.parseInt(repGoal.getText());
            double weightGoalNumber = Double.parseDouble(weightGoal.getText());

            ExerciseGoal goal = new ExerciseGoal(exerciseName, repGoalNumber, weightGoalNumber, Category.HYPERTROPHY);
            goalTracker.getGoalList().add(goal);

            tableModel.addRow(new Object[]{goal.getName(), goal.getReps(), goal.getWeight(), Category.HYPERTROPHY});
            name.setText("");
            repGoal.setText("");
            weightGoal.setText("");
        }
    }

    // Represents the ActionListener for the removing functionality
    private class RemoveButtonListener implements ActionListener {
        // MODIFIES: this, goalTracker
        // EFFECTS: removes the selected goal from the table and the GoalTracker object
        public void actionPerformed(ActionEvent event) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                ExerciseGoal removedGoal = goalTracker.getGoalList().get(selectedRow);
                tableModel.removeRow(selectedRow);
                goalTracker.removeGoal(removedGoal);
            }
        }
    }

    // Represents the ActionListener for the counting functionality
    private class CountButtonListener implements ActionListener {
        // EFFECTS: outputs a message with the total goal count in the table
        public void actionPerformed(ActionEvent event) {
            countFrame = new JFrame();
            int goalCount = tableModel.getRowCount();
            JOptionPane.showMessageDialog(countFrame,"Total Goal Count is: " + goalCount);
        }
    }

    // Represents the ActionListener for the saving functionality
    private class SaveButtonListener implements ActionListener {
        // EFFECTS: saves the GoalTracker on file
        public void actionPerformed(ActionEvent event) {
            try {
                saveFrame = new JFrame();
                jsonWriter.open();
                jsonWriter.write(goalTracker);
                jsonWriter.close();
                JOptionPane.showMessageDialog(saveFrame,
                        "Saved " + goalTracker.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(saveFrame,"Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // Represents the ActionListener for the loading functionality
    private class LoadButtonListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: loads the GoalTracker from file and inputs it back into the table
        public void actionPerformed(ActionEvent event) {
            try {
                loadFrame = new JFrame();
                goalTracker = jsonReader.read();
                JOptionPane.showMessageDialog(loadFrame,
                        "Loaded " + goalTracker.getName() + " from " + JSON_STORE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(loadFrame,"Unable to read from file: " + JSON_STORE);
            }

            tableModel.setRowCount(0);

            for (ExerciseGoal goal : goalTracker.getGoalList()) {
                tableModel.addRow(new Object[]{goal.getName(), goal.getReps(), goal.getWeight()});
            }
        }
    }

    // EFFECTS: creates an instance of the FitnessTrackerUI class
    public static void main(String[] args) {
        new FitnessTrackerUI();
    }
}
