import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EksponatImpl extends UnicastRemoteObject implements Eksponat {
    private String id;
    private String naziv;
    private int cena;
    private KlijentAukcije trenKlijent;

    public EksponatImpl(String id, String naziv, int cena) throws RemoteException
    {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.trenKlijent = null;
    }
    
    public void prijaviLicitaciju(KlijentAukcije klijent) throws RemoteException
    {
        this.trenKlijent = klijent;
    }

    public KlijentAukcije vratiKlijentaAukcije() throws RemoteException
    {
        return this.trenKlijent;
    }
    
    public void odustaniOdLicitacije(String klijentId) throws RemoteException
    {
        if(trenKlijent != null && trenKlijent.getId().equals(klijentId))
            this.trenKlijent = null;
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
