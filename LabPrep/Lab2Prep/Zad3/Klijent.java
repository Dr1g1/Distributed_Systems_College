import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;

public class Klijent {
    public static void main(String args[])
    {
        String objectName = args[0];
        Scanner scanner = new Scanner(System.in);

        EBanka ebanka;
        try {
            ebanka = (EBanka) Naming.lookup("rmi://localhost:1099/" + objectName);

            while(true)
            {
                System.out.println("Dobrodosli u eBank korisnicki servis. Za nastavak izaberite opciju:\r\n" + //
                                        "a) Transfer sa dinarskog na devizni račun \r\n" + //
                                        "b) Transfer sa deviznog na dinarski račun \r\n" + //
                                        "c) Provera stanja \r\n" + //
                                        "d) Kraj");
                String unos = scanner.nextLine();

                if(unos.equals("d"))
                    break;

                String jbrk;
                float iznos;
                Korisnik k;
                switch(unos) {
                    case "a":
                        System.out.println("Izbrali ste opciju za Transfer sa dinarskog na devizni račun: \r\n" + //
                                                        "Unesite jedinstveni broj korisnika:");
                        jbrk = scanner.nextLine();
                        System.out.println("Unesite iznos:");
                        iznos = scanner.nextInt();
                        k = ebanka.vratiKorisnika(jbrk);
                        k.transferDinarskiNaDevizni(iznos, 117);
                        break;
                    case "b":
                        System.out.println("Izbrali ste opciju za Transfer sa deviznog na dinarski račun: \r\n" + //
                                                        "Unesite jedinstveni broj korisnika:");
                        jbrk = scanner.nextLine();
                        System.out.println("Unesite iznos:");
                        iznos = scanner.nextInt();
                        k = ebanka.vratiKorisnika(jbrk);
                        k.transferDevizniNaDinarski(iznos, 117);
                        break;
                    case "c":
                        System.out.println("Izbrali ste opciju za Proveru stanja računa: \r\n" + //
                                                        "Unesite jedinstveni broj korisnika:");
                        jbrk = scanner.nextLine();
                        k = ebanka.vratiKorisnika(jbrk);
                        Stanje stanje = k.vratiStanje();
                        System.out.println("Vase stanje je: \r\n" + //
                                                        "Iznos na dinarskom računu:  " + stanje.vratiDinarskiIznos() +"\r\n" + //
                                                        "Iznos na deviznom računu: " + stanje.vratiDevizniIznos());
                        break;
                    default:
                        System.out.println("Nevalidan unos.");
                        break;
                }
            }

        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(RemoteException e) {
            e.printStackTrace();
        } catch(NotBoundException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}

/*
Dobrodosli u eBank korisnicki servis. Za nastavak izaberite opciju:
a) Transfer sa dinarskog na devizni račun 
b) Transfer sa deviznog na dinarski račun 
c) Provera stanja 
d) Kraj

Izbrali ste opciju za Transfer sa dinarskog na devizni račun: 
Unesite jedinstveni broj korisnika:

Unesite iznos:
*/