This delivery has been corrected, and the grade is: 10/12 (passed)
Tilbakemelding på innlevering 9
15.10
a) Riktig
b) Bra. Er du enig i at {<1,1>, <1,2>} hadde vært like riktig?
c) Riktig
d) Riktig
4/4 poeng

16.4
a) Riktig
b) Riktig
c) Riktig
d) Riktig
e) Riktig
f) Denne er sann. Formelen ∃x¬∃y(Under(x,y)) uttrykker at det finnes en figur som ikke er under noen annen figur, hvilket er tilfellet for de øverste figurene.
3/4 poeng

16.7
Her tror jeg du roter litt.

Husk at når vi sier at Q er en logisk konsekvens av P (P=>Q) så sier vi at "hvis P, så Q". Derfor blir det feil når du f.eks. sier at ∀x∀yRxy er en logisk konsekvens av ∃x∃yRxy fordi selv om et element er relatert til noe (∃x∃yRxy) så betyr ikke det nødvendigvis at alle elementene er relatert til hverandre (∀x∀yRxy ). Men du kan si det motsatte!
Husk at ∀x∃yRxy og ∃y∀xRxy ikke er ekvivalente! Jeg foreslår at du leser disse to formlene høyt for deg selv rett frem, så kommer forskjellen tydeligere frem.
∀x∃yRxy betyr at alle elementer er relatert til noe, ∃y∀xRxy betyr at det finnes et element som alle er relatert til.
Jeg er enig i at ∃y∀xRxy⇒∀x∃yRxy, men ikke motsatt. (Hvorfor tror du det er sånn?)
Du gjorde litt motsatt på hva som er logisk konsekvens til hva, men ellers har du stort sett svart godt. Bra.

3/4 poeng

Totalt: 10/12 poeng

Flott, nesten alt riktig! Se på løsningsforslaget til 16.7. Lykke til på neste oblig!
Send meg gjerne en mail på mariahva@student.matnat.uio.no enten angående innleveringen eller stoffet ellers hvis du lurer på noe. ^-^

-Marianne

Løsningsforslag
15.10

a) RM={⟨1,1⟩,⟨1,2⟩,⟨2,1⟩,⟨2,2⟩}RM={⟨1,1⟩,⟨1,2⟩,⟨2,1⟩,⟨2,2⟩}
Forklaring: Formelen ∀x∀yRxy∀x∀yRxy kan leses som "for alle x, så er det slik at for alle y, så er x relatert med y i R". Altså må det være sånn at uansett hvilket element du velger fra domenet, kalt x, så skal du kunne velge et hvilket som helst annet element fra domenet, kalt y, og da skal ⟨x,y⟩∈RM⟨x,y⟩∈RM. Altså må "alle mulige kombinasjoner av x-er og y-er være med i R".

b) RM={⟨1,1⟩,⟨1,2⟩}RM={⟨1,1⟩,⟨1,2⟩}
Forklaring: Formelen ∃x∀yRxy∃x∀yRxy kan leses som "det finnes en x, slik at for alle y, så er x relatert med y i R". Altså må det for minst ett element i domenet, kalt x, være slik at du kan velge et hvilket som helst annet element fra domenet, kalt y, og da skal ⟨x,y⟩∈RM⟨x,y⟩∈RM. Her er det flere mulige løsninger. I denne løsningen er det 1 som er valgt som "x-elementet".

c) RM={⟨1,2⟩,⟨2,1⟩}RM={⟨1,2⟩,⟨2,1⟩}
Forklaring: Formelen ∀x∃yRxy∧¬∃xRxx∀x∃yRxy∧¬∃xRxx kan leses som "for alle x, så finnes det en y slik at x er relatert med y i R, og det finnes ikke en x slik at x er relatert med x i R". Altså må det være slik at uansett hvilket element du velger fra domenet, kalt x, så finnes det et element fra domenet, kalt y, slik at ⟨x,y⟩∈RM⟨x,y⟩∈RM. Samtidig skal det ikke for noen x i domenet være slik at ⟨x,x⟩∈RM⟨x,x⟩∈RM. Den eneste måten å oppnå dette på er at RM={⟨1,2⟩,⟨2,1⟩}RM={⟨1,2⟩,⟨2,1⟩}.

