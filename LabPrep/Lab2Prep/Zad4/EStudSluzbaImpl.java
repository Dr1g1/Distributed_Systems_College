import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EStudSluzbaImpl extends UnicastRemoteObject implements EStudSluzba {
    
    private Student[] studenti;

    public EStudSluzbaImpl() throws RemoteException
    {
        studenti = new Student[3];

        studenti[0] = new StudentImpl("19167");
        studenti[1] = new StudentImpl("19169");
        studenti[2] = new StudentImpl("19999");
    }

    public Student vratiStudenta(String brIndeksa) throws RemoteException
    {
        for(Student s : studenti)
        {
            if(s.getIndeks().equals(brIndeksa))
                return s;
        }
        return null;
    }
}
