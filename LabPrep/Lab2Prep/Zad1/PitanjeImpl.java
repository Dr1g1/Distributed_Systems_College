import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PitanjeImpl extends UnicastRemoteObject implements Pitanje {
    public String pitanje;
    public String a;
    public String b;
    public String c;

    public PitanjeImpl(String pitanje, String a, String b, String c) throws RemoteException
    {
        this.pitanje = pitanje;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String vratiTekst() throws RemoteException {
        // TODO Auto-generated method stub
        return pitanje + "\na) " + a + "  b) " + b + "  c) " + c;
    }
}
