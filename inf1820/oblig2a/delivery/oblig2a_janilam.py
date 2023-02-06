import nltk
brown_news = nltk.corpus.brown.tagged_words(categories="news")
brown_sents = nltk.corpus.brown.tagged_sents(categories="news")

# Oppgave 1 Ordfrekvens og taggfrekvens
print("\n\nOppgave 1")
brown_tag = {tag: 0 for (word, tag) in brown_news}
brown_word = {word.lower(): 0 for (word, tag) in brown_news}
for word, tag in brown_news:
    brown_tag[tag] += 1
    brown_word[word.lower()] += 1

once_count = 0
for word in brown_word:
    if brown_word[word] == 1:
        once_count += 1
        
print("Den mest frekvente ordklassen i nyhetsdelen er:", sorted(brown_tag.items(), key=lambda i: i[1], reverse=True)[0][0])
print("Antall ord som kun forekommer en gang: ", once_count)

        
# Oppgave 2 Flertydighet
print("\n\nOppgave2")
brown_dict = {word.lower(): set() for (word, tag) in brown_news}
for word, tag in brown_news:
    brown_dict[word.lower()].add(tag)
flertydig = 0
for word in brown_dict:
    if len(brown_dict[word]) > 1:
        flertydig += 1
        
print("Antall flertydige ord:", flertydig)

max_tag = max(brown_dict.items(), key=lambda dict_value: len(dict_value[1]))
print("Ordet med flest tagger er:", '"' + max_tag[0] + '"', "med", len(max_tag[1]), "tagger")


# Oppgave 3 Finne spesifikke eksempler
print("\n\nOppgave3")

for sent in brown_sents:
    for word, tag in sent:
        if word.lower() == max_tag[0] and tag in max_tag[1]:
            max_tag[1].remove(tag)
            print("\n" + tag + ":")
            for w, t in sent:
                print(w, end=' ')
            print("\n")


# Oppgave 4 Fordelingen av maskuline kontra feminine possessive pronomer
print("\n\nOppgave 4")
male_count = 0
female_count = 0
pronomer = {"male":["his"], "female":["her", "hers"]}
for word, tag in brown_news:
    if tag == "PP$" or tag == "PP$$":
        if word.lower() in pronomer["male"]:
            male_count += 1
        elif word.lower() in pronomer["female"]:
            female_count += 1
print("maskulin, feminin forhold;", str(male_count) + ":" + str(female_count))
