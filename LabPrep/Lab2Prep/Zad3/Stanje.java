import java.rmi.*;

public interface Stanje extends Remote {
    public float vratiDinarskiIznos() throws RemoteException;
    public float vratiDevizniIznos() throws RemoteException;
}
