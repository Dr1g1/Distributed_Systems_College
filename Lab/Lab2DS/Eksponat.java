import java.rmi.*;

public interface Eksponat extends Remote {
    public void prijaviLicitaciju(KlijentAukcije ka) throws RemoteException;
    public KlijentAukcije vratiKlijentaAukcije() throws RemoteException;
    public void odustaniOdLicitacije(String klijentAukcijeId) throws RemoteException;
    public String vratiNaziv() throws RemoteException;
    public int vratiCenu() throws RemoteException;
    public void povecajCenu(int iznos) throws RemoteException;
    public String vratiId() throws RemoteException;
}
