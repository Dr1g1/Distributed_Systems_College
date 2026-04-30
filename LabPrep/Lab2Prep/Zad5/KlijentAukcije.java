import java.io.Serializable;

// klasa implementira Serializable zato sto je potrebno kopirati celu instancu i  preneti je preko mreze
// Serializable - za kad hocemo niz bajtova da prenesemo mrezom ili cuvamo u fajl
public class KlijentAukcije implements Serializable {
    public String klijentAukcijeId;
    public String ime;
    public String prezime;

    public KlijentAukcije(String klijentAukcijeId, String ime, String prezime)
    {
        this.klijentAukcijeId = klijentAukcijeId;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getId() { return klijentAukcijeId; }
    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
}
