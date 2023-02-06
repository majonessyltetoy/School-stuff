/**
 * Klassen Sorter kan sortere en String-array eller flette sammen to String-arrays
 * 
 * @author Jan Inge Lamo
 * @author Victoria Ariel Bjoernestad
 */
public class Sorter extends Thread {
    private Monitor monitor; 
    private String[] liste; 
    private String[] listenr1;
    private String[] listenr2;
    private boolean flette;

    /**
     * Constructor #1 for Sorter class
     * 
     * @param  monitor container to put the finished sorted array
     * @param  liste   array to sort
     */
    public Sorter(Monitor monitor, String[] liste) {
        this.monitor=monitor;
        this.liste=liste;
        flette=false;
    }

    /**
     * Constructor #2 for Sorter class
     * 
     * @param  monitor  container to put the finished sorted array
     * @param  listenr1 array to sort #1
     * @param  listenr2 array to sort #2
     */
    public Sorter(Monitor monitor, String[] listenr1, String[] listenr2) {
        this.monitor=monitor;
        this.listenr1=listenr1;
        this.listenr2=listenr2;
        flette=true;
    }
    /**
     * Kjoerer traad
     */
    @Override
    public void run() {
        if(!flette) {
            String[] list = mergeSort(liste);
            monitor.faaSortert(list);
        } else {
            String[] flettetListe=merge(listenr1, listenr2);
            monitor.faaSortert(flettetListe);
        }
    }

    /**
     * Sorterer med MergeSort-algoritmen
     * 
     * @param  liste Arrayen som skal sorteres 
     * @return       Sortert array
     */
    public String[] mergeSort(String[] liste) {
        if(liste.length <=1) {
            return liste;
        } else {
            int del1 = liste.length/2;
            int del2 = liste.length-del1;
            String[] liste1 = new String[del1];
            String[] liste2 = new String[del2];
            for(int i=0; i<del1; i++) {
                liste1[i]=liste[i];
                liste2[i]=liste[i+del1];
            }
            liste2[del2-1]=liste[del2+del1-1];
            liste1=mergeSort(liste1);
            liste2=mergeSort(liste2);
            String[] sortertliste = merge(liste1, liste2);
            return sortertliste;
        }
            
    }

    /**
     * Fletter sammen to arrayer
     * 
     * @param  liste1 En array som skal flettes
     * @param  liste2 En array som skal flettes
     * @return        Flettet array
     */
    private String[] merge(String[] liste1, String[] liste2) {
        int lengde = liste1.length+liste2.length;
        String[] sortertliste=new String[lengde];
        int antall1=0;
        int antall2=0;
        for(int i=0; i<lengde; i++) {
            if(antall1<liste1.length&&antall2<liste2.length) {
                if(liste1[antall1].toLowerCase().compareTo(liste2[antall2].toLowerCase())<0) {
                    sortertliste[i]=liste1[antall1];
                    antall1++;
                } else {
                    sortertliste[i]=liste2[antall2];
                    antall2++;
                }
            } else if(antall1>=liste1.length) {
                sortertliste[i]=liste2[antall2];
                antall2++;
            } else if(antall2>=liste2.length) {
                sortertliste[i]=liste1[antall1];
                antall1++;
            }
        }
        return sortertliste;
    }
}