# Fitness Progression Tracker
**Function of Application**

A fitness tracking application to manage personal progress in the gym. The application will allow the user to enter
information about their goals for a specific exercise indicating their goals for reps and weight. 

Future possible features include:
- A line graph indicating the reps progress for an exercise
- Having a randomized fitness tip/advice bank for each exercise

**Potential Users**

Anyone looking for a tool to assist their fitness journeys will use this application. From beginner lifters to those
that want to compete on stage can benefit from the application.

**Inspiration**

Fitness and bodybuilding is a passion that I discovered in first year. It is a passion that allows me to decompress,
feel more mentally stable and more confident in my own skin. A little over a year into my fitness journey, I have
experienced lessons, successes and failures that have shaped my knowledge of fitness as a whole. Obtaining this
knowledge and being able to help my friends with their own fitness goals is a fulfilling feeling that I would not trade
for anything.

Being given the opportunity to relate my passion and career ambitions in CPSC 210 is what makes this project of interest
to me. I firmly believe that career interests should not be seen as purely a method of making money. Instead, taking the
initiative to relate it to your passions will make it much more motivating and fulfilling.

## User Stories
- As a user, I want to be able to add new goal to my fitness goal list (X and Y Component)
- As a user, I want to be able to select my fitness goal list and view all the goals within it 
- As a user, I want to be able to view the last inputted goal for an exercise in my goal list 
- As a user, I want to be able to see the number of goals currently in my goal list 
- As a user, I want to be able to save my fitness goal lists (if I choose to) 
- As a user, I want to be able to load my fitness goal lists (if I choose to)

# Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by inputting values for the exercise name, reps, and weight then clicking the button labelled "Add Goal"
- You can generate the second required action related to adding Xs to a Y by clicking on an added goal in the list and then clicking the button labelled "Remove Goal"
- You can generate the additional action related to adding Xs to a Y by clicking on the button labelled "Get Total Number of Goals" 
- You can locate my visual component at the top of the GUI 
- You can save the state of my application by clicking the "Save Goal Tracker" button
- You can reload the state of my application by clicking the "Load Goal Tracker" button

# Phase 4: Task 2
Keep up the good work!!  
Sun Apr 09 22:26:12 PDT 2023  
Added new exercise goal to exercise goal list  
Sun Apr 09 22:26:16 PDT 2023  
Added new exercise goal to exercise goal list  
Sun Apr 09 22:26:20 PDT 2023  
Added new exercise goal to exercise goal list  
Sun Apr 09 22:26:21 PDT 2023  
Counted all goals in exercise goal list  
Sun Apr 09 22:26:22 PDT 2023  
Counted all goals in exercise goal list  

# Phase 4: Task 3
A possible refactoring change I would make to my project is to extract a method in the TrackerApp class to reduce duplication within the viewGoal() and viewLatestGoal() methods. In both methods, there are 4 print statements that are identical with the only difference being the ExerciseGoal object that the information is being extracted from. Therefore, this would be an easy refactoring change by just simply making a method that takes in an ExerciseGoal object as a parameter. 

Reflecting on phase 3, there was also a change that I made that I did not realize was considered refactoring. To meet the checkstyle requirements, I seperated my FitnessTrackerUI() constructor into multiple methods of code. Although I did not know this was a type of refactoring in the moment, this change made my code easier to understand. 




