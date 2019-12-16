package MVC.Model;

public class Biljett {
    private String biljettNummer;
    private boolean upptagen;

    public Biljett(String biljettNummer){
        this.biljettNummer = biljettNummer;
        this.upptagen = false;
    }

    public String getBiljettNummer() {
        return biljettNummer;
    }

    public boolean isUpptagen() {
        return upptagen;
    }
}
