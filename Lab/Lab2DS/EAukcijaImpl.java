import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class EAukcijaImpl extends UnicastRemoteObject implements EAukcija {
    private EksponatImpl[] eksponati;

    public EAukcijaImpl() throws RemoteException {
        eksponati = new EksponatImpl[10];
        
        eksponati[0] = new EksponatImpl("1111", "Nesto1", 1200);
        eksponati[1] = new EksponatImpl("2222", "Nesto2", 9900);
        eksponati[2] = new EksponatImpl("3333", "Nesto3", 5000);
        eksponati[3] = new EksponatImpl("4444", "NestoTamo1", 75000);
        eksponati[4] = new EksponatImpl("5555", "NestoTamo2", 3000);
    }

    public Eksponat vratiEksponat(String idEksponata) throws RemoteException
    {
        for(EksponatImpl e : eksponati) {
            if(e.vratiId().equals(idEksponata)) {
                return e;
            }
        }
        return null;
    }
}
