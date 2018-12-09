package laskin;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa implements Komento {

    TextField tuloskentta, syotekentta;
    Sovelluslogiikka sovellus;

    private int edellinen;

    public Summa(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {

        try {
            int luku = Integer.parseInt(syotekentta.getText());
            sovellus.plus(luku);
            edellinen = luku;
        } catch (Exception e) {
            edellinen = 0;
        }
    }
    @Override
    public  void peru() {
        sovellus.miinus(edellinen);
    }
}
