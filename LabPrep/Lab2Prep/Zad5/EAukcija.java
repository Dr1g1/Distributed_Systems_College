import java.rmi.*;

public interface EAukcija extends Remote {
    Eksponat vratiEksponat(String idEksponata) throws RemoteException;
}