import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class KorisnikImpl extends UnicastRemoteObject implements Korisnik {

    public String broj; // broj telefona korisnika
    private int minuti; // pocetni broj minuta
    private int poruke; // pocetni broj poruka
    private int internet; // pocetni broj megabajta interneta
    private int minutiTarifa; // tarifa dinara po minuti
    private int porukeTarifa; // tarifa dinara po poruci
    private int internetTarifa; // tarifa dinara po megabajtu interneta
    private float racun; // trenutno stanje racuna

    public KorisnikImpl(String broj, int minuti, int poruke, int internet, int minutiTarifa, int porukeTarifa, int internetTarifa) throws RemoteException {
        this.broj = broj;
        this.minuti = minuti;
        this.poruke = poruke;
        this.internet = internet;
        this.minutiTarifa = minutiTarifa;
        this.porukeTarifa = porukeTarifa;
        this.internetTarifa = internetTarifa;
    }

    public void uplatiMinute(int minuti) throws RemoteException
    {
        this.minuti += minuti;
        // TO DO: povecava racun u skladu sa tarifom
        this.racun += minuti * minutiTarifa;
    }

    public void uplatiPoruke(int poruke) throws RemoteException
    {
        this.poruke += poruke;
        // TO DO: povecava racun u skladu sa tarifom
        this.racun += poruke * porukeTarifa;
    }

    public void uplatiInternet(int internet) throws RemoteException
    {
        this.internet += internet;
        // TO DO: povecava racun u skladu sa tarifom
        this.racun += internet * internetTarifa;
    }

    public Stanje vratiStanje() throws RemoteException
    {
        return new StanjeImpl(broj, minuti, poruke, internet, racun);
    }
}