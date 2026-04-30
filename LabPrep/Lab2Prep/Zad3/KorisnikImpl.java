import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KorisnikImpl extends UnicastRemoteObject implements Korisnik {
    public String jbk;
    private float iznosDinarski;
    private float iznosDevizni;

    public KorisnikImpl(String jbk, float iznosDinarski, float iznosDevizni) throws RemoteException
    {
        this.jbk = jbk;
        this.iznosDinarski = iznosDinarski;
        this.iznosDevizni = iznosDevizni;
    }

    public String vratiJbk() throws RemoteException {
        return this.jbk;
    }

    public Stanje vratiStanje() throws RemoteException
    {
        return new StanjeImpl(iznosDinarski, iznosDevizni);
    }

    public void transferDinarskiNaDevizni(float iznos, float kurs) throws RemoteException
    {
        this.iznosDinarski -= iznos;
        this.iznosDevizni += iznos / kurs;
    }

    public void transferDevizniNaDinarski(float iznos, float kurs) throws RemoteException
    {
        this.iznosDevizni -= iznos;
        this.iznosDinarski += iznos * kurs;
    }
}
