execfile("nfa_smart_recog_modified.py")
execfile(".test.py")

nfa = NFAFromFile("norwegian_abrs.nfa")

test(nfa, nrec, ".norsk_testset.txt")