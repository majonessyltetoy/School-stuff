This delivery has been corrected, and the grade is: 7/12 (passed)
10.7

Jeg skjønner hva du tenker her, men her forholder du deg ikke til den induktive definisjonen av utsagnslogiske formler. Du ser mer på utsagnslogiske formler som strenger. "xF" er ikke en form utsagnslogiske formler kan ha ut fra den induktive definisjonen av utsagnslogiske formler. Funksjonen skal også være fra mengden av utsagnslogiske formler til mengden av naturlige tall, og for eksempel "F v G)" (altså uten den første parentesen) er ikke en utsagnslogisk formel.

Forøvrig vil jeg anbefale å ikke levere oppgaver i form av scheme-kode i fremtiden, da ikke alle rettere nødvendigvis kan scheme.

Løsningsforslag:

Funksjonen ss kan defineres rekursivt på følgende måte:

s(P)=1s(P)=1 for alle utsagnsvariabler PP. (Dette er basistilfellet for funksjonen.)
s(¬F)=1+s(F)s(¬F)=1+s(F)
s((F∧G))=3+s(F)+s(G)s((F∧G))=3+s(F)+s(G)
s((F∨G))=3+s(F)+s(G)s((F∨G))=3+s(F)+s(G)
s((F→G))=3+s(F)+s(G)s((F→G))=3+s(F)+s(G)
(Legg merke til at det er en tett sammenheng med definisjon 9.4 der mengden av utsagnslogiske formler defineres induktivt.)
1/6 poeng

11.8

Du har basistilfelle n=1, påstanden er dermed ikke bevist for n=0 (som vi regner som et naturlig tall i INF1080). Ellers veldig bra!

6/6 poeng

Totalt: 7/12 poeng

Det virker som du har forstått prinsippene bak både matematisk induksjon og rekursjon. Studer løsningsforslaget til 10.7 nøye og forstå hvorfor denne funksjonen fungerer.

Ikke nøl med å sende epost til [bjornife@ifi.uio.no] dersom du lurer på noe, jeg elsker å svare på spørsmål :)

Bjørn Ingeberg Fesche
[bjornife]