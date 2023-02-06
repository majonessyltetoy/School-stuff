import java.util.*;
import java.io.*;

class DB
{
    private String dbFile;

    private Tabell<Legemidler> legemiddelTabell = new Tabell<Legemidler>(90);
    private Tabell<Pasienter> pasientTabell = new Tabell<Pasienter>(90);
    private SortertEnkelListe legeListe = new SortertEnkelListe();
    private EnkelReseptListe reseptListe = new EnkelReseptListe();

    private int pasientNr, legemiddelNr, reseptNr;

    public DB(String defaultFile)
    {
        dbFile = defaultFile;
        pasientNr = legemiddelNr = reseptNr = 0;
    }

    public void lesDB(String file) throws Exception
    {
        String file_to_read = dbFile;
        if (!file.equals(""))
        {
            file_to_read = file;
        }

        // Delete all previous data
        pasientNr = legemiddelNr = reseptNr = 0;
        legemiddelTabell = new Tabell<Legemidler>(90);
        pasientTabell = new Tabell<Pasienter>(90);
        legeListe = new SortertEnkelListe();

        int byttMetode = 0;
        String line;
        String[] l; // not so good naming convention, but we will use it a lot
        Scanner read = new Scanner(new File(file_to_read));

        while (read.hasNextLine())
        {
            line = read.nextLine();

            if (!line.equals(""))
            {
                if (line.startsWith("#"))
                {
                    byttMetode++;
                }
                else
                {
                    l = line.split(",");

                    // remove space before text
                    for (int i=1;i<l.length;i++)
                    {
                        l[i] = l[i].substring(1);
                    }

                    switch (byttMetode)
                    {
                        // Pasient
                        case 1: {
                            addPasient(l[1], l[2], l[3], l[4]);
                            break;
                        }
                        case 2: {
                            if (l[6].equals("0"))
                            {
                                addLegemiddel(l[1],l[2],l[3],l[4],l[5],l[6],"0");
                            }
                            else
                            {
                                addLegemiddel(l[1],l[2],l[3],l[4],l[5],l[6],l[7]);
                            }
                            break;
                        }
                        case 3: {
                            addLege(l[0], l[1]);
                            break;
                        }
                        case 4: {
                            addResept(l[1], l[2], l[3], l[4], l[5]);
                            break;
                        }
                        case 5: {
                            break;
                        }
                        default: {
                            System.out.println("Error when reading db!");
                        }
                    }
                }
            }
        }
    }

    public void addPasient(String name, String persNr, String adresse,
        String postNr)
    {
        pasientTabell.put(new Pasienter(name, Long.parseLong(persNr), adresse,
            Integer.parseInt(postNr)), pasientNr);
        pasientNr++;
    }
    public void addLegemiddel(String navn, String form, String type,
        String pris, String antall, String virkestoff, String styrke)
    {
        int p, a, i, v, s;
        p = Integer.parseInt(pris);
        a = Integer.parseInt(antall);
        i = legemiddelNr; // we use legemiddelNr as the unique ID
        v = Integer.parseInt(virkestoff);
        s = Integer.parseInt(styrke);
        switch (type)
        {
            case "a": {
                if (form.equals("pille"))
                {
                    legemiddelTabell.put(new NarkotiskPiller(navn,p,i,a,v,s),
                        legemiddelNr);
                }
                else
                {
                    legemiddelTabell.put(new NarkotiskMikstur(navn,p,i,a,v,s),
                        legemiddelNr);
                }
                legemiddelNr++;
                break;
            }
            case "b": {
                if (form.equals("pille"))
                {
                    legemiddelTabell.put(new VanedannendePiller(navn,p,i,a,v,s),
                        legemiddelNr);
                }
                else
                {
                    legemiddelTabell.put(new VanedannendeMikstur(navn,p,i,a,v,s),
                        legemiddelNr);
                }
                legemiddelNr++;
                break;
            }
            case "c": {
                if (form.equals("pille"))
                {
                    legemiddelTabell.put(new VanligPiller(navn,p,i,a,v),
                        legemiddelNr);
                }
                else
                {
                    legemiddelTabell.put(new VanligMikstur(navn,p,i,a,v),
                        legemiddelNr);
                }
                legemiddelNr++;
                break;
            }
            default : {
                System.out.println("Error while adding 'legemidler'!");
            }
        }
    }

    public void addLege(String name, String avtaleNr)
    {
        legeListe.put(new Leger(name, Integer.parseInt(avtaleNr)));
    }

    public void addResept(String type, String pasientNr, String legeNavn,
        String legemiddelNr, String reit) throws ObjectNonExistingException
    {
        Resepter nyResept;
        Legemidler settInnMiddel;
        int l, r, p;
        Pasienter pasient;
        l = Integer.parseInt(legemiddelNr);
        r = Integer.parseInt(reit);
        p = Integer.parseInt(pasientNr);
        pasient = pasientTabell.find(p);
        settInnMiddel = legemiddelTabell.find(l);

        if (type.equals("blaa"))
        {
            nyResept = new BlaaResepter(reseptNr,
                settInnMiddel, legeNavn, pasient.toString(), r);
        }
        else
        {
            nyResept = new HviteResepter(reseptNr,
                settInnMiddel, legeNavn, pasient.toString(), r);
        }
        reseptNr++;

        ((Leger)legeListe.find(legeNavn)).giResept(nyResept, pasient);
        reseptListe.put(nyResept);
    }

    public void brukResept(String pasientNr, String reseptNr) throws ObjectNonExistingException
    {
        int p, r;
        p = Integer.parseInt(pasientNr);
        r = Integer.parseInt(reseptNr);
        if (pasientTabell.find(p).brukResept(r))
        {
            reseptListe.find(r).brukResept();
        }
    }

    public void iterate()
    {
        System.out.println("### Pasienter ####");
        pasientTabell.iterate();

        System.out.println("\n### Leger ###");
        legeListe.iterate();

        System.out.println("\n### Legemidler ###");
        legemiddelTabell.iterate();

        System.out.println("\n### Resepter ###");
        reseptListe.iterate();
    }
}