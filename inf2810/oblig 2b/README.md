This delivery has been corrected, and the grade is: 18/20 (passed)
Tilbakemelding på Innlevering 2b
Se også løsningsforslaget på oblig-siden.

1 Innkapsling, lokal tilstand og omgivelsesmodellen (1,5 av 2 poeng)
a)
Bra!

b)
Veldig bra! Dere har dog noen få mangler.

Det skal egentlig være tomme rammer mellom hver av E1- og E2-rammene, og den globale omgivelsen. Det er slik at for hvert prosedyrekall opprettes det en ny ramme med parametere bundet til argument-verdier, og dersom prosedyren ikke tar noen argumenter opprettes det en tom ramme. Så kallene på make-counter fører først til at det opprettes nye omgivelser i form av tomme rammer (tomme siden make-counter ikke tar noen argumenter), før let-uttrykkene som så evalueres her fører til at disse omgivelsene utvides med rammer der count bindes til tallet 0.

Omgivelsen dere har kalt E1, altså den som opprettes under kallet på c2 skal peke tilbake til c2 sin lokale omgivelse (navngi gjerne også denne) istedenfor å peke direkte tilbake på den globale omgivelsen. E1-rammen deres skal også være tom siden dette kallet ikke har noen parametre.

Se omgivelsesdiagram-tegningen for løsningsforslaget og forklaringen under den.

2 Innkapsling, lokal tilstand og -message passing- (2 av 2 poeng)
a)
Her fungerer alt som det skal, bra!

b)
Dette fungerer bra!

3 Strukturdeling og sirkulære lister (5,5 av 6 poeng)
a)
Fint!

b)
Tegningen er fin, men dere forklarer ikke resultatet av det siste kallet.

c)
Veldig bra!

d)
Riktig!

e)
En fin implementasjon av make-ring!

Den eneste mangelen er at make-ring ikke skal være destruktiv (merk at den ikke heter make-ring!), for den skal ikke endre lista man får ved input destruktivt.

Se også løsningsforslag der insert! og delete! implementeres mye enklere ved hjelp av en peker til noden før toppnoden.

f)
Riktig!

Generelt
Bra jobbet!

Poengsum:

Denne obligen:   9 / 10 poeng.
Forrige oblig:   9 / 10 poeng.
Totalt:         18 / 20 poeng.
Ikke nøl med å sende en mail til kjetisva@ifi.uio.no dersom dere lurer på noe! Lykke til videre med innlevering 3a.

Vennlig hilsen
Kjetil Svalestuen