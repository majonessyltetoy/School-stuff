##//////////////////////////////////////////////////////
##  Shift-Reduce Parser
##  INF2820 V2016
##  Jan Tore Loenning
##//////////////////////////////////////////////////////"""

"""
A simple Shift-Reduce parser.

This parser checks all possible parses and
returns a list of all the trees.
(In contrast to nltk.ShiftReduceParser() which does not find all parses.)

The parser takes the grammar as an argument.
The grammar is an nltk grammar.

This version should accept grammars where the right-hand side
of a rule is a mixture of terminals and non-terminals.
The price for this flexibility is less efficiency,
i.e. it shifts many times where a reduction is mandatory.

There are three levels of tracing determined by the optional
*trace* argument:
0 - default - no tracing
1 - prints out the value of the two buffers as they change
2 - prints the value of the buffers and reports the operations.

There are two varaints of the parser:
sr_parse() which simply return the trees.
all_trees() a wrapper around sr_parse which instead prettyprints the trees.

A grammar is loaded like
g1 = nltk.data.load('file:../grammar1.cfg'), or simpler
g1 = load('../gramar1.cfg') which facilitates auto-completation when available.


"""


from __future__ import division
import nltk
from nltk.tree import Tree
import time

def load(filename):
    """Load <filename> into nltk.data.load."""
    address='file:'+filename
    return nltk.data.load(address)


def all_trees(grammar, words, trace = 0):
    """Parse the words according to grammar. Print trees."""
    parses = sr_parse_modified(grammar, words, trace)
    print "\nNumber of different trees:", len(parses), "\n"
    for p in parses:
        p.pretty_print()
        print "\n"

def sr_parse(grammar, words, trace = 0):
    return parse(grammar,[],words, trace)
        
def sr_parse_modified(grammar, words, trace = 0):
    return parse_modified(grammar,[],words, trace)


## Old parse
def parse(grammar, stack, rwords, trace):
    """SR-parse and return all trees compatible with rwords, stack and grammar.

    grammar - a CF-PSG of the nltk-format
    stack - a list where each element is either
        a terminal of type str, or
        a tree of type nltk-Tree
    rwords - a list of remaining words, each of type str
    trace - 0, 1 or 2. Default is 0.

    Try to recognize the rwords by two operations
        - shifting words from rwords to stack
        - reducing the stack
     Return True on first success.
     """
    trees = []
    # A call on parse should always return a list.
    # If there is no succesful parses it returns the empty list.
    if trace > 0:
        for element in stack:
            if isinstance(element, basestring):
                print element,
            else:
                print element.label(),
        print "<>",
        for word in rwords:
            print word,
        if len(rwords) == 0: print "#",
        print " "
    if (rwords == [] and
        len(stack) == 1 and
        not isinstance(stack[0], basestring) and
        stack[0].label() == grammar.start().symbol()
        ):
        # A successful parse!
        if trace > 0:
            print "\nFound analysis:\n\n", stack[0], "\n"
        trees = [stack[0]]
        return trees
    else:
        for p in grammar.productions():
            # Try all rules.
            # Reduce if top of stack equals rule.rhs .
            rhs = list(p.rhs())
            n = len(rhs)
            for i, symbol in enumerate(rhs):
                if not isinstance(symbol, basestring):
                    # a non-terminal
                    rhs[i] = symbol.symbol()          
            top = stack[-n:]
            for i, node in enumerate(top):
                if not isinstance(node, basestring):
                    # not a leaf node
                    top[i] = node.label()
            if top == rhs:
                # Reduce!
                if trace > 1:
                    print "Reduce:", p, "\t",
                newstack = stack[0:-n]
                # Make a copy of the stack
                # to avoid conflicts with constructed trees.
                newstack.append(Tree(p.lhs().symbol(), stack[-n:]))
                newtrees = parse(grammar, newstack, rwords, trace)
                trees = trees + newtrees
                if trace > 1:
                    print "Backtrack:", p, "\t",
                    for element in stack:
                        if isinstance(element, basestring):
                            print element,
                        else:
                            print element.label(),
                    if len(stack) == 0: print "#",
                    print " <> ",
                    for word in rwords:
                        print word,
                    if len(rwords) == 0: print "#",
                    print " "
        if not len(rwords) == 0:
            # Shift!
            word = rwords[0]
            if trace > 1:
                print "Shift:", word, "\t\t",
            newstack = stack[:]
            # A new copy of the stack to be able to build several trees.
            newstack.append(word)
            newtrees = parse(grammar, newstack, rwords[1:], trace)
            trees = trees + newtrees
            if trace > 1:
                print "Backtrack, shift:", rwords[0], "\t",
                for element in stack:
                    if isinstance(element, basestring):
                        print element,
                    else:
                        print element.label(),
                if len(stack) == 0: print "#",
                print " <> ",
                for word in rwords:
                    print word,
                if len(rwords) == 0: print "#",
                print " "
    return trees

