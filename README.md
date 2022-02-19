# My Personal Project - Goal Tracker

## Project Proposal

Project Idea: Goal Tracker 

*What will the application do?*

This application will provide a way to add, track, and visualize various goals. 
The user can **add** how many hours they have put into achieving the goal, **track** to see the running 
total hours they have worked on each goal, and **visualize** through a graphical dashboard what progress they have made 
so far. The application will store, modify, save, and load the multiple goals and associated data. Each goal will have a 
set amount of time that represents 100% progress, and the user can continue adding time until they achieve that number. When they are 
finished, the goal will be marked as complete, and the application will say "Congratulations!"

Future functionality includes
- A Pomodoro Timer
- Adding colour + sound to the visualizations
- Incorporating more interactive art in the tracking process bar, ie the growth of a shape as a goal becomes closer and closer to full progress


*Who will use it?*

The target user of this application is a university student who has multiple goals to achieve during their school year,
and must have a consolidated way to store the about each of their goals.

*Why does this project interest me?*

This project interests me as both myself and my peers are the target user and would be extremely helpful in everyday life for managing multiple goals, and being 
able to see at a glance what the progress is on each goal.  

Additionally, I will gain valuable experience designing a Java application through each of the 4 project phases including: basic model and user interaction, data
persistence, graphical user interface, and design.

## User stories

### Phase 1 
- As a user, I want to be able to add goals to my goal tracker.
- As a user, I want to be able to add hours to each of my goals in my goal tracker.
- As a user, I want to be able to view my list of goals and the progress for each.
- As a user, I want to be able to have an inspirational quote printed when I select the option.
- As a user, I want to be able to remove goals from my goal tracker.
### Phase 2
- As a user, I want to be able to save my goal tracker with the progress so far on each goal on quitting the application. 
- As a user, I want to load my goal tracker on file on opening the application by typing view goals.
### Phase 3
GUI User Stories:
- As a user, I want to be able to add multiple goals to my goal tracker using an "Add Goal" button in my GUI.
- As a user, I want to be able to view all the goals I have added to my goal tracker in a list in a panel.

- As a user, the first event related to my goals and goal tracker is: Viewing the goal details, including name, current hours, target hours, and progress bar in a separate screen upon selecting the goal in the panel.
- As a user, a second event related to my goals and goal tracker is: Editing the goal, such as changing the name, adding time, and changing the target hours. This is also handled upon selecting the goal in the panel, changing the desired details, and pressing the "Save and Back" button.
- As a user, I want to be able to load the goal tracker on file by pressing the "Load" button in my GUI.
- As a user, I want to be able to save my goal tracker by pressing the "Save" button in my GUI.
- As a user, I want to be greeted with a Welcome message and animated visually aesthetic components upon running my goal tracker and viewing the GUI.
- As a user, I want to be able to generate a new screen that displays an inspirational visual image upon pressing the "Inspiration" button in my GUI.

### Graphical User Interface Screenshots:

![image](https://user-images.githubusercontent.com/73559847/154778826-3bf52914-c3b9-41b7-9888-dc79c5d0f1a7.png)

![image](https://user-images.githubusercontent.com/73559847/154778898-8ae3516a-bc3c-4c46-8da9-08962925d6fb.png)

![giphy (3)](https://user-images.githubusercontent.com/73559847/154778863-1a2e3d1f-2301-4b07-b320-6b951b86d011.gif)

### Phase 4

Phase 4: Task 2

Example of Event Logging:

Tue Nov 23 12:03:18 PST 2021
Added a goal:Goal 1 example


Tue Nov 23 12:03:26 PST 2021
Added a goal:Goal 2 wow!


Tue Nov 23 12:03:37 PST 2021
Added a goal:Go to Office Hours


Tue Nov 23 12:03:43 PST 2021
Added a goal:Complete Phase 4


Fri Nov 26 13:48:28 PST 2021
Added a goal:Goal Example


Fri Nov 26 13:48:36 PST 2021
Added a goal:Study for finals CPSC


Fri Nov 26 13:48:41 PST 2021
Edited a goal: Study for finals CPSC


Fri Nov 26 13:48:49 PST 2021
Added a goal:hello there!


Fri Nov 26 13:49:04 PST 2021
Edited a goal:Editing this goal


Phase 4: Task 3

Reflections on my uml class diagram and refactoring:


When reflecting on my UML Class Diagram of my code design, there are a few aspects I would change in order to improve cohesion and coupling as well as readability and maintainability.

First, the most confusing part of my design is the GUI. I would refactor this by extracting certain functions like GoalDetailAdd and GoalDetailEditVIew into separate classes that only function to edit, add, and view, not ALSO construct the UI panel. The way I could refactor this is by extracting a new public abstract class that constructs the GoalDetailScreen. Then, I would implement the unique functions of Add and Edit/View in separate classes. This serves to reduce duplication and unnecessary code.

Next, there may be certain degrees of coupling that are unnecessary, such as the relationship between the GoalTrackerScreen UI and the list of indiidual goals. It may be helpful to implement helpers that allow the UI to only access the goal list, and not each individual goal that needs to be displayed. This could decrease coupling and improve the functionality of code.
