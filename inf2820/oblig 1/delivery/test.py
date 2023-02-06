
def test(nfa, recog, exfile):
    """Test whether the nfa with recog recognize the items in exfile.

    - nfa is the name of a NFA constructed according to our conventions,
    e.g. by NFAFromFile('<filename>')
    - recog is the recognition procedure, typically nrec or
    naiveenrec. A more robust implementation would have this
    as part of the nfa-class
    - exfile is the path to the file containing the examples,
    e.g. 'norsk_testset.txt' or 'english_testset.txt'.
    The format is one sentence per line preceded by + for acceptable
    or - for non-acceptable.
    """
    grammatical = []
    ungrammatical = []
    infile = open(exfile, 'r')
    line = infile.readline()
    while line:
        if line[0] == '+':
            grammatical.append(line[1:].strip())
        if line[0] == '-':
            ungrammatical.append(line[1:].strip())
        line = infile.readline()
    print " "
    print "Correctly recognized:"
    print "**********************"
    for s in grammatical:
        if recog(s.split(), nfa):
            print s
    print " "
    print "Should have been recognized, but wasn't:"
    print "****************************************"
    for s in grammatical:
        if not recog(s.split(), nfa):
            print s
    print " "
    print "Correctly not recognized:"
    print "**********************"
    for s in ungrammatical:
        if not recog(s.split(), nfa):
            print s
    print " "
    print "Should not have been recognized, but was:"
    print "****************************************"
    for s in ungrammatical:
        if  recog(s.split(), nfa):
            print s
    print " "


            
