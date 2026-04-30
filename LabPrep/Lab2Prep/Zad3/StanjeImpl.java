import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//UnicastRemoteObject - Unicast znaci da je komunikacija jedan na jedan;
//Remote znaci da je objekat dostupan sa druge masine preko mreze
//Object - osnovna Java klasa od koje se sve nasledjuje
public class StanjeImpl extends UnicastRemoteObject implements Stanje {

    public float iznosDinarski;
    public float iznosDevizni;

    public StanjeImpl(float iznosDinarski, float iznosDevizni) throws RemoteException
    {
        this.iznosDinarski = iznosDinarski;
        this.iznosDevizni = iznosDevizni;
    }

    public float vratiDinarskiIznos() throws RemoteException
    {
        return iznosDinarski;
    }

    public float vratiDevizniIznos() throws RemoteException
    {
        return iznosDevizni;
    }
}
