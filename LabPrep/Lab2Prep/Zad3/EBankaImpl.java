import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EBankaImpl extends UnicastRemoteObject implements EBanka {
    
    private Korisnik[] korisnici;

    public EBankaImpl() throws RemoteException {
        
        korisnici = new Korisnik[3];
        
        korisnici[0] = new KorisnikImpl("123456", 100, 200);
        korisnici[1] = new KorisnikImpl("789101", 3000, 8000);
        korisnici[2] = new KorisnikImpl("112131", 70000, 50);
    }

    public Korisnik vratiKorisnika(String jbk) throws RemoteException
    {
        for(Korisnik k : this.korisnici)
        {
            if(k.vratiJbk().equals(jbk)) {
                return k;
            }
        }
        return null;
    }
}
