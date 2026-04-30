import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EksponatImpl extends UnicastRemoteObject implements Eksponat{
    private String id;
    private String naziv;
    private int cena;
    private KlijentAukcije trenutniKlijent;

    public EksponatImpl(String id, String naziv, int cena) throws RemoteException
    {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.trenutniKlijent = null;
    }

    public void prijaviLicitaciju(KlijentAukcije ka) throws RemoteException
    {
        this.trenutniKlijent = ka;
    }

    public KlijentAukcije vratiKlijentAukcije() throws RemoteException
    {
        return this.trenutniKlijent;
    }

    public void odustaniOdLicitacije(String klijentAukcijeId) throws RemoteException
    {
        if(trenutniKlijent != null && trenutniKlijent.getId().equals(klijentAukcijeId))
        {
            this.trenutniKlijent = null;
        }
    }

    public String vratiNaziv() throws RemoteException
    {
        return naziv;
    }

    public int vratiCenu() throws RemoteException
    {
        return cena;
    }

    public void povecajCenu(int iznos) throws RemoteException
    {
        this.cena += iznos;
    }

    public String vratiId() throws RemoteException
    {
        return id;
    }
}
