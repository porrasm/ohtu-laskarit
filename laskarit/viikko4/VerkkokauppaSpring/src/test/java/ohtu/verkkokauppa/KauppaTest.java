package ohtu.verkkokauppa;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class KauppaTest {

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaatyttyaTilisiirtoKutsutaanOikein() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        //(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }

    @Test
    public void ostoksenPaatyttyaTilisiirtoKutsutaanOikein2() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        //(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 8);
    }

    @Test
    public void ostoksenPaatyttyaTilisiirtoKutsutaanOikein3() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        //(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
    }

    @Test
    public void ostoksenPaatyttyaTilisiirtoKutsutaanOikein4() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        //(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }

    @Test
    public void aloitaAsiointiNollaaEdellisen() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");



        //(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }

    @Test
    public void ostoaVartenPyydetaanViitenumero() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        //(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);

        //Uusi asiakas

        when(viite.uusi()).thenReturn(43);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("matti", "67890");

        verify(pankki).tilisiirto("matti", 43, "67890", "33333-44455", 5);

        verify(viite, times(2)).uusi();
    }

    @Test
    public void koristaVoiPoistaaTuotteen() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.poistaKorista(2);
        k.tilimaksu("pekka", "12345");

        //(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
}