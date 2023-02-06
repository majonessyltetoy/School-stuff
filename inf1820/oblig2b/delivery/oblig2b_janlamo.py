#encoding: utf-8

# making python2 act like python3
from __future__ import division
from __future__ import print_function

import nltk



print("\n\n______________________________________")
print("Oppgave 1 Tilfeldig samsvar i annotering\n")

# The probability that Arild and Birgitte picks the same
# tag is an independent event. First we need to find the
# probabilities that they pick the same tag, for all the
# tags. Then add them up to find the probability for them
# picking the the same tag out of all the tags
# 
# P(they pick same tag) = P(A)*P(A) + P(B)*P(B) + P(C)*P(C)
# where P(A) = 55%  P(B) = 25% and P(C) = 20%

probability = 0.55**2 + 0.25**2 + 0.20**2
print("Sannsynligheten for at Arild og Birgitte velger \
samme tag er:", str(probability * 100) + "%")



print("\n\n______________________________________")
print("Oppgave 2 Zipfianske distribusjoner\n")

frequency = [round(63287/x) for x in range(1,56000)]
# the print function under returns the frequency of all the
# 56000 types in the fictional corpus, but since it will flood
# the output it has been commented out.
#print(frequency)

print(frequency.count(1), "ord har frekvensen 1.")


print("\n\n______________________________________")
print("Oppgave 3 Flertydighet og ordklassetagger\n")

print("LACK OF BRAINS HINDERS RESEARCH")
print("LACK/NN OF/IN BRAINS/NNS HINDERS/VBZ RESEARCH/NN")
print("Flertydigheten stammer fra at 'mangelen på hjerner' \
kan være både at de mangler hjerner til å forske på og at \
forskerne selv mangler hjerner.")

"""
        S
      /    \
     /       \
    /         \
   NP          VP
  / | \       /   \
NN IN  NNS   VBZ  NN

"""


print("\n")

print("IRAQI HEAD SEEKS ARMS")
print("IRAQI/NN HEAD/NN SEEKS/VBZ ARMS/NNS")
print("Et Irakisk hode some ser etter armer, eller \
det irankse overhode søker etter våpen.")

"""
        S
       /  \
      /    \ 
     /      \
    N       VP
   /  \    /   \
 NN   NN   VBZ  NNS

"""


print("\n")

print("STOLEN PAINTING FOUND BY TREE")
print("STOLEN/JJ PAINTING/NN FOUND/VBD BY/DT TREE/NN")
print("Er det stjålene maleriet funnet av eller ved treet.")

"""
          S
        /    \
      /  \     \
     NP   \      NP
   /   \   \      | \   
  JJ   NN  VBD   DT  NN

"""



print("\n\n______________________________________")
print("Oppgave 4 Tagging med regulære uttrykk\n")

# Regular expression patterns
patterns = [                               
    (r'.*ing$', 'VBG'),
    (r'.*ed$', 'VBD'),
    (r'.*es$', 'NNS'), # Changed this from VBZ, to improve evaluation rate.
                       # Example; eyes, horses, times 
    (r'.*ould$', 'MD'),
    (r'.*\'s$', 'NN$'),
    (r'.*s$', 'NNS'),
    (r'^-?[0-9]+(.[0-9]+)?$', 'CD'),
    (r'^be$', 'BE'), # Tags the word 'be'

    (r'^is$', 'BEZ'), # Tags the word 'is'

    (r'^are$', 'BER'), # Tags the word 'are'

    (r'^was$', 'BEDZ'), # Tags the word 'was'

    (r'^were$', 'BED'), # Tags the word 'were'

    (r'.*able$', 'JJ'), # Find words that ends with 'able'
                        # Example; able, unable, *table

    (r'.*ly$', 'RB'), # Find words that ends with 'ly'
                      # Example; only, really, probably

    (r'.*ever$', 'RB'), # Find words that ends with 'ever'
                        # Example; never, ever, *however

    (r'.*ieve$', 'VB'), # Find words that ends with 'ieve'
                        # Example; believe, *reprieve

    (r'^(up|out|off)$', 'RP'), # Tags a few particle adverb

    (r'^(of|in|for|by|to|on|at|over|after)$', 'IN'), # Tags some preposition words

    (r'.*ost$', 'RB'), # Find words that ends with 'ost'
                       # Example; almost, *most, *lost

    (r'^wh.*', 'WDT'), # Find words that starts with 'wh'
                       # Example; *when, what, which

    (r'.*\'ll$', 'PPS+MD'), # Find words that ends with "'ll"
                            # Example; I'll, we'll, he'll

    (r'^(s?he|it)$', 'PPS'), # Tags 'she', 'he' and it

    (r'^(the|an?)$', 'AT'), # Tags 'the' 'a' and 'an'

    (r'^th', 'DT'), # Finds words that starts with 'th'
                    # *they, that, this

    (r'^\,$', ','), # Tags the symbol ','
    (r'^\W$', '.'), # Tags every symbol as '.'
                    # Example; ?, ., !
    (r'.*', 'NN')
]


