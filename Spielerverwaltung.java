import java.util.Arrays;

public class Spielerverwaltung {
    private Spieler[] spieler;

    public Spielerverwaltung() {
        spieler = new Spieler[100];
    }

    public void addSpieler(String name) {
        if(getSpieler(name) != null) {
            System.out.println("Benutzername ist schon vergeben.");
            return;
        }
        for(int i = 0; i < spieler.length; i++) {
            if(spieler[i] == null) {
                initalisiereSpieler(spieler, i, name);
                return;
            } else if(spieler[i].getName().equalsIgnoreCase(name)) {
                System.out.println("Benutzername ist schon vergeben.");
                return;
            }
        }
        System.out.println("Alle Plätze sind bereits vergeben.");
    }

    private void initalisiereSpieler(Spieler[] spieler, int index, String name) {
        spieler[index] = new Spieler(name);
        spieler[index].setStatistikTicTacToe(new int[]{0, 0, 0});
        spieler[index].setStatistik4Gewinnt(new int[]{0, 0, 0});
        spieler[index].setStatistikSchiffeVersenken(new int[]{0, 0, 0});
        System.out.println("Benutzer wurde erstellt.");
    }

    public void entferneSpieler(String name) {
        if(getSpielerId(name) == -1) {
            System.out.println("Spieler nicht gefunden.");
        } else {
            int i = getSpielerId(name);
            spieler[getSpielerId(name)] = null;
            System.out.println("Spieler wurde entfernt.");
            for(; i < spieler.length - 1; i++) {
                spieler[i] = spieler[i+1];
                spieler[i+1] = null;
            }
        }
    }

    private int getSpielerId(String name) {
        for(int i = 0; i < spieler.length; i++) {
            if(spieler[i] != null && spieler[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Spieler getSpieler(String name) {
        for(Spieler s : spieler) {
            if(s != null && s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public Spieler[] getSpieler() {
        return spieler;
    }

    public void setSpieler(Spieler[] spieler) {
        this.spieler = spieler;
    }

    @Override
    public String toString() {
        return "Alle Spieler: \n" + "Erster Wert der Statistik = Spiele gespielt, Zweiter Wert: Spiele gewonnen, Dritter Wert: Spiele unentschieden \n"  + Arrays.toString(spieler) + "\n";
    }
}
