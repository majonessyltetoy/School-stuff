#encoding: utf-8


from __future__ import print_function

execfile('oppgave3c.py')


def read_lex(lexicon, lex_nr, form_to_lex, form_to_feat):
    infile = open(lexicon, 'r')
    line = infile.readline()
    while line:
        posts = line.strip().split(';')
        if posts[0] == 'Lexeme':
            lex_nr += 1
        else:
            lex_ident = 'x'+str(lex_nr)
            form = posts[0].decode('utf-8')
            if len(posts) == 2:
                posts.append('no_syn_feat')
            if form in form_to_lex:
                form_to_lex[form] += [lex_ident]
                form_to_feat[form] += [(posts[1],posts[2])]
            else:
                form_to_lex[form] = [lex_ident]
                form_to_feat[form] = [(posts[1],posts[2])]
        line = infile.readline()
    infile.close()
    return lex_nr, form_to_lex, form_to_feat

lex_nr = -1
form_to_lex = {}
form_to_feat = {}

for lexicon in ['gramwords','gramwords2x','main','idiomwords','abbrevwords']:
    lex_nr,form_to_lex,form_to_feat = read_lex(lexicon,lex_nr,form_to_lex,form_to_feat)

def analyze(word):
    word = word.decode('utf-8')
    for i in range(0, len(form_to_lex[word])):
        features = form_to_feat[word][i]
        lemma = lex_to_lemma(form_to_lex[word][i], features[0].split(',')[0])
        print(lemma, end='\t')
        print('morf', end=' ')
        print(features[0], end='\t')
        print(features[1])
