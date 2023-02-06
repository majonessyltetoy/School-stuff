execfile("nfa_smart_recog_modified.py")
nfa = NFAFromFile("cent_dollar.nfa")

test_string = "twenty one dollars ninety four cents"
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa, 0)
print ""

test_string = "ten ten dollars twenty cents"
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa, 0)
print ""

test_string = "sixteen dollars twelve cents"
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa, 0)
print ""

test_string = "eight dollars ninety nine cents"
print 'String: "' + test_string + '"'
print "Accepted:", nrec(test_string.split(" "), nfa, 0)
print ""