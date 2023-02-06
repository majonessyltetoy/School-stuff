This delivery has been corrected, and the grade is: 10/10 (passed)
Tilbakemelding på innlevering 3a
Se også løsningsforslaget.

1 Prosedyrer for bedre prosedyrer (5 av 5 poeng)
a)
Memoisering av prosedyrer fungerer fint!

b)
En super implementasjon av mem! Flott at den er helt generell og kun bruker lokal tilstand!

Et lite tips for å gjøre koden litt mer kompakt (og få fjernet begin) er at koden

(if (lookup vars cache)
    (lookup vars cache)
    (begin
      (let ((result (apply proc vars)))
        (insert! vars result cache)
        result)))
kan byttes ut med

(or (lookup vars cache)
    (let ((result (apply proc vars)))
      (insert! vars result cache)
      result))
Jeg vil også si at det er litt unødvendig å lage prosedyren dispatch i dette tilfellet. Prosedyren fungerer vel så bra uten den veldig enkle prosedyren. Vi pleier å bruke "dispatch-mønsteret" når vi returnerer dispatch-prosedyren, her kunne dere bare hatt cond-koden som selve prosedyre-kroppen i mem.

c)
Fint!

d)
Supert!

For å være veldig pirkete skulle egentlig disse meldingene bli skrevet ut med display, ikke returnert som strenger, men det er ikke noe jeg trekker for.

2 Strømmer (5 av 5 poeng)
a)
Bra!

b)
Utmerket!

Jeg anbefaler å bruke cond hvis man har mer enn to tilfeller istedetfor å bruke nøstede if-kall.

c)
Fint!

Det kan være verdt å påpeke at den modifiserte memq ikke nødvendigvis trenger å gå i det uendelige, men at det er hvertfall en mulighet.

d)
Bra!

e)
Fint!

f)
Supert!

Generelt
Totalt: 10 / 10 poeng

Det er bare å sende mail til atodegaa@student.matnat.uio.no dersom det er noe du lurer på (eventuelt er uenig i).

Lykke til videre med innlevering 3b!

- Arne Tobias Malkenes Ødegaard