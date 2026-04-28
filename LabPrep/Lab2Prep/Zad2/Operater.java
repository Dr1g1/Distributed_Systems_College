import java.rmi.*;

public interface Operater extends Remote {
    public Korisnik vratiKorisnika(String broj) throws RemoteException;
}
