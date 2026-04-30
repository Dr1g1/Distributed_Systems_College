import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PrijavaImpl extends UnicastRemoteObject implements Prijava {

    private ArrayList<String> ispiti;

    public PrijavaImpl() throws RemoteException
    {
        this.ispiti = new ArrayList<>();
    }

    public String vratiIspite() throws RemoteException
    {
        String rezultat = "";
        for(int i = 0; i < ispiti.size(); i++) {
            rezultat += (i + 1) + ". " + ispiti.get(i) + "\n";
        }
        return rezultat;
    }

    public void dodajIspit(String ispit) throws RemoteException
    {
        this.ispiti.add(ispit);
    }
}
