# loading the fsa recognizer
execfile(".dfa_recog.py")

L2 = DFA()

# L2
L2.start = 0
L2.finals.append(1)
L2.finals.append(3)
L2.edge[(0,'a')] = 1
L2.edge[(0,'b')] = 4
L2.edge[(1,'a')] = 0
L2.edge[(1,'b')] = 2
L2.edge[(2,'b')] = 3
L2.edge[(3,'a')] = 0
L2.edge[(4,'b')] = 5
L2.edge[(5,'a')] = 1

print "\nValid words"
print "i:\t" + drec("a", L2, 0)
print "ii:\t" + drec("bba", L2, 0)
print "iii:\t" + drec("abbaa", L2, 0)
print "iv:\t" + drec("abbabbabbaaaa", L2, 0)
print "v:\t" + drec("bbaaa", L2, 0)

print "\nInvalid words"
print "i:\t" + drec("b", L2, 0)
print "ii:\t" + drec("aa", L2, 0)
print "iii:\t" + drec("ababa", L2, 0)
print "iv:\t" + drec("abbbaa", L2, 0)
print "v:\t" + drec("abbaabbaaa", L2, 0)