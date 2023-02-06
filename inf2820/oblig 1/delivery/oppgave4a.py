execfile("nfa_smart_recog_modified.py")
nfa_template_abrs = NFAFromFile("template_abrs.nfa")

test_string = "John saw the hamburger" # it was love at first sight
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa_template_abrs, 0)
print ""

test_string = "John the saw hamburger"
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa_template_abrs, 0)
print ""

test_string = "Mary likes a cake"
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa_template_abrs, 0)
print ""

test_string = "the hamburger John saw"
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa_template_abrs, 0)
print ""

test_string = "a chicken saw the chicken"
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa_template_abrs, 0)
print ""

test_string = "a table likes Mary" # improbable, but grammatically sound
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa_template_abrs, 0)
print ""

