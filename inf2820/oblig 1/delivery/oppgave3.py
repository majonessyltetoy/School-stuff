execfile(".nfa_naive_recog.py")
execfile(".nfa_smart_recog.py")

nfa2 = NFA(
start=0,
finals=[4],
edges=[
(0,'b',1),
(1,'a',2),
(2,'a',3),
(3,'a',3),
(3,'!',4)
])

test_string = "baa!"
print "String: " + test_string
print "naive recog: ", nrec(test_string, nfa2, 0)
print "smart recog: ", naiveenrec(test_string, nfa2, 0)
print ""

test_string = "baaaaaab"
print "String: " + test_string
print "naive recog: ", nrec(test_string, nfa2, 0)
print "smart recog: ", naiveenrec(test_string, nfa2, 0)
print ""

test_string = "baab!"
print "String: " + test_string
print "naive recog: ", nrec(test_string, nfa2, 0)
print "smart recog: ", naiveenrec(test_string, nfa2, 0)
print ""

test_string = "baaaaaa!"
print "String: " + test_string
print "naive recog: ", nrec(test_string, nfa2, 0)
print "smart recog: ", naiveenrec(test_string, nfa2, 0)
print ""