# New modified parse
def parse_modified(grammar, stack, rwords, trace):
    trees = []
    if False:
        for element in stack:
            if isinstance(element, basestring):
                print element,
            else:
                print element.label(),
        print "<>",
        for word in rwords:
            print word,
        if len(rwords) == 0: print "#",
        print " "
    if (rwords == [] and
        len(stack) == 1 and
        not isinstance(stack[0], basestring) and
        stack[0].label() == grammar.start().symbol()
        ):
        if trace > 0:
            print "\nFound analysis:\n\n", stack[0], "\n"
        trees = [stack[0]]
        return trees
    else:
        for p in grammar.productions():
            rhs = list(p.rhs())
            n = len(rhs)
            for i, symbol in enumerate(rhs):
                if not isinstance(symbol, basestring):
                    rhs[i] = symbol.symbol()          
            top = stack[-n:]
            for i, node in enumerate(top):
                if not isinstance(node, basestring):
                    top[i] = node.label()
            if top == rhs:
                if trace > 1:
                    print "Reduce:", p, "\n",
                newstack = stack[0:-n]
                newstack.append(Tree(p.lhs().symbol(), stack[-n:]))
                newtrees = parse_modified(grammar, newstack, rwords, trace)
                trees = trees + newtrees
                if trace > 1:
                    print "Backtrack:", p, "\t",
                    for element in stack:
                        if isinstance(element, basestring):
                            print element,
                        else:
                            print element.label(),
                    if len(stack) == 0: print "#",
                    print " <> ",
                    for word in rwords:
                        print word,
                    if len(rwords) == 0: print "#",
                    print " "
        if not len(rwords) == 0:
            # Find productions that match the first word
            for p in grammar.productions():
                rhs = list(p.rhs())
                if rhs[0] == rwords[0]:
                    word = p.lhs()
                    if trace > 1:
                        print "Shift:", word, "\t\n",
                    newstack = stack[:]
                    newstack.append(Tree(word.symbol(), [rwords[0]]))
                    newtrees = parse_modified(grammar, newstack, rwords[1:], trace)
                    trees = trees + newtrees
                    if trace > 1:
                        print "Backtrack, shift:", rwords[0], "\t",
                        for element in stack:
                            if isinstance(element, basestring):
                                print element,
                            else:
                                print element.label(),
                        if len(stack) == 0: print "#",
                        print " <> ",
                        for word in rwords:
                            print word,
                        if len(rwords) == 0: print "#",
                        print " "
    return trees


def timing(expression):
    x = time.time()
    eval(expression)
    print time.time() - x


def demos(trace=2):
    """A demonstration. Can be run with different levels of tracing"""

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
    print "\nParsing with trace =", trace, ", of the sentence:"
    print sent, "\n"
    print all_trees(grammar, sent.split(),trace)
    


