import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StudentImpl extends UnicastRemoteObject implements Student {
    private String brIndeksa;
    private Prijava prijava;

    public StudentImpl(String brIndeksa) throws RemoteException {
        this.brIndeksa = brIndeksa;
        prijava = new PrijavaImpl();
    }

    public String getIndeks() throws RemoteException
    {
        return brIndeksa;
    }

    public Prijava vratiPrijavu() throws RemoteException
    {
        return prijava;
    }

    public void prijaviIspit(String ispit) throws RemoteException
    {
        prijava.dodajIspit(ispit);
    }
}
