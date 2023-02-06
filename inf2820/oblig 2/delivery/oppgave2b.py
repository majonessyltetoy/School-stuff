#encoding: utf-8

import nltk
from __future__ import print_function

#### CODE ####
# Note: this code only work in Python2. Python3 use unicode by default thereby
# avert this whole issue.

norsk_stemmer = nltk.SnowballStemmer('norwegian')

file = open('nor2')
nor2 = file.read().split()
file.close()

nor2_stem = [norsk_stemmer.stem(t[:-1].decode('utf-8')) + '.' \
    if t.endswith('.') else norsk_stemmer.stem(t.decode('utf-8')) for t in nor2]

for s in nor2_stem:
    if s.endswith('.'):
        print(s, end='\n')
    else:
        print(s, end=' ')