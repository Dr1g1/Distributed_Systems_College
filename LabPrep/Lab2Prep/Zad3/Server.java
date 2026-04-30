import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;

public class Server {

    public Server(String objectName) {
        try {
        // Sun/Oracle je izabrao broj 1099 kada je kreirao RMI tehnologiju i ovaj broj je registrovan kod IANA(Internet Assigned Numbers Authority)
        LocateRegistry.createRegistry(1099);
        System.out.println("Java RMI registry created.");
        } catch(RemoteException e) {
            System.out.println("Java RMI registry already exists.");
        }

        try {
            EBankaImpl ebanka = new EBankaImpl();
            Naming.rebind("rmi://localhost:1099/" + objectName, ebanka);
        } catch(RemoteException e) {
            System.out.println("Failure during RMI object creation: " + e);
        } catch(MalformedURLException e) {
            System.out.println("Failure during Name registration: " + e);
        }
    }

    public static void main(String[] args) {
        String objectName = args[0];

        new Server(objectName);
        System.out.println("Server started.");

        try {
            System.in.read();
        } catch(IOException e) {

        }
    }

}
