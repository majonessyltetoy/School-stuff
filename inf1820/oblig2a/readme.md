This delivery has been corrected, and the grade is: 100/100 (passed)
Hei!

Som alltid - kjempebra oblig, alt riktig!

Da jeg kjørte koden din, kom jeg på at jeg glemte å svare deg på mail - jeg beklager det på det sterkeste!
En mulig måte å skrive ut setninger på:

brown_sents = nltk.corpus.brown.tagged_sents(categories="news")


def print_sent(sent):
    for w, t in sent:
        print w, # komma gjør at print ikke skriver ut et linjeskift
    print "\n"


# Itererer over mengden av ulike tagger for ordet 'to':
for p in ambposlst:
    for s in brown_sents:
        sl = [(s1.lower(), s2) for (s1, s2) in s]
        if (ambw, p) in sl:
            print ambw, p, ": ",
            print_sent(s)
            break
Hvis du har spørsmål om rettingen, ta kontakt på natalsm@ifi.uio.no

Lykke til videre!

Natalia
