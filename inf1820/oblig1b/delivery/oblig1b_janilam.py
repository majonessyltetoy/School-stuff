import re

# Oppgave 1: Tostavelseord i "Anna Karenina"
print("Oppgave 1\n")

# Acquire file path from user input
file_path = str(input("write path to the text\n>> "))

# Open file and store in variable
text_file = open(file_path).read()

# Each word is transformed into a list
# with it's respective vowels, double
# vowels count as one object in the list.
vowel_list = [re.findall('[aeiou]{1,}', word, re.I) for word in text_file.split()]

# Count vowel in each word
tostavelseord = 0
for vowel_word in vowel_list:
	if len(vowel_word) == 2:
		tostavelseord += 1

# Print answer
print("Teksten inneholder: ", tostavelseord, " tostavelseord.")

print("Dekkes av uttrykkt, men er ikke tostavelseord: are, wife, house")
print("Dekkes ikke, men er tostavelseord: every, family")



# Oppgave 2 Regulære uttrykk for datouttrykk
print("\n-----------\n\nOppgave 2")

# Variables on days and month to improve
# readability and repeating parameters
days = '(mandag|tirsdag|onsdag|torsdag|fredag|lørdag|søndag)'
months = '(januar|februar|mars|april|mai|juni|juli|august|september|oktober|november|desember)'

# Fuction for "neste" and "forrige"
def neste_forrige(text):
 return re.search( r'(neste|forrige) ' + days, text).group()

# Function that finds a date string
def search_date(text):
 return re.search(days + r' \b([1-9]|[1-2][0-9]|3[01])\b ' + months + r', (19[0-9][0-9]|20[0-9][0-9])', text).group()

# Checking if the functions work
text = "Hei, vi skal svømme neste søndag, vil du bli med?"
print(neste_forrige(text))

text = "Husker du kampen forrige onsdag?"
print(neste_forrige(text))

text = "♫♪Når du en gang kommer, neste torsdag, vil du atter være min?♪♫"
print(neste_forrige(text))

text = "Vi skal ha et nytt milleniums fest søndag 31 desember, 1999 vil du bli med?"
print(search_date(text))

text = "Første søndag i advent er søndag 29 november, 2015 i år"
print(search_date(text))

text = "Marty McFly fra Back to the Future dro til onsdag 21 oktober, 2015"
print(search_date(text))

