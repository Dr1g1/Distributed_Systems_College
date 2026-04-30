import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.*;
import java.util.Scanner;

public class Klijent {
    public static void main(String args[])
    {
        String objectName = args[0];
        Scanner scanner = new Scanner(System.in);

        EStudSluzba sluzba;
        try {

            sluzba = (EStudSluzba) Naming.lookup("rmi://localhost:1099/" + objectName);

            while(true)
            {
                System.out.println("Dobrodosli u korisnicki servis studentske sluzbe. Za nastavak izaberite opciju: \r\n" + //
                                        "a) Prijava ispita \r\n" + //
                                        "b) Provera prijavljenih ispita \r\n" + //
                                        "c) Kraj");
                String unos = scanner.nextLine();
                String indeks;
                Student student;
                if(unos.equals("c"))
                    break;
                else if(unos.equals("a"))
                {
                    System.out.println("Izbrali ste opciju za prijavu ispita: \r\n" + //
                                                "Unesite broj indeksa:");
                    indeks = scanner.nextLine();
                    System.out.println("Unesite naziv ispita:");
                    String ispit = scanner.nextLine();
                    student = sluzba.vratiStudenta(indeks);
                    student.prijaviIspit(ispit);
                }
                else
                {
                    System.out.println("Izbrali ste opciju za proveru prijavljenih ispita: \r\n" + //
                                                "Unesite broj indeksa:");
                    indeks = scanner.nextLine();
                    student = sluzba.vratiStudenta(indeks);
                    Prijava prijava = student.vratiPrijavu();
                    System.out.println(prijava.vratiIspite());
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
