import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;

public class Klijent {
    public static void main(String[] args) {
        String objectName = args[0];
        Scanner scanner = new Scanner(System.in);

        try{

            EAukcija aukcija = (EAukcija) Naming.lookup("rmi://localhost:1099/" + objectName);
            System.out.println("Dobrodosli na elektronsku aukciju. Unesite vase licne podatke:");
            System.out.println("Identifikator:");
            String id = scanner.nextLine();
            System.out.println("Ime:");
            String ime = scanner.nextLine();
            System.out.println("Prezime:");
            String prezime = scanner.nextLine();

            KlijentAukcije ka = new KlijentAukcije(id, ime, prezime);

            while(true) {
                System.out.println("Unesite identifikator eksponata (ili 'kraj' za izlaz):");
                String eksId = scanner.nextLine();

                if(eksId.equals("kraj")) break;

                Eksponat e = aukcija.vratiEksponat(eksId);
                if(e == null) {
                    System.out.println("Eksponat nije pronadjen!");
                    continue;
                }

                System.out.println("Naziv: " + e.vratiNaziv());
                System.out.println("Cena: " + e.vratiCenu());

                System.out.println("Izaberite opciju:\na) Licitacija\nb) Odustajanje");
                String opcija = scanner.nextLine();

                if (opcija.equals("a")) {
                    System.out.println("Za koliko uvecavate iznos?");
                    int iznos = Integer.parseInt(scanner.nextLine());
                    e.povecajCenu(iznos);
                    e.prijaviLicitaciju(ka);
                    System.out.println("Uspesno ste licitirali! Nova cena: " + e.vratiCenu());
                } else if (opcija.equals("b")) {
                    e.odustaniOdLicitacije(ka.getId());
                    System.out.println("Odustali ste od licitacije.");
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
