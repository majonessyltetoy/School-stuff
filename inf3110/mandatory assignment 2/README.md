This delivery has been corrected, and the grade is: Passed
Grei innlevering.

Resultatene til testene blir riktig og programmet kjører bra.
Et problem du har er at design dokumentet mangler. Men siden programmet er så komplett, blir det godkjent likevel. Husk derimot at design dokumentet var en del av oppgaven, det var ikke nok å bare skrive et program som gir riktig utskrift.

Implementasjon
Koden er lesbar og formatert bra, men du mangler kommentarer. Bare én liten kommentar her og der kan hjelpe meg å forstå hva som skjer. F.eks (* move *) over move implementasjonen eller (* while *).
Statement tolkingen skjer for det meste i interpret funksjonen, noe som ikke ser så dårlig ut, men jeg ville ha likt det bedre dersom du hadde en move funksjon og assign funksjon for å gjøre interpret litt mindre. Din håndtering av move er fin derimot ved at du bruker switch case.
Din evalExp funksjon er veldig fin og følger grammatikken på en bra måte. Du har en smart implementasjon av arithexp og boolexp.
Du har to "Warning: match nonexhaustive" som burde blitt håndtert. Den ene er pga du bruker strings for å beskrive direction: datatype direction = Direction of string. Dersom dette var datatype direction = EAST | WEST | NORTH | SOUTH, ville det ha gått uten advarsel.
Alt i alt er det en god innlevering, selv om du mangler design dokumentet.

Lykke til med eksamen!

Mvh

Espen Volnes