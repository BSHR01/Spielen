import java.util.Arrays;

public class Spieler {
    private String name;

    //erster Wert: Spiele insgesamt in dem Spiel
    //zweiter Wert: Spiele gewonnen
    //dritter Wert: Spiele unentschieden
    private int[] statistikSchiffeVersenken;
    private int[] statistikTicTacToe;
    private int[] statistik4Gewinnt;

    public Spieler(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isBlank() || name.isEmpty()) {
            System.out.println("Bitte Name eingeben");
        } else {
            this.name = name;
        }
    }

    public int[] getStatistikSchiffeVersenken() {
        return statistikSchiffeVersenken;
    }

    public void setStatistikSchiffeVersenken(int[] statistikSchiffeVersenken) {
        this.statistikSchiffeVersenken = statistikSchiffeVersenken;
    }

    public int[] getStatistikTicTacToe() {
        return statistikTicTacToe;
    }

    public void setStatistikTicTacToe(int[] statistikTicTacToe) {
        this.statistikTicTacToe = statistikTicTacToe;
    }

    public int[] getStatistik4Gewinnt() {
        return statistik4Gewinnt;
    }

    public void setStatistik4Gewinnt(int[] statistik4Gewinnt) {
        this.statistik4Gewinnt = statistik4Gewinnt;
    }

    @Override
    public String toString() {
        return  "Name = '" + name + '\'' +
                ", Statistik Schiffe versenken =" + Arrays.toString(statistikSchiffeVersenken) +
                ", Statistik Tic Tac Toe =" + Arrays.toString(statistikTicTacToe) +
                ", Statistik 4 Gewinnt =" + Arrays.toString(statistik4Gewinnt) + "\n";
    }
}
