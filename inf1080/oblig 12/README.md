This delivery has been corrected, and the grade is: 12/12 (passed)
Tilbakemelding på innlevering 12
21.6

Riktig

22.10

a) Riktig

b) Riktig

c) Det er riktig at det finnes en Eulervei, men ikke en Eulerkrets i grafen. Det at det ikke finnes noen Eulerkrets er imidlertid ikke på grunn av at vi må gå gjennom startnoden før vi er ferdig, men snarere fordi vi ikke kan både starte og slutte på samme node.

12/12 poeng

Bra jobba, lykke til på eksamen!

Det er bare å sende meg en mail til stianval@ifi.uio.no hvis du har spørsmål til tilbakemeldingen eller noe annet!

-Stian Valle / stianval

Løsningsforslag
21.6

Den komplette grafen med nn noder har (n2)(n2) kanter (for hver unike kant må vi velge to av nodene). Vi har at ee er en kant i G¯¯¯¯G¯ hvis og bare hvis ee ikke er en kant i GG. Hvis vi summerer antall kanter GG med antall kanter i G¯¯¯¯G¯, får vi antall kanter i den komplette grafen. Antall kanter i G¯¯¯¯G¯ blir derfor (n2)−m(n2)−m.

Vi kan finne frem til formelen for antall kanter i den komplette grafen med nn noder på flere måter. I den komplette grafen KnKn vil det være n−1n−1 kanter inntil hver node (siden det "går en kant til" alle de andre n−1n−1 nodene). Til sammen er det n(n−1)n(n−1) kanter inntil alle nodene. Men da teller vi alle kantene 2 ganger, siden hver kant er inntil 2 noder, så vi må dele dette tallet på 2 for å finne antall kanter i grafen: n(n−1)2n(n−1)2 (som er lik (n2)(n2)).

Vi kan finne frem til denne formelen på en annen måte, ved å ta utgangspunkt i følgende observasjon: Når man legger til en nn-te node i en komplett graf, må man legge til n−1n−1 kanter for å få en ny komplett graf. For hver node man legger til, må man altså legge til en kant mer enn man måtte forrige gang. Ut fra dette kan man se at antall kanter i den komplette grafen med nn noder svarer til summen av de n−1n−1 første tallene (som også svarer til det (n−1)(n−1)-te triangulære tallet, se side 111). Formelen for summen av de nn første tallene står på side 132, og er n(n+1)2n(n+1)2. Formelen for summen av de n−1n−1 første tallene, og antall kanter i den komplette grafen med nn noder, blir altså igjen n(n−1)2n(n−1)2.

22.10

a) m+nm+n
b) m⋅nm⋅n
c)

K2,3K2,3 har to noder av odde grad. Denne grafen har ingen Eulerkrets, siden noen av nodene i grafen er av odde grad. Grafen har en Eulervei, siden nøyaktig to av nodene i grafen har odde grad. Se diskusjonen på side 247 for en forklaring på hvorfor det er sånn.