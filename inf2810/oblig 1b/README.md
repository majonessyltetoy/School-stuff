This delivery has been corrected, and the grade is: 19/20 (passed)
Tilbakemelding på Innlevering 1b! (9 av 10 poeng)
Totalt på oblig 1a og 1b --> 19 av 20 poeng.
--
1 Par og lister (2,5 av 3 poeng)
a) - e) boks- og peker-diagram
Oppgave a-d) er helt riktige, bra!

Det er vanlig å tegne diagrammene med en skrå strek over cdr for å vise at den peker på den tomme listen. Dette vil jeg anbefale deg å gjøre fremover, spesielt på eksamen, istedenfor å putte en tom liste inn i cdr-cellen i det siste consparet.

Oppgave e) er nesten riktig, men her må både car- og cdr-pekerene peke til den samme lista, i stedet for to adskilte kopier av foo. Dette er fordi foo nødvendigvis er nødt til å evalueres til det samme hver gang vi kaller foo. Derfor må det være den samme listen, og ikke to forskjellige:

;; (define foo '(1 2 3))
;; (cons foo foo) --> ((1 2 3) 1 2 3)
;;
;;
;;         +-------+-------+
;;         |       |       |
;;         |   o   |   o   |
;;         |   |   |   |   |
;;         +---+---+---+---+
;;             |      /
;;             |     /
;;             |    /
;;             |   / 
;;             |  /
;;             V V
;;            +-------+-------+        +-------+-------+        +-------+-------+
;;            |       |       |        |       |       |        |       |    /  |
;;    foo---> |   o   |   o---+------> |   o   |   o---+------> |   o   |   /   |
;;            |   |   |       |        |   |   |       |        |   |   |  /    |
;;            +---+---+-------+        +---+---+-------+        +---+---+-------+
;;                |                        |                        |
;;                |                        |                        |
;;                |                        |                        |
;;                V                        V                        V
;;
;;                2                        2                        3
f) - i) car og cdr
Oppgave f-i) er helt riktig. Bra!

2 Rekursjon over lister og høyereordens prosedyrer (6,5 av 7 poeng)
a) length2
Du har skrevet en fungerende halerekursiv versjon av length2, bra!

b) rev-list
Denne er helt riktig, bra!

Oppgaven ber deg om en kommentar på hva slags type rekursjon du har valgt å bruke og hvorfor. Dette mangler i din innlevering.

c) ditch
Implementasjonen av ditch er veldig bra!
Bra kommentar også.

d) nth
nth fungerer som den skal, bra!

e) where
where fungerer akkurat slik den skal, bra!

f) map2
Veldig bra! map2 er implementert helt nydelig.

g) λ og map2
Bra!

h) both?
Helt riktig, bra!

i) self
Også denne er helt riktig!

Generelt
Hittil i kurset tror jeg vi kun har vist at vi lager indre hjelpeprosedyrer ved hjelp av define. Du bruker nesten konsekvent let til dette.
Dersom du gjør det fordi du mener det er bedre, så skal ikke jeg si noe på det, men dersom du ikke vet hvorfor, bare sett at noen gjør det på nettet e.l. ville jeg vurdert å gjøre det slik som eksemplene i forelesning er.
Dette er bare et råd, du står i utgangspunktet fritt til å gjøre oppgavene slik du vil, så lenge de gjør det de skal.

Send veldig gjerne mail til esbenss@ifi.uio.no dersom jeg har vært uklar eller du har andre spørsmål. Du er også hjertelig velkommen til gruppetimen min mandager 12:00 på rom Sed i første etasje dersom du har spørsmål du ønsker ekstra grundig forklaring på.

Lykke til med innlevering 2a!

Mvh.
- Esben Slaatto