import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        String objectName = args[0];
        Scanner scanner = new Scanner(System.in);

        Kviz  kviz;
        try{
            kviz = (Kviz) Naming.lookup("rmi://localhost:1099/" + objectName);
            kviz.pocetak();
            for(int i = 0; i < 3; i++)
            {
                Pitanje p = kviz.vratiPitanje();
                String tekst = p.vratiTekst();
                System.out.println("\n" + tekst);

                String odgovor = scanner.nextLine();

                kviz.odgovori(odgovor);
            }
            int brP = kviz.vratiBrojPoena();
            System.out.println("\nVas broj poena je: " + brP);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        scanner.close();
    }
}