d) RM={⟨1,2⟩,⟨1,1⟩,⟨2,2⟩}RM={⟨1,2⟩,⟨1,1⟩,⟨2,2⟩}
Forklaring: Formelen ∃x∃y(Rxy∧¬Ryx)∧∀xRxx∃x∃y(Rxy∧¬Ryx)∧∀xRxx kan leses som "det finnes en x og en y, slik at x er relatert med y i R, men y er ikke relatert med x i R, og for alle x så er x relatert med x i R." Altså må det være slik at for minst et element i domenet, kalt x, og minst et element i domenet, kalt y, så er ⟨x,y⟩∈RM⟨x,y⟩∈RM uten at ⟨y,x⟩∈RM⟨y,x⟩∈RM. Samtidig skal det for alle x i domenet være slik at ⟨x,x⟩∈RM⟨x,x⟩∈RM. En av de to måtene å oppnå dette på er at RM={⟨1,2⟩,⟨1,1⟩,⟨2,2⟩}RM={⟨1,2⟩,⟨1,1⟩,⟨2,2⟩}.

16.4

a) Usann. Formelen ∃x(Liten(x)∧Trekant(x))∃x(Liten(x)∧Trekant(x)) uttrykker at det finnes en liten trekant, men det er ikke tilfellet fordi alle trekantene er store.

b) Sann. Formelen ∃x(Liten(x)∧Firkant(x))∃x(Liten(x)∧Firkant(x)) uttrykker at det finnes en liten firkant, hvilket er tilfellet.

c) Usann. Formelen ∀x(Liten(x)→Firkant(x))∀x(Liten(x)→Firkant(x)) uttrykker at alle små figurer er firkanter, men det er ikke tilfellet fordi det finnes en liten sirkel.

d) Sann. Formelen ∀x(Sirkel(x)→Liten(x))∀x(Sirkel(x)→Liten(x)) uttrykker at alle sirkler er små, hvilket er tilfellet.

e) Sann. Formelen ∀x(Trekant(x)→Stor(x))∀x(Trekant(x)→Stor(x)) uttrykker at alle trekanter er store, hvilket er tilfellet.

f) Sann. Formelen ∃x¬∃y(Under(x,y))∃x¬∃y(Under(x,y)) uttrykker at det finnes en figur som ikke er under noen annen figur, hvilket er tilfellet for de øverste figurene.

16.7

Vi har følgende logiske konsekvenser:

∀x∀yRxy⇒∃x∃yRxy∀x∀yRxy⇒∃x∃yRxy
∀x∀yRxy⇒∃y∀xRxy∀x∀yRxy⇒∃y∀xRxy
∀x∀yRxy⇒∀x∃yRxy∀x∀yRxy⇒∀x∃yRxy
∃y∀xRxy⇒∀x∃yRxy∃y∀xRxy⇒∀x∃yRxy
∃y∀xRxy⇒∃x∃yRxy∃y∀xRxy⇒∃x∃yRxy
∀x∃yRxy⇒∃x∃yRxy∀x∃yRxy⇒∃x∃yRxy
Formelen ∀x∀yRxy∀x∀yRxy har alle de andre formlene som logisk konsekvens; M⊨∀x∀yRxyM⊨∀x∀yRxy betyr at ⟨x,y⟩∈RM⟨x,y⟩∈RM for alle x,y∈|M|x,y∈|M|, eller med andre ord at RMRM er lik |M|×|M||M|×|M|. Enhver modell som gjør denne formelen sann må også gjøre de tre andre formlene sanne.
∃x∀yRxy⇒∀x∃yRxy∃x∀yRxy⇒∀x∃yRxy: Anta at M⊨∃y∀xRxyM⊨∃y∀xRxy. Da finnes det et element a∈|M|a∈|M| slik at for alle x∈|M|x∈|M| så M⊨Rx¯a¯M⊨Rx¯a¯. For å vise at M⊨∀x∃yRxyM⊨∀x∃yRxy, velg et vilkårlig element x∈|M|x∈|M|. Ved antagelsen følger det at M⊨Rx¯a¯M⊨Rx¯a¯, og dermed at M⊨∃yRx¯yM⊨∃yRx¯y. Fordi xx var vilkårlig valgt har vi at M⊨∀x∃yRxyM⊨∀x∃yRxy.
Formelen ∃x∃yRxy∃x∃yRxy er en logisk konsekvens av alle de andre formlene; M⊨∃x∃yRxyM⊨∃x∃yRxy betyr at det finnes x,y∈|M|x,y∈|M| slik at ⟨x,y⟩∈RM⟨x,y⟩∈RM, og det er tilfellet for enhver modell som gjør en av de andre formlene sanne.