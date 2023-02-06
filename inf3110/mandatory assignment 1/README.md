This delivery has been corrected, and the grade is: Passed

Design document
Your design document is completely alright. You could have explained why you decided to make the classes you did, and what you based it on. In your case you it looks like you defined a class for most of the non-terminals in the grammar.

Drawings
Great job, you included a very pretty and accurate parse tree.

Nitpicks
An alternative to what you did for handling undefined variables and robot going out of bounds is using exceptions. This is however not asked for, so the way you do it is just fine.

You use an interface for Statement, and include a robot as an argument for the interpret method defined there. This can actually be avoided if you simply make a robot variable available for all Statements. This way you can use the Robol interpret method for all Statements instead of making a new interpret method in Statement.

The way your Stop statement works is one possible interpretation, that it simply prints out the position of the robot when it stops for one round. But you write in the design document that the program would've ended after the Stop nonetheless, and that isn't true if you add statements after it. If you want to stop the program with the Stop statement, you need to do something more.

Good luck with assignment 2!

Espen Volnes