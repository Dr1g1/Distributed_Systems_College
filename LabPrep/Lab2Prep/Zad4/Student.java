import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Student extends Remote {
    public Prijava vratiPrijavu() throws RemoteException;
    public void prijaviIspit(String ispit) throws RemoteException;
    public String getIndeks() throws RemoteException;
}
