import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KvizImpl extends UnicastRemoteObject implements Kviz{
    public int brPoena = 0;
    public int indeks = 0;
    public Pitanje[] pitanja;
    public String[] tacniOdgovori;

    public KvizImpl() throws RemoteException
    {
        pitanja = new Pitanje[3];
        tacniOdgovori = new String[3];

        pitanja[0] = new PitanjeImpl("1+1= ?", "1", "2", "3");
        pitanja[1] = new PitanjeImpl("2*3= ?","6","2","1");
        pitanja[2] = new PitanjeImpl("10/2= ?","1","2","5");

        tacniOdgovori[0] = "b";
        tacniOdgovori[1] = "a";
        tacniOdgovori[2] = "c";
    }

    @Override
    public void pocetak() throws RemoteException
    {
        brPoena = 0;
        indeks = 0;
    }

    @Override
    public Pitanje vratiPitanje() throws RemoteException
    {
        return pitanja[indeks];
    }
    
    @Override
    public void odgovori(String odg) throws RemoteException
    {
        if(odg.equals(tacniOdgovori[indeks]))
        {
            brPoena++;
        }
        indeks++;
    }
    
    @Override
    public int vratiBrojPoena() throws RemoteException
    {
        return brPoena;
    }
}

