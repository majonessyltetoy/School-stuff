"""
An implementation of nondeterministic finite automata.

As is, it processes NFAs without abreviations or epsilon transitions.

An NFA can be read in from the Python shell by:

>>> nfa1 = NFA(
start=0,
finals=[4],
edges=[
(0,'b',1),
(1,'a',2),
(2,'a',3),
(3,'a',3),
(3,'!',4)
]) 

There is a subclass of NFA which lets one read the NFA from file.
The file should be on the same form as 'sheep.nfa' or 'template.nfa'
>>> dfa2 = NFAFromFile('sheep.nfa')
The reading from file includes abreviations as in 'template.nfa', but the
recognition procedures cannot handle abreviations (nor epsilons) so far.
The file reading is not robust; the files must be properly formatted. 


"""


class NFA:
    """Non-deterministic Finite Automaton

    Class for reprsenting and constructing and NFA.
    Common superclass for reading the automaton from file or entering
    it manually.
    By default it reads from terminal.
    Reading from terminal does not include abreviations.
    Use NFAFromFile class to read from file.
    """
    
    def __init__(self,start=None, finals=None, edges=None):
        """Read in an automaton from python shell"""
        self.start = start
        self.edges = edges
        self.finals = finals
        self.abrs = {}

def nrec(tape, nfa, trace=0):
    """Recognize in linear time similarly to transform NFA to DFA """
    index = 0
    states = [nfa.start]
    while True:
        if trace > 0: print " Tape:", tape[index:], "   States:", states
        if index == len(tape): # End of input reached
            successtates = [s for s in states
                              if s in nfa.finals]
            # If this is nonempty return True, otherwise False.
            return len(successtates)> 0
        elif len(states) == 0:
            # Not reached end of string, but no states.
            return False
        else:
            # Calculate the new states.
            states = set([e[2] for e in nfa.edges
                               if e[0] in states and
                                  tape[index] == e[1]
                                    ])
            index += 1 


class NFAFromFile(NFA):
    """Non-deterministic Finite Automaton

    Read a NFA description from file.
    Build the automaton
    """
    
    def __init__(self, fsa_file):
        """Read in an automaton on the format shown in template.nfa"""
        self.start = None
        self.edges = []
        self.finals = []
        self.abrs = {}
        infile = open(fsa_file, 'r')
        line = infile.readline()
        # self.abrs = {}
        while line:
            if line[0:5] == 'START':
                line = infile.readline()
                self.start = line.split()[0]
                # The first word on the line
                line = infile.readline()
            if line[0:5] == 'FINAL':
                line = infile.readline()
                while len(line)>1:
                    # The final states may be at more than one line
                    # If the next line isn't blank there are more final states
                    symbols = line.split()
                    for word in symbols:
                        self.finals.append(word.strip(','))
                    # The finals may (or may not) be separated by commas.
                    line = infile.readline()
            if line[0:5] == 'EDGES':
                line = infile.readline()
                while len(line)>1:
                    # Each nonblank line contains one edge.
                    triple = line.split()
                    triple[1] = triple[1].strip("'")
                    # Stripping off quotes from the edge symbol.
                    edge = tuple(triple)
                    # Each edge is represented as a triple.
                    self.edges.append(edge)
                    # The edges are stored in the list 'edges'.
                    line = infile.readline()
            if line[0:4] == 'ABRS':
                line = infile.readline()
                while len(line)>1:
                    words = line.split()
                    if words[0][-1]== ':':
                        # First word ends with :
                        # New abreviation
                        abr = words[0][:-1]
                        # Strip the :
                        self.abrs[abr] = []
                        words = words[1:]
                    for word in words:
                        self.abrs[abr].append(word.strip(",'"))
                    line = infile.readline()
            while line and len(line) == 1:
                line = infile.readline()
                # Ignore blank lines.
        infile.close()
        # Always close the file!
        







