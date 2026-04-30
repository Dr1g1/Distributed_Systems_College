import java.rmi.*;

public interface Prijava extends Remote {
    public String vratiIspite() throws RemoteException;
    public void dodajIspit(String ispit) throws RemoteException;
}
