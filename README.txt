==============
=: Overview :=
==============

Sudoku game this time! This chess game GUI and this GUI had many similar elements.


===================
=: Core Concepts :=
===================

1. Properly representing values using 2-D arrays.
                I used a 9x9 array to model the sudoku board and their respective values.
                I had to also create methods to check for the correct solutions in each 
                respective 3x3 box and also draw appropriate borders so that each 3x3
                box was clear to the user. All the locations of everything can be
                easily accessed using a 9x9 2-D array.
                
2. File I/O: using I/O to parse a novel file format.
                I used readers to read in each sudoku game. I have a bunch of sudoku
                games in the \files folder to play with. To get these games, I just went
                to https://sudoku.game and just copied a bunch of the puzzles into .txt files.
		The difficulty of each puzzle should increase accordingly with the 
		number of the puzzle (0 = easy, 9 = hard).
                
3. Implementing the backtracking algorithm.                
                I implemented the backtracking algorithm which is a brute force
                algorithm that tries every possible solution (if needed) to find the
                solution of the puzzle. This algorithm was much easier to implement
                than Dijkstra's, and I was also able to implement this algorithm
                recurisvely as well. Apparently it is possible to implement this
                non-recursively with a stack with all the possible solutions, but
                it just seemed a lot easier to do it recursively, so that's what I did.
                Perhaps, that'll be something I'll look into for next time.
                
                
