import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;

public class Klijent {

    public static void main(String args[])
    {
        String objectName = args[0];
        Scanner scanner = new Scanner(System.in);

        Operater operater; // zasto ovde nije impl nego je interfejs??
        try {
            operater = (Operater) Naming.lookup("rmi://localhost:1099/" + objectName);

            while(true) {
                System.out.println("Unesite opciju kao a, b, c...\na) Uplata Minuta \r\n" + //
                                        "b) Uplata Poruka \r\n" + //
                                        "c) Uplata Interneta \r\n" + //
                                        "d) Provera stanja \r\n" + //
                                        "e) Kraj ");
                String unos = scanner.nextLine();

                if(unos.equals("e")){
                    break;
                }
                String br;
                Korisnik k;
                Stanje stanje;
                switch (unos) {
                    case "a":
                        System.out.println("Unesite vas broj telefona...");
                        br = scanner.nextLine();
                        System.out.println("Unesite broj minuta...");
                        String brMin = scanner.nextLine();
                        k = operater.vratiKorisnika(br);
                        k.uplatiMinute(Integer.parseInt(brMin));
                        break;
                    case "b":
                        System.out.println("Unesite vas broj telefona...");
                        br = scanner.nextLine();
                        System.out.println("Unesite broj poruka...");
                        String brPor = scanner.nextLine();
                        k = operater.vratiKorisnika(br);
                        k.uplatiPoruke(Integer.parseInt(brPor));
                        break;
                    case "c":
                        System.out.println("Unesite vas broj telefona...");
                        br = scanner.nextLine();
                        System.out.println("Unesite broj megabajta...");
                        String brMB = scanner.nextLine();
                        k = operater.vratiKorisnika(br);
                        k.uplatiInternet(Integer.parseInt(brMB));
                        break;
                    case "d":
                        System.out.println("Unesite vas broj telefona...");
                        br = scanner.nextLine();
                        k = operater.vratiKorisnika(br);
                        stanje = k.vratiStanje();
                        System.out.println("Stanje vaseg racuna je:\nBroj - " + br + "\nMinuti - " + stanje.vratiMinute() + "\nPoruke - " + stanje.vratiPoruke() + "\nInternet - " + stanje.vratiInternet() + "MB\nRacun> " + stanje.vratiRacun() + "\n");
                        break;
                    default:
                        System.out.println("Nevalidan unos; probajte ponovo...");
                        break;
                }
            }
            scanner.close();
        }
        // zasto ovaj redosled???
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch(RemoteException e) {
            e.printStackTrace();
        }
        catch(NotBoundException e) {
            e.printStackTrace();
        }
    }
}

