#encoding: utf-8


from __future__ import print_function


def read_lex(lexicon, form_to_feat):
    infile = open(lexicon, 'r')
    line = infile.readline()
    while line:
        posts = line.strip().split(';')
        if posts[0] != 'Lexeme':
            form = posts[0].decode('utf-8')
            if len(posts) == 2:
                posts.append('no_syn_feat')
            if form in form_to_feat:
                form_to_feat[form] += [(posts[1],posts[2])]
            else:
                form_to_feat[form] = [(posts[1],posts[2])]
        line = infile.readline()
    infile.close()
    return form_to_feat

form_to_feat = {}

for lexicon in ['gramwords','gramwords2x','main','idiomwords','abbrevwords']:
    form_to_feat = read_lex(lexicon, form_to_feat)

def analyze(word):
    word = word.decode('utf-8')
    for i in range(0, len(form_to_feat[word])):
        feature = form_to_feat[word][i]
        print('morf', end=' ')
        print(feature[0], end='\t')
        print(feature[1])
