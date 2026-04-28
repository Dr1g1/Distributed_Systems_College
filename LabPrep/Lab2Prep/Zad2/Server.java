import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;

public class Server {
    
    public Server(String objectName) {
        try {
            LocateRegistry.createRegistry(1099); // sta radi ova fja
            System.out.println("Java RMI registry created.");
        }
        catch(RemoteException e) { // sta znaci ovaj exception
            System.out.println("Java RMI registry already exists.");
        }

        try{
            OperaterImpl o = new OperaterImpl();
            Naming.rebind("rmi://localhost:1099/" + objectName, o);
        }
        catch(RemoteException e) { // sta znace ovi catchevi i zasto su ovog tipa
            System.out.println("Failure during RMI object creation: " + e);
        }
        catch(MalformedURLException e) { // zasto je ovo drugi catch a ne prvi
            System.out.println("Failure during Name registration: " + e);
        }
    }

    // nisam znala da unutar neke klase moze da  postoji main od kog ce da krene da se izvrsava program
    public static void main(String[] args) {
        String objectName = args[0];

        new Server(objectName);
        System.out.println("Server started.");

        try {
            System.in.read();
        }
        catch(IOException e)
        {
            
        }
    }
}