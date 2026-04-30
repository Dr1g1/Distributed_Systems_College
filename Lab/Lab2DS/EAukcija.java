import java.rmi.*;

public interface EAukcija extends Remote {
    public Eksponat vratiEksponat(String eksponatId) throws RemoteException;
}