regexp_tagger = nltk.RegexpTagger(patterns)

# Evaluation texts
adventure = nltk.corpus.brown.tagged_sents(categories='adventure')
fiction = nltk.corpus.brown.tagged_sents(categories='fiction')
adventure_text = [[(word.lower(), tag) for (word, tag) in sent] for sent in adventure]
fiction_text = [[(word.lower(), tag) for (word, tag) in sent] for sent in fiction]

print("adventure korpuset fikk:", str(round(regexp_tagger.evaluate(adventure_text), 3) * 100) + "% nøyaktighet")
print("fiction korpuset fikk:  ", str(round(regexp_tagger.evaluate(fiction_text), 3) * 100) + "% nøyaktighet\n")

test_setninger = """\
Zenith Data Systems Corp. , a subsidiary of Zenith Electronics Corp. , \
received a $ 531 million Navy contract for software and services of \
microcomputers over an 84-month period .
Pacific First Financial Corp. said shareholders approved its acquisition \
by Royal Trustco Ltd. of Toronto for $ 27 a share , or $ 212 million . 
If he had married her , he'd have been asking for trouble .
All this has not , however , been an unmixed blessing .\
"""

lower_test_setninger = [word.lower() for word in test_setninger.split()]
print(regexp_tagger.tag(lower_test_setninger))


"""
[('zenith', 'NN'), ('data', 'NN'), ('systems', 'NNS'), ('corp.', 'NN'), (',', ','), ('a', 'AT'), ('subsidiary', 'NN'), ('of', 'IN'), ('zenith', 'NN'), ('electronics', 'NNS'), ('corp.', 'NN'), (',', ','), ('received', 'VBD'), ('a', 'AT'), ('$', '.'), ('531', 'CD'), ('million', 'NN'), ('navy', 'NN'), ('contract', 'NN'), ('for', 'IN'), ('software', 'NN'), ('and', 'NN'), ('services', 'NNS'), ('of', 'IN'), ('microcomputers', 'NNS'), ('over', 'IN'), ('an', 'AT'), ('84-month', 'NN'), ('period', 'NN'), ('.', '.'), ('pacific', 'NN'), ('first', 'NN'), ('financial', 'NN'), ('corp.', 'NN'), ('said', 'NN'), ('shareholders', 'NNS'), ('approved', 'VBD'), ('its', 'NNS'), ('acquisition', 'NN'), ('by', 'IN'), ('royal', 'NN'), ('trustco', 'NN'), ('ltd.', 'NN'), ('of', 'IN'), ('toronto', 'NN'), ('for', 'IN'), ('$', '.'), ('27', 'CD'), ('a', 'AT'), ('share', 'NN'), (',', ','), ('or', 'NN'), ('$', '.'), ('212', 'CD'), ('million', 'NN'), ('.', '.'), ('if', 'NN'), ('he', 'PPS'), ('had', 'NN'), ('married', 'VBD'), ('her', 'NN'), (',', ','), ("he'd", 'NN'), ('have', 'NN'), ('been', 'NN'), ('asking', 'VBG'), ('for', 'IN'), ('trouble', 'NN'), ('.', '.'), ('all', 'NN'), ('this', 'NNS'), ('has', 'NNS'), ('not', 'NN'), (',', ','), ('however', 'RB'), (',', ','), ('been', 'NN'), ('an', 'AT'), ('unmixed', 'VBD'), ('blessing', 'VBG'), ('.', '.')]
"""

print("""
The main drawback with regular expression tagging is that even though
you make rules for every conceivable word, it's still not going to be 
foolproof. Language is inherently ambigious. The text over is how the
the regexp tagger interpreted the sample text. It has some flaws, however
most of them could be corrected by extending the regular expression 
descriptions e.g 'million', 'said' and 'and' are tagged as NN even though
they should be CD, VBD and CC. Some defenitions were there, but they
still tagged wrong 'blessing'/VBG and 'its'/NNS, they should be NN and
PP$. These are shortcomings of regular expressions. As said we could
make rules until our faces were blue, but there'd still be errors.
""")


