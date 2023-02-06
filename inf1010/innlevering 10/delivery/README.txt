Usually I strictly adhere to 80 characters per line, but this has been dis-
regarded because of the ridiculously long name "fyllUtDenneRuteOgResten".

Reading the file has it's own class because it cluttered the main class
(SudokuLoser) unnecessarily.

If you're having stackoverflws with large boards <36x36 try adding -Xss8m to the
execution. This problem stems from the fact that methods can only send state,
not reference. My first reaction was "oh shit every Rute object has a copy of
the Brett", so I used a couple of hours to remove Brett and replace it with node
link list instead. Tested it, and stackoverflows on boards <49x49. While an imp-
rovement time efficiency took a significant hit. That's why my delivery need to
increase stack size on larger Sudoku boards.
Update: looks like my first assumption was wrong my first (slow) algorithm
manages large boards just fine... maybe it has something to do with stack size
allocated per thread... should have learned to use jdb

If there are more than one solution then there is an interactive menu for
choosing which board you want to see. If "pretty" is appended after the Sudoku
board file (e.g. java -Xss8m SudokuLoser brett.txt pretty) then the program
will print more visually pleasing Sudoku boards.

If the first guess fyllUtDenneRuteOgResten() makes has odd and even as possible
values, then one thread will do the odd, and the other the even. However this is
not likely because the algorithm is optimized to start guessing on the place
with lowest possible values.

I'm pretty satisfied with my Sudoku solver; the board i used to benchmark:

3
3
...8.1...
.......43
5........
....7.8..
......1..
.2..3....
6......75
..34.....
...2..6..

only use 70-90ms versus the first minimum requirements algorithm that took
upward of 50 seconds.