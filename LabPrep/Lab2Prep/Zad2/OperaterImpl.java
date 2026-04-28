import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class OperaterImpl extends UnicastRemoteObject implements Operater {
    
    private KorisnikImpl[] korisnici;

    public OperaterImpl() throws RemoteException 
    {
        this.korisnici = new KorisnikImpl[3];
        
        this.korisnici[0] = new KorisnikImpl("060123456", 50, 60, 30, 500, 500, 500);
        this.korisnici[1] = new KorisnikImpl("060789101", 40, 70, 120, 300, 200, 100);
        this.korisnici[2] = new KorisnikImpl("060112131", 60, 80, 10, 500, 600, 700);
    }

    public Korisnik vratiKorisnika(String broj) throws RemoteException
    {
        for(KorisnikImpl k : korisnici)
        {
            if(k.broj.equals(broj))
            {
                return k;
            }
        }
        return null;
    }
}
