import java.rmi.*;

public interface Pitanje extends Remote {
    String vratiTekst() throws RemoteException;
}
