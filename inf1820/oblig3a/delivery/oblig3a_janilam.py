#encoding: utf-8

# making python2 act like python3
from __future__ import division
from __future__ import print_function

import nltk
from nltk.corpus import brown
from nltk.corpus import conll2000
from nltk.probability import FreqDist


print("\n\n________________________________")
print("Oppgave 1 Betinget sannsynlighet\n")

# We're using universal tagset to compose the word class
# derivatives. e.g. JJR, JJT, VBD, VBG, etc.
# If we used the standard brown tagset we'd have got "new" 
# as the most frequent adjective instead of "other"
cfd_tags = nltk.ConditionalFreqDist((tag, word.lower()) for (word, tag) in brown.tagged_words(tagset='universal'))
cfd_words = nltk.ConditionalFreqDist((word.lower(), tag) for (word, tag) in brown.tagged_words(tagset='universal'))

print("Det mest frekvente adjektivet er:", cfd_tags['ADJ'].max())

print('Ordet "time" forekommer', end=' ')
for (tag, count) in cfd_words['time'].items():
    if tag == 'NOUN':
        print(count, "ganger som substantiv,", end=' ')
    if tag == 'VERB':
        print(count, "ganger som verb,", end=' ')
        
print("\nDet mest frekvente adverbet er:", cfd_tags['ADV'].max(), "\n")


bigrams = nltk.bigrams((tag for (_, tag) in brown.tagged_words()))
cfd_bigrams = nltk.ConditionalFreqDist(bigrams)

print("Den vanligste taggen etter et substantiv er:", cfd_bigrams['NN'].max())
print("Bigrammet 'DT JJ' forekommer", cfd_bigrams['DT']['JJ'], "ganger\n")


# We're continue using universal tagset lest we'd get the wrong answer
cpd_brown = nltk.ConditionalProbDist(cfd_tags, nltk.MLEProbDist)
print("Det mest sannsynlige verbet er:", cpd_brown['VERB'].max())
# There is a typo in the assignment; P(JJ|DT) != P(NN|DT)
# It should probably be:
# "Hva er P(JJ|DT), sannsynligheten for adjektivtagg etter en bestemmertagg?"
# But let's not presume anything and do them both
cpd_tags = nltk.ConditionalProbDist(cfd_bigrams, nltk.MLEProbDist)
print("P(JJ|DT):", str(round(cpd_tags['DT'].prob('JJ'), 4) * 100) + "%")
print("P(NN|DT):", str(round(cpd_tags['DT'].prob('NN'), 4) * 100) + "%")



print("\n\n_____________________")
print("Oppgave 2 HMM-tagging\n")

print("""\
Sannsynligheten i den flertydige settningen:
"I saw her duck"\
""")
print('"hennes and":', str(round(cpd_tags['PP$'].prob('NN'), 3) * 100) + "%")
print('"henne dukke":', str(round(cpd_tags['PPO'].prob('VB'), 3) * 100) + "%")

print('\nDen mest sannsynlige tolkningen er "hennes and"')



print("\n\n__________________")
print("Oppgave 3 Chunking\n")

training = conll2000.chunked_sents('train.txt', chunk_types=['NP'])
test = conll2000.chunked_sents('test.txt', chunk_types=['NP'])

grammar = """
NP:
    {<DT|PRP\$|POS>?<RB.*>?<\#>*<\$>*<CD|JJ.*>*<NN.*>+<CD>*(<CC>*<NN.*>+)?}
    {<DT>?<PRP>}
    {<RB>?<\$>+<CD>+(<TO><\$>+<CD>+)?}
    {<RB>(<JJ><IN>)?<\#>+<CD>+}
    {<EX|WP|WDT>}
"""
# The topmost regex chunker account for about 88% accuracy, the second and third
# got 2% and the two last are just below 1% as a whole. Since the complexity is
# decending, we'll start with the bottom and work our way up.
#
#
# The last regex is a simple tag that searches for a wh-determiner, wh-pronoun
# and an existential. It only matches a single tag.
#
#
# The next regex was made for a single chunk (as/RB little/JJ as/IN
# #/# 1.3/CD billion/CD). However there are a significant amount of chunks that
# has the structure {RB # CD CD} that is why the JJ and IN are optional.
#
#
# Third from the top is a regex that searches for "$" followed by one or more
# cardinal numbers. There is also an optional adverb in the beginning and at
# the end an optional {TO $ CD}.
#
#
# The second regex is pretty simple too, it's looking for a single personal
# pronoun, if a determiner precedes it, it is also included.
#
#
# The top regex looks daunting, but it only need one noun to match, all the other
# tags are just optional. Let's break it down. To start off we can have either a
# determiner, posseive pronoun, or possesive ending, these cannot come in succession
# therefore they have to be alternatives. Next is an adverb tag, this can be any
# adverb form. The pound and dollar sign occure in rare instances. CD and JJ can
# occure in {CD JJ} and {JJ CD} sequence, hence the conditional "|" between them.
# The noun tag is the core of NP chunking. It has to be on or more of any noun form.
# Once more an cardinal number tag, for when a number succeed the noun. And lastly
# the coordinating conjunction with a noun after it, when there is an conjuction
# there have to be a noun after it, otherwise it's not part of the NP.

cp = nltk.RegexpParser(grammar)

print("Training settet fikk:", str(round(nltk.chunk.util.accuracy(cp, training), 3) * 100) + "%")
print("Test settet fikk:", str(round(nltk.chunk.util.accuracy(cp, test), 3) * 100) + "%")
