import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class TicTak {
    static String SpielernameTiktak;
    private static Spielerverwaltung spielerverwaltung;
    private String[][] tiktak;

    public TicTak(String SpielernameTiktak ,Spielerverwaltung svTik) {
         setSpielernameTikatak(SpielernameTiktak);
        spielerverwaltung = svTik;
    }

    public void setSpielernameTikatak(String spielernameTiktak) {
        this.SpielernameTiktak=spielernameTiktak;
    }

    public static String getSpielernameTiktak() {
        return SpielernameTiktak;
    }

    public static void tiktakSpiel(String[][] tiktak) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int playerTurn = 0;

        while (isfreiPos(tiktak)) {
            int row, col;
            if (playerTurn == 0) {
                anzeigen(tiktak);
                System.out.println("Spieler, geben Sie die Position ein (z.B. 0 1): ");
                row = sc.nextInt();
                col = sc.nextInt();
            } else {
                do {
                    row = random.nextInt(3);
                    col = random.nextInt(3);
                } while (tiktak[row][col] != null);
            }

            if (row >= 0 && row < tiktak.length && col >= 0 && col < tiktak[0].length && tiktak[row][col] == null) {
                tiktak[row][col] = (playerTurn == 0) ? "x" : "o";
                playerTurn = 1 - playerTurn;
            } else {
                System.out.println("Ungültige Position oder bereits besetzt. Bitte erneut eingeben.");
            }

            if (hatGewonnen(tiktak, "x")) {
                anzeigen(tiktak);
                Spieler s = spielerverwaltung.getSpieler(SpielernameTiktak);
                int[] stats = s.getStatistikTicTacToe();
                for(int i = 0; i < stats.length - 1; i++) {
                    stats[i]++;
                }
                s.setStatistikTicTacToe(stats);
                System.out.println("Spieler " + SpielernameTiktak + " hat gewonnen!");
                break;
            } else if (hatGewonnen(tiktak, "o")) {
                anzeigen(tiktak);
                Spieler s = spielerverwaltung.getSpieler(SpielernameTiktak);
                int[] stats = s.getStatistikTicTacToe();
                stats[2]++;
                s.setStatistikTicTacToe(stats);
                System.out.println("PC hat gewonnen!");
                break;
            }
        }
        anzeigen(tiktak);
        System.out.println("Das Spiel ist beendet.");
    }

    public static boolean isfreiPos(String[][] tiktak) {
        for (int i = 0; i < tiktak.length; i++) {
            for (int j = 0; j < tiktak[i].length; j++) {
                if (tiktak[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hatGewonnen(String[][] tiktak, String symbol) {
        for (int i = 0; i < 3; i++) {
            if (Objects.equals(tiktak[i][0], symbol) && Objects.equals(tiktak[i][1], symbol) && Objects.equals(tiktak[i][2], symbol)) {
                return true; // horizontale Gewinner
            }
            if (Objects.equals(tiktak[0][i], symbol) && Objects.equals(tiktak[1][i], symbol) && Objects.equals(tiktak[2][i], symbol)) {
                return true; // vertikale Gewinner
            }
        }
        if (Objects.equals(tiktak[0][0], symbol) && Objects.equals(tiktak[1][1], symbol) && Objects.equals(tiktak[2][2], symbol)) {
            return true; // diagonale Gewinner (von links oben nach rechts unten)
        }
        if (Objects.equals(tiktak[0][2], symbol) && Objects.equals(tiktak[1][1], symbol) && Objects.equals(tiktak[2][0], symbol)) {
            return true; // diagonale Gewinner (von rechts oben nach links unten)
        }
        return false;
    }

    public static void anzeigen(String[][] tiktak) {
        for (int i = 0; i < tiktak.length; i++) {
            for (int j = 0; j < tiktak[i].length; j++) {
                if (tiktak[i][j] != null) {
                    System.out.print("[" + tiktak[i][j] + "]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }



}