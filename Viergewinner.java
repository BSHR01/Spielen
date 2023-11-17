import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Viergewinner {
    private static String nameVierGewiner;
    private static Spielerverwaltung spielerverwaltung;
   static Scanner sc = new Scanner(System.in);
   static Random random = new Random();

   public  Viergewinner(String nameVierGewiner ,Spielerverwaltung sv){
       setNameVierGewiner(nameVierGewiner);
       spielerverwaltung = sv;
    }

    public void setNameVierGewiner(String nameVierGewiner){
       this.nameVierGewiner = nameVierGewiner;
    }

    public static void startViergewinner(String[][] gewinner) {
        int counter = gewinner.length - 1;
        boolean kontrole = false;
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        while (!kontrole) {
            System.out.println("Geben Sie die Spalte ein von 1 bis " + (gewinner[0].length));
            int col = sc.nextInt() - 1;
            int colPC = random.nextInt(gewinner[0].length);

            if (col < 0 || col >= gewinner[0].length) {
                System.out.println("Ungültige Spalte. Bitte geben Sie eine gültige Spalte ein.");
                continue;
            }

            for (int row = counter; row >= 0; row--) {
                if (gewinner[row][col] == null) {
                    gewinner[row][col] = "\u001B[33m" + "o" + "\u001B[0m";
                    break;
                }
            }

            counter = gewinner.length - 1;

            for (int row = counter; row >= 0; row--) {
                if (gewinner[row][colPC] == null) {
                    gewinner[row][colPC] = "\u001B[31m" + "0" + "\u001B[0m";
                    break;
                }
            }

            counter = gewinner.length - 1;

            printBordviergwinner(gewinner);

            if (hasConnectFour(gewinner, "\u001B[33m" + "o" + "\u001B[0m")) {

                Spieler s = spielerverwaltung.getSpieler(nameVierGewiner);
                int[] stats = s.getStatistik4Gewinnt();
                for(int i = 0; i < stats.length - 1; i++) {
                    stats[i]++;
                }
                s.setStatistik4Gewinnt(stats);
                System.out.println("======" + nameVierGewiner + " hat gewonnen======");

                kontrole = true;
            }
            if (hasConnectFourPC(gewinner, "\u001B[31m" + "0" + "\u001B[0m")){
                System.out.println("======PC hat gewonnen======");
                Spieler s = spielerverwaltung.getSpieler(nameVierGewiner);
                int[] stats = s.getStatistik4Gewinnt();
                stats[2]++;
                s.setStatistikTicTacToe(stats);
                kontrole = true;
            }
            if (hasConnectFourPC(gewinner, "\u001B[31m" + "0" + "\u001B[0m") &&hasConnectFour(gewinner, "\u001B[33m" + "o" + "\u001B[0m") ){
                System.out.println("======Unentschieden======");
                kontrole = true;
            }
            if (kontrole){
                System.out.println("=====Möchten Sie erneut spielen? Wählen Sie 1 für Ja oder 0 für Nein=====");
                int auswahl = sc.nextInt();
                if (auswahl == 1){
                    System.out.println("=====Spiel nochmal start...");
                    startViergewinner(gewinner);
                } else if (auswahl == 0) {
                    System.out.println("=====Tschüss=====");
                }
            }

        }
    }

    public static boolean hasConnectFour(String[][] board, String player) {
        int rows = board.length;
        int cols = board[0].length;

        if (rows < 4 || cols < 4) {
            return false; // Das Spielfeld ist zu klein für einen Connect Four-Gewinner
        }

        // Überprüfung der horizontalen und vertikalen Gewinnmöglichkeiten
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] != null && board[row][col].equals(player)) {
                    // Überprüfung horizontal
                    if (col + 3 < cols &&
                            board[row][col + 1] != null && board[row][col + 1].equals(player) &&
                            board[row][col + 2] != null && board[row][col + 2].equals(player) &&
                            board[row][col + 3] != null && board[row][col + 3].equals(player)) {
                        return true;
                    }

                    // Überprüfung vertikal
                    if (row + 3 < rows &&
                            board[row + 1][col] != null && board[row + 1][col].equals(player) &&
                            board[row + 2][col] != null && board[row + 2][col].equals(player) &&
                            board[row + 3][col] != null && board[row + 3][col].equals(player)) {
                        return true;
                    }

                    // Überprüfung diagonal (von links oben nach rechts unten)
                    if (col + 3 < cols && row + 3 < rows &&
                            board[row + 1][col + 1] != null && board[row + 1][col + 1].equals(player) &&
                            board[row + 2][col + 2] != null && board[row + 2][col + 2].equals(player) &&
                            board[row + 3][col + 3] != null && board[row + 3][col + 3].equals(player)) {
                        return true;
                    }

                    // Überprüfung diagonal (von links unten nach rechts oben)
                    if (col + 3 < cols && row - 3 >= 0 &&
                            board[row - 1][col + 1] != null && board[row - 1][col + 1].equals(player) &&
                            board[row - 2][col + 2] != null && board[row - 2][col + 2].equals(player) &&
                            board[row - 3][col + 3] != null && board[row - 3][col + 3].equals(player)) {
                        return true;
                    }
                }
            }
        }

        return false; // Kein Gewinner gefunden
    }



    public static boolean hasConnectFourPC(String[][] board, String player) {
        int rows = board.length;
        int cols = board[0].length;

        if (rows < 4 || cols < 4) {
            return false; // Das Spielfeld ist zu klein für einen Connect Four-Gewinner
        }

        // Überprüfung der horizontalen und vertikalen Gewinnmöglichkeiten
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] != null && board[row][col].equals(player)) {
                    // Überprüfung horizontal
                    if (col + 3 < cols &&
                            board[row][col + 1] != null && board[row][col + 1].equals(player) &&
                            board[row][col + 2] != null && board[row][col + 2].equals(player) &&
                            board[row][col + 3] != null && board[row][col + 3].equals(player)) {
                        return true;
                    }

                    // Überprüfung vertikal
                    if (row + 3 < rows &&
                            board[row + 1][col] != null && board[row + 1][col].equals(player) &&
                            board[row + 2][col] != null && board[row + 2][col].equals(player) &&
                            board[row + 3][col] != null && board[row + 3][col].equals(player)) {
                        return true;
                    }

                    // Überprüfung diagonal (von links oben nach rechts unten)
                    if (col + 3 < cols && row + 3 < rows &&
                            board[row + 1][col + 1] != null && board[row + 1][col + 1].equals(player) &&
                            board[row + 2][col + 2] != null && board[row + 2][col + 2].equals(player) &&
                            board[row + 3][col + 3] != null && board[row + 3][col + 3].equals(player)) {
                        return true;
                    }

                    // Überprüfung diagonal (von links unten nach rechts oben)
                    if (col + 3 < cols && row - 3 >= 0 &&
                            board[row - 1][col + 1] != null && board[row - 1][col + 1].equals(player) &&
                            board[row - 2][col + 2] != null && board[row - 2][col + 2].equals(player) &&
                            board[row - 3][col + 3] != null && board[row - 3][col + 3].equals(player)) {
                        return true;
                    }
                }
            }
        }

        return false; // Kein Gewinner gefunden
    }



    public static void printBordviergwinner(String [][] gewinner){
        for (int i = 0 ; i < gewinner.length; i++){
            for (int j = 0; j < gewinner[0].length; j++){

                if (gewinner[i][j] != null) {
                    System.out.print("[" + gewinner[i][j] + "]");
                } else {
                    System.out.print("[ ]");
                }




            }
            System.out.println();
        }

    }

}
