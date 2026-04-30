import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EAukcijaImpl extends UnicastRemoteObject implements EAukcija {
    
    private EksponatImpl[] eksponati;

    public EAukcijaImpl() throws RemoteException {
        eksponati = new EksponatImpl[5];
        eksponati[0] = new EksponatImpl("EKS_997", "Slika Mona Liza", 1200);
        eksponati[1] = new EksponatImpl("EKS_991", "Rimska vaza", 99200);
        eksponati[2] = new EksponatImpl("EKS_992", "Zlatni sat", 5000);
        eksponati[3] = new EksponatImpl("EKS_993", "Dijamantski prsten", 75000);
        eksponati[4] = new EksponatImpl("EKS_994", "Anticki novcanik", 3000);
    }

    public Eksponat vratiEksponat(String idEksponata) throws RemoteException {
        for (EksponatImpl e : eksponati) {
            if(e.vratiId().equals(idEksponata)) {
                return e;
            }
        }
        return null;
    }
}
