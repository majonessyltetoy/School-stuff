This delivery has been corrected, and the grade is: 4/6 (passed)
For the most part, this is quite good.

Obviously you won't be getting a perfect score because of the functionality that wasn't implemented. However, you seem to have a decent insight into why you struggled with it, so I won't comment on it any more.

Separating the database and the menu is a very good idea, and decently implemented to boot--well done! Separation of concerns should be a cornerstone of object-oriented programming, and it's a shame that we don't emphasize it more; in fact, yours is almost the only assignment I've seen that does this. A minor concern is that it's good practice to call the close() method on all Scanner objects when you are completely done with them (e.g. when you are about to close the program), in order to prevent memory leak. Again, this is a minor concern, though.

Beyond this, there are some minor annoyances in the form of unhandled exceptions of various types, because the prospect of a database program quitting in a huff because you accidentally wrote a "b" in the wrong field is at best depressing. You should try to ensure that the program is more robust in the future. If you are writing in Eclipse in particular, highlighting text and pressing Shift-Alt-Z will let you automatically surround the selected lines with a try-multicatch, catching any exceptions that can be thrown in the selected text.