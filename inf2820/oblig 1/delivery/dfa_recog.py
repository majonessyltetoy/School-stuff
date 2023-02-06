"""
An implemention of deterministic finite automata following Jurafsky&Martin.

A DFA is represented by a record type object and may be entered in
an interactive session as in

f = DFA()
f.start = 0
f.finals.append(4)
f.edge[(0,'b')] = 1
f.edge[(1,'a')] = 2
f.edge[(2,'a')] = 3 
f.edge[(3,'a')] = 3
f.edge[(3,'!')] = 4

The recognition procedures takes a tape and a dfa and checks whether
the dfa recognizes the tape.

The goal is to represent J&Ms algorithm from fig. 2.13 as closely
as possible, and to be compact.

Better programming practice would
- place the recognition function as a method in the class DFA.
- include routines for constructing and modifying the DFA,
- and protecting the DFA itself from direct modification

"""


class DFA:
    """A record for representing one DFA

    It is intended to have the following attrbutes:
    start - an atom, string, or number representing a state
    finals - a list of states
    edge - a dictionary taking a pair of a state and a symbol
            producing a state
    """
    def __init__(self):
        """Initialize edge to be a dictionary and finals to be a list."""
        self.start = 0
        self.edge = {}
        self.finals = []
            

def drec(tape, dfa, trace=0):
    """Return "accept" if fa recognizes tape, otherwise return "rejects"

    A direct implementation of J&Ms algorithm from fig. 2.13.
    The tape can be represented as a string or a list of symbols.
    If all the symbols in the alphabet of the formal language being
    described are characters/atomic strings,
    the tape may alternatively be represented as 
    e.g. ['b','a','a','a','!'] or 'baaa!'    
    By providing a number greater than 0 as the third argument
    one can see the behavior of the automaton on the tape, e.g.,
    drec("baaaa", mydfa, 1)
    """
    index = 0
    state = dfa.start
    while True:
        # This runs until a return statement is executed.
        if trace > 0: print "state:", state, " tape:", tape[index:]
        if index == len(tape): # End of input reached
            if state in dfa.finals:
                return "accept"
            else:
                return "reject"    
        elif not (state, tape[index]) in dfa.edge.keys():
            # No defined transition from this configuration
            return "reject"
        else:
            # Change state and move one step.
            state = dfa.edge[(state,tape[index])]
            index += 1
            



