#encoding: utf-8

# No need to reinvent the wheel, we use provided lexicon parser
execfile('read_lexicon.py')
sclex = ScaryLexicon()

def lex_to_lemma(lexem, pos=''):
    pos_count = {} # pos: part of speech
    word_forms = []

    if pos == '':
        # Find the most common part of speech
        for w in sclex.lexemes[lexem]:
            w = sclex.words[w].morf_feat.split(',')[0]
            if w in pos_count:
                pos_count[w] = pos_count[w] + 1
            else:
                pos_count[w] = 1
        pos = max(pos_count, key=pos_count.get)


    # Filter out words that are definitely not lemmas
    for w in sclex.lexemes[lexem]:
        feature = sclex.words[w].morf_feat.split(',')
        if pos in feature:
            if pos == 'N' or pos == 'Adj':
                if 'indef' in feature:
                    word_forms.append(sclex.words[w].form)
            if pos == 'V':
                if 'inf' in feature:
                    word_forms.append(sclex.words[w].form)

    word_forms = sorted(word_forms, key=len)
    # The shortest word is probably the lemma
    lemma = word_forms[0]

    # Choose lemma based on pos type and ending

    if pos == 'N':
        if lemma.endswith('a') and lemma[:-1] + 'e' in word_forms:
            return lemma[:-1] + 'e'

    elif pos == 'V':
        if lemma + 'e' in word_forms and not lemma.endswith('e'):
            return lemma + 'e'
        elif lemma.endswith('o') and lemma[:-1] + u'å' in word_forms:
            return lemma[:-1] + u'å'
        elif lemma.endswith('a') and lemma[:-1] + 'i' in word_forms:
            return lemma[:-1] + 'i'

    elif pos == 'Adj':
        if lemma.endswith('a') and lemma[:-1] + 'e' in word_forms:
            return lemma[:-1] + 'e'
        if lemma.endswith('ne') and lemma[:-2] + 'en' in word_forms:
            return lemma[:-2] + 'en'

    return lemma