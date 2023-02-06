##//////////////////////////////////////////////////////
##  Shift-Reduce Recognizer
##  INF2820 V2016
##  Jan Tore Loenning
##//////////////////////////////////////////////////////

"""
A simple Shift-Reduce recognizer.

This recognizer checks all possible analyses until it succeeds,
i.e. it backtracks when failure.
If and when it succeeds, it interrupts and returns with True.

The recognizer takes the grammar as an argument.
The grammar is an nltk grammar.

It accepts grammars where the right-hand side
of a rule is a mixture of terminals and non-terminals,
as long as it is non-empty.
The price for this flexibility is less efficiency,
i.e. it shifts many times where a reduction is mandatory.

There are three levels of tracing determined by the optional
argument to the recognizer:
0 - default - no tracing
1 - prints out the value of the stack and rest of words
2 - prints the value of the stack and rest of words and describes action

A grammar is loaded like
g1 = nltk.data.load('file:../grammar1.cfg'), or simpler
g1 = load('../gramar1.cfg') which facilitates auto-completation when available.


"""

from __future__ import division
import nltk
import time

        
def load(filename):
    address='file:'+filename
    return nltk.data.load(address)


def sr_recognize(grammar, words, trace = 0):
    """Recognize the words according to the grammar using an SR-procedure"""
    if trace > 1:
        print "Initialize:\t\t",
    return recognize(grammar,[], words, trace)

 
## The structure of the recognize-procedure without comments and tracing:
## To see that it is correct, uncomment it and comment out the
## long version of recognize. 
##
##def recognize(grammar, stack, rwords, trace):
##    if rwords == [] and len(stack) == 1 and stack[0] == grammar.start():
##        return True
##    else:
##        for p in grammar.productions():
##            rhs = list(p.rhs())
##            n = len(rhs)
##            if stack[-n:] == rhs:
##                newstack = stack[0:-n]
##                newstack.append(p.lhs())
##                if recognize(grammar, newstack, rwords, trace):
##                    return True
##        if not len(rwords) == 0:
##            newstack = stack[:]
##            newstack.append(rwords[0])
##            if recognize(grammar, newstack, rwords[1:], trace):
##                return True
##    return False

def recognize(grammar, stack, rwords, trace):
    """SR-recognize rwords according to grammar and current stack.

    grammar - a CF-PSG of the nltk-format
    stack - a list of symbols where each symbol is either
        a terminal of type str or unicode, or
        a non-terminal of type nltk-nonterminal
    rwords - a list of remaining words, each of type str or unicode
    trace - 0, 1 or 2. Default is 0.

    Try to recognize the rwords by two operations
        - shifting words from rwords to stack
        - reducing the stack
     Return True on first success.
     """
    if trace > 0:
        for symbol in stack:
            print symbol,
        if len(stack) == 0: print "#",
        print " <> ",
        for word in rwords:
            print word,
        if len(rwords) == 0: print "#",
        print " "
    if rwords == [] and len(stack) == 1 and stack[0] == grammar.start():
        # Success!
        return True
    else:
        for p in grammar.productions():
            # Try all possible reductions in turn.
            rhs = list(p.rhs())
            n = len(rhs)
            if stack[-n:] == rhs:
                # Reduce!
                if trace > 1:
                    print "Reduce:", p, "\t",  
                newstack = stack[0:-n]
                # Make a copy to avoid conflicts.
                newstack.append(p.lhs())
                if recognize(grammar, newstack, rwords, trace):
                    return True
                if trace > 1:
                    print "Backtrack:", p, "\t",
                    for symbol in stack:
                        print symbol,
                    if len(stack) == 0: print "#",
                    print " <> ",
                    for word in rwords:
                        print word,
                    if len(rwords) == 0: print "#",
                    print " "
        if not len(rwords) == 0:
            # Reduce first!
            for p in grammar.productions():
                rhs = list(p.rhs())
                if rhs[0] == rwords[0]:
                    # Then shift!
                    newstack = stack[:]
                    newstack.append(p.lhs())
                    # Make a copy to avoid conflicts.
                    if trace > 1:
                            print "Shift: ", rwords[0], " -> ", p.lhs(), "\t",
                    if recognize(grammar, newstack, rwords[1:], trace):
                        return True
                    if trace > 1:
                        print "Backtrack, shift:", rwords[0], "\t",
                        for symbol in stack:
                            print symbol,
                        if len(stack) == 0: print "#",
                        print " <> ",
                        for word in rwords:
                            print word,
                        if len(rwords) == 0: print "#",
                        print " "
    return False


def timing(expression):
    x = time.time()
    eval(expression)
    print time.time() - x
    

def demo():
    """A demonstration."""

    grammar = nltk.CFG.fromstring("""
    S -> NP VP
    VP -> V NP | V NP PP
    PP -> P NP
    V -> 'saw' | 'ate' | 'walked'
    NP -> PN | Det N | Det N PP | 'I'
    PN -> 'John' | 'Mary'
    N -> 'dog' | 'man' | 'telescope' | 'park'
    P -> 'in' | 'by' | 'on' | 'with'
    Det -> 'a' | 'an' | 'the' | 'my'
    """)
    print "Grammar rules:"
    for prod in grammar.productions():
        print prod
    sent = "Mary saw a man in the park"
    print "\nRecognition with trace = 1, of the sentence:"
    print sent, "\n"
    print sr_recognize(grammar, sent.split(),1)
    print "\n\nRecognition with trace = 2, of the sentence:"
    print sent, "\n"
    print sr_recognize(grammar, sent.split(),2)
