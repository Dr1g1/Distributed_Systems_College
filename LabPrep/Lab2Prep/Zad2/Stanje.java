import java.rmi.*;

public interface Stanje extends Remote{
    public int vratiMinute() throws RemoteException;
    public int vratiPoruke() throws RemoteException;
    public int vratiInternet() throws RemoteException;
    public float vratiRacun() throws RemoteException;
}
