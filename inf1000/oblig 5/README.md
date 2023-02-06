This delivery has been corrected, and the grade is: 5/5 (passed)
Oppgave 1: Du har fått til veldig mye her! I main klassen så oppretter du et bil-objekt helt rett, og bruker pekeren til å kalle på objektmetodene på riktig måte. I Bil er alle variable private, og metodene dine er bra. Det du mangler er en konstruktør. Du har satt verdiene direkte i klassen, noe som gjør at alle bilene du oppretter vil ha samme str på tanken, og samme forbruk per km. Ved å bruke en konstruktør til å sette disse verdiene kan du lage forskjellige biler med forskjellig verdier :) Se eksempel:

public class Bil {

    private double tanken;
    private double forbruk;

    //Denne setter jeg til 0 siden det står i oppgaveteksten at alle nye biler har 0 i kilometerstand
    private int kmStand = 0; 

    //Dette er konstruktøren, som tar imot verdiene jeg ønsker å gi til objektvariablene 'tanken' 
    //og 'forbruk' når jeg oppretter en nytt 'Bil' objekt. 
    public Bil(double tankenBil, double forbrukBil) {
        tanken = tankenBil;
        forbruk = forbrukBil;
    }

}

public class Oppgave51 {
    public static void main(String[] args) {

        //Her oppretter jeg et objekt av typen bil, hvor
        //pekeren heter 'minBil'. Jeg sender med to verdier, siden konstruktøren
        //i Bil klassen krever to verdier. Når 'minBil' opprettes utføres konstruktøren
        //i Bil klassen, og tanken på minBil blir altså 100, mens forbruket blir 1. 
        Bil minBil = new Bil(100, 1):

    }
}
Oppgave 2: Det meste er riktig (selvom du kunne svart litt mer utfyllende). Det er viktig å vite at kun de public metodene til en klasse er grensesnittet. Du svarer heller ikke helt på hva en static metode er. I hovedsak så følger den en klasse, så man trenger ikke et objekt for å kalle på metoden. Det er altså ulikt fra en instansmetode hvor metodene 'tilhører' et objekt, og man må derfor opprette et objekt for å kalle på metoden.

Oppgave 3-4: Veldig bra! Du har lagd en fungerende stack. Isbod klassen din er veldig bra, og du har implementert metodene på en effektiv og bra måte. I main oppretter du isboden din riktig, og kallet på objektmetodene gir riktig resultat.

Oppgave 5: Husk å lever hver klasse i en separat fil! Dette står i oppgaveteksten. Ellers veldig bra, og du har brukt aspektene ved objektorientering på en fin måte :)

Poeng 5/5

En veldig bra innlevering! Det blir poeng på alle oppgaver. Det ser ut til at du har forstått grunnprinsippene bak objektorientering! Det eneste er at du ikke har brukt en konstruktør i noen av oppgavene, så pass på å øv deg på dette. De fleste klasser vil ha konstruktør etterhvert :) Godt jobba!