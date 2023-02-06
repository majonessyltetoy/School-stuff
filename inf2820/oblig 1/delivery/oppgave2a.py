# loading the fsa recognizer
execfile(".dfa_recog.py")

L1 = DFA()

# L1
L1.start = 0
L1.finals.append(1)
L1.finals.append(4)
L1.finals.append(5)
L1.edge[(0,'b')] = 1
L1.edge[(0,'a')] = 2
L1.edge[(1,'a')] = 1
L1.edge[(1,'c')] = 1
L1.edge[(2,'a')] = 2
L1.edge[(2,'b')] = 1
L1.edge[(2,'c')] = 3
L1.edge[(3,'a')] = 4
L1.edge[(3,'b')] = 5
