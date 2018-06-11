# Torus
A torus 8-puzzle is a variant with the following change: when the empty square is at the boarder, the tile at the opposite end can slide into it, too. 
Here is an example:123450678 (which 0 means the empty space)
This state has four succesors in torus 8-puzzle. three of them are the standard:
120453678
123458670
123405678
However, the fourth successor allowes the tile 4 to slide out to the left and re-enter from the right into the empty square: 123 54678
You can think of that row as a ring, where a tile going out of the board from one end automatically enters from the other end. This is true for all rows and all colums.
Here is another example state: 012345678
It has four successor states. Two are standard:
102345678
312045678
The third one comes from the torus row ring mvovement:
210345678
And the fourth one comes from the torus column ring movement:
612345078
For this project, you will use iterative deepening depth-first search to find the shortest path from an input state to the goal state (which will always be this state):123456780
Write a program Torus.java with the following command line format: $java Torus FLAG tile1 tile2 tile3 tile4 tile5 tile6 tile7 tile8 tile 9 where FLAG is an integer that specifices the output of the program. Tile1-tile9 specify the initial state in the natural reading order (leaft to right, top to bottom). These take values in integer 1-8 for the 8 tiles, plus 0 for the empty space.
For example, if the initial state is our very first example and FLAG = 100, the command line would be $java Torus 100 1 2 3 4 5 0 6 7 8

1. when FLAG = 100, print our the four successor states of the initial state, in the order they are pushed into the stack
2. when FLAG = 2XX, perform a depth-limited depth-first search with cutoff depth XX
3. when FLAG = 3XX, perform a depth-limited depth-first search with cutoff depth XX like in part b. But this time, also print out the backpointers. That is, each printed state should be follwed by the word "parent,", then the parent state (all space-separated). Use all-zero for the parent of the initial state.
4. When FLAG=4XX, perform a depth-limited depth-first search with cutoff depth XX like in part b. But this time, only print out the prefix path (starting from the initial state) for the very first state you goal-check at depth XX+1 (recall the initial state is goal-checked at depth 1).
5. when FLAG = 5XX, perform iterative deepening (which can go beyond depth cutoff 99 if necessary).
