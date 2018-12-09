package laskin;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa implements Komento {

    TextField tuloskentta, syotekentta;
    Sovelluslogiikka sovellus;

    private int edellinen;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        edellinen = sovellus.tulos();
        sovellus.nollaa();
    }
    @Override
    public  void peru() {
        sovellus.nollaa();
        sovellus.plus(edellinen);
    }
}
