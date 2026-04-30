import java.rmi.*;

public interface Korisnik extends Remote {
    public String vratiJbk() throws RemoteException;
    public Stanje vratiStanje() throws RemoteException;
    public void transferDinarskiNaDevizni(float iznos, float kurs) throws RemoteException;
    public void transferDevizniNaDinarski(float iznos, float kurs) throws RemoteException;
}
