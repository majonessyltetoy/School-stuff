This delivery has been corrected, and the grade is: Passed
Det var mye bedre :-) Godkjent!

Forresten trenger du ikke å se på første wildcard, bare siste. Tenk på følgende situasjon

nål: E_IALLESAMMEN

og høystakken er:
XXXALLESAMMENIALLESAMMEN

Nåla får en mismatch ved # under:

XXXALLESAMMENIALLESAMMEN
E_IALLESAMMEN
  #         s
Hvis den bare skulle flyttes ett steg, ville det tilsi at

XXXALLESAMMENIALLESAMMEN
 E_IALLESAMMEN
kunne vært en mulig match. Men siden siste bokstav i første forsøk (markert med "s" over) er en N, vet BMH at N-en ikke kan matche med E, M, A, S, E, L, A, eller I.
Det eneste N-en kan matche er wildcard, så da er det trygt å flytte til:

XXXALLESAMMENIALLESAMMEN
           E_IALLESAMMEN
som gir en match.
