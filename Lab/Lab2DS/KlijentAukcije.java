import java.io.Serializable;

public class KlijentAukcije implements Serializable {
    private String  klijentAukcijeId;
    private String ime;
    private String prezime;

    public KlijentAukcije(String klijentAukcijeId, String ime, String prezime) {
        this.klijentAukcijeId = klijentAukcijeId;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getId() { return klijentAukcijeId; }
    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
}
