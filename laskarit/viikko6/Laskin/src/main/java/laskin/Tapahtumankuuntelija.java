package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class Tapahtumankuuntelija implements EventHandler {
    private Button undo;
    private Sovelluslogiikka sovellus;

    private Map<Button, Komento> komennot;
    private Komento edellinen = null;
    private TextField tuloskentta;
    private Button nollaa;


    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.tuloskentta = tuloskentta;
        this.nollaa = nollaa;
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta, sovellus) );
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, sovellus) );
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, sovellus) );
    }

    @Override
    public void handle(Event event) {

        if ( event.getTarget() != undo ) {
            Komento komento = komennot.get(event.getTarget());

            komento.suorita();
            edellinen = komento;
        } else {
            if (edellinen == null) {
                return;
            }
            edellinen.peru();
            edellinen = null;
        }

        int result = sovellus.tulos();
        tuloskentta.setText("" + result);

        if ( result == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }

        if (edellinen == null) {
            undo.disableProperty().set(true);
        } else {
            undo.disableProperty().set(false);
        }
    }
}
