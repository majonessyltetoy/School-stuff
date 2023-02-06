To execute the program run the Gui class.
To set font size manually append "show-font-size" after Gui.

Example execution:
java Gui show-font-size


If the solved board has more than one solutions, then the dropdown menu will
become available and the user can freely browse different solutions.

The program is designed for 3x3 Sudoku boards, but does work with boards of all
sizes. The grid scales automatically, and the cells scale proportionally. The
font also scales, however different machines have different fonts and font-
spacing. That is why the 'show-font-size' option is available. The program will
use the default monospace font on the system, if a non-monospace font is set
then it will cause cell spacing problems.

This program has the same deficiencies as previous assignment (if the board is
large then the stack size must be increased and it takes forever to solve it).
This is because it uses the same underlying algorithm, actually everything is
the same, save the Gui class.

One last thing. The program handles exceptions very well, except when a dupli-
cate error is on the board. The problem is that this is not discovered until
it tries to generate the structure(box,row,column), and generating the structure
is called by the Brett, but Brett can't throw an exception because it extends
Thread. Long story short I should have implemented Runnable instead of extending
Thread.