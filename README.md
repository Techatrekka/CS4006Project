# CS4006Project
19-04-2020
    - When obstacle is T, it adds an extra dot if it has to decide between two equal distances (FIXED)
    - Write 2-3 page report on project (google doc)
    - Make dots appear one after the other instead of all at once

10-04-2020
    - Add check for obstacle being generated right on the edge and preventing path from being generated (eg T generated in bottom left corner) DONE

09-04-2020
    - After algorithm implemented, draw best path using circles to fill in (to differentiate from filled in squares) DONE

06-04-2020
To do list:
    - A* algorithm DONE

31-03-2020
To do list:
    - Add numbers 1-8 (rows) and letter A-H (cols) to grid (done)
    - Add user input for start + goal positions AFTER Grid is displayed (done), then redisplay grid with start and goal positions now labeled (done)
    - User input needs to be validated (rows within 1-8, cols with A-H) (done)

26-03-2020
Okay so the idea I have for this project is to use the Board class to generate the 8x8 grid, each square will be it's own instance of Square 
class.

The Graph class will be where the A* algorthim will be coded.

The Point and Path classes are two things I'm not sure of. I think there may be a better approach to it. 
The Point class would just mark the turning parts of the Path, 
while the Path class would just be the collection of Points and contain methods that would describe the length of the Path.
