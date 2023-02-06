This delivery has been corrected, and the grade is: 19/20 (passed)
Tilbakemelding på Innlevering 3b! (9 av 10 poeng)
Totalt på oblig 3a og 3b --> 19 av 20 poeng.
--
1 Bli kjent med evaluatoren (0,5 av 0,5 poeng)
a) (0,5 av 0,5 poeng)
Bra forklaring!

2 Primitiver / innebygde prosedyrer (1,5 av 1,5 poeng)
a) (0,5 av 0,5 poeng)
Fint!

b) (1 av 1 poeng)
Bra!

Prosedyren kan implementeres enklere. Istedenfor å bruke destruktive listeoperasjoner på the-global-environment kan vi bruke define-varible!. define-variable! tar navn, verdi og miljø som argumenter. For å få prosedyren til å regnes som en primitive må vi "tagge" prosedyren med symbolet 'primitive. Implementasjonen kan da f.eks se slik ut:

(define (install-primitive! name proc) 
  (let ((new (list 'primitive proc)))
    (define-variable! name new the-global-environment)))
3 Nye special forms og alternativ syntaks (7 av 8 poeng)
a) (0,5 av 1,5 poeng)
Bra! En liten mangel er at and og or ikke fungerer uten argumenter. I R5RS-Scheme returnerer (and) "true" (#t) og (or) "false" (#f). Se dokumentasjonen.

I tillegg fungerer ikke and/or helt som forventet. Prøv f.eks følgende (i meta-REPL'et):

(or 'false 'true)  ;; => false (men forventet true)
(and 'true 'false) ;; => true (men forventet false)
Grunnen til at du får disse feilene er at du ikke har tatt høyde for at meta-schemen bruker symbolene 'true og 'false som sannhetsverdier i tillegg til #t og #f. I prekoden er det to prosedyrer true? og false? som tar høyde for disse sannhetsverdisymbolene.

Hadde gjerne sett at dere returnert 'true og 'false som sannhetsverdier i stedet for #t og #f. Det fremgår av prekoden at det er disse som brukes som "hovedsannhetsverdier" i meta-schemen.

b) (1,5 av 1,5 poeng)
Flott!

Dette fungerer!

Dette kan gjøres enklere uten å transformere til cond-uttrykk og penere med en fullstendig abstraksjonsbarriere:
Løsningsforslag:

(define (else? exp) (tagged-list? exp 'else))

(define (if-else exp) (cadr exp))

(define (if-consequent exp) (cadddr exp))

(define (if-alternative exp) (cddddr exp))

(define (eval-if exp env)
 (cond ((else? exp) (mc-eval (if-else exp) env))
       ((true? (mc-eval (if-predicate exp) env))
        (mc-eval (if-consequent exp) env))
       (else (eval-if (if-alternative exp) env))))
c) (1,5 av 1,5 poeng)
Bra!

Dette fungerer!

Fin abstraksjonsbarriere!

d) (1,5 av 1,5 poeng)
Bra!

e) (2 av 2 poeng)
Bra!

Bra! Fin og enkel løsning!

Generelt
Veldig bra innlevering!

Lykke til på eksamen!

Det er bare å sende mail til esbenss@ifi.uio.no dersom det er noe du lurer på, enten det er om retting eller andre ting.

Hilsen Esben