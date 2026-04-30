import java.rmi.*;

public interface EBanka extends Remote {
    public Korisnik vratiKorisnika(String jbk) throws RemoteException;
}
