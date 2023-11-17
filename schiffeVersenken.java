
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
public class schiffeVersenken {
    static String spielerName;
    private static Spielerverwaltung spielerverwaltung;
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public schiffeVersenken(String spielerName, Spielerverwaltung sv){
        setSpielerName(spielerName);
        spielerverwaltung = sv;
    }

    public void setSpielerName(String spielerName) {
        this.spielerName = spielerName;
    }
    public String getSpielerName(String spielerName){
        return spielerName;
    }

    public static void schiefeAuswahl(String[][] spieler, String[][] spielerPc) {
        int row = 0, col = 0;
        int counterPC = 0; // Anzahl der Schiffslängen für PC
        int counter = 0; // Anzahl der Schiffslängen für Spieler
        char aussehen; // Ausrichtung (horizontal 'h' oder vertikal 'v')
        int counterSchiffeAnzahl = 0; // Zählt, wie oft die Schleife durchlaufen wurde
        int Auswahl; // Auswahl für die Switch-Anweisung
        boolean positionValid = false; // Überprüft, ob die Position gültig ist
        boolean sindAlleSchiffeDa = false; // Überprüft, ob alle Schiffe platziert wurden; bei Erfüllung wird die Methode 'schiffeAuswahl' aufgerufen
        System.out.println("Bitte wählen Sie eines der folgenden Schiffe zum Platzieren:");
        System.out.println("Hinweis: Bitte verwenden Sie die Nummerierung (1, 2, 3, 4) für Ihre Auswahl.");
        System.out.println("Sobald Sie alle Schiffe platziert haben, geben Sie die Zahl 5 ein.");
        Auswahl = sc.nextInt();

        if (Auswahl == 5){
            sindAlleSchiffeDa = true;
        }
       counterSchiffeAnzahl = switch (Auswahl) {
            case 1 -> {
                System.out.println("======Schlachtschiff (Länge 5)=======");
                yield 5;

            }
            case 2 -> {
                System.out.println("======Kreuzer (Länge 4)=======");
                yield 4;
            }
            case 3 -> {
                System.out.println("======Zerstörer (Länge 3)=======");
                yield 3;
            }
            case 4 -> {
                System.out.println("======U-Boote  (Länge 2)=======");
                yield 2;
            }

            default -> counterSchiffeAnzahl;
        };


        if (!sindAlleSchiffeDa){
        System.out.println("Geben Sie char Horizontal(h) oder Vertikal(v) ein");
        aussehen = sc.next().charAt(0);
        if (aussehen == 'h') {
            System.out.println("Enter the number of row: ");
            row = sc.nextInt();
        } else if (aussehen == 'v') {
            System.out.println("Enter the number of col: ");
            col = sc.nextInt();
        }
              // schiffe einlegen

          if (row >= 0 && row <= 10 && col >= 0 && col <= 10 ) {
                for (int i = 0; i < counterSchiffeAnzahl; i++) {
                    if (aussehen == 'h') {
                        if (spieler[row - 1][counter] == null) {
                            spieler[row - 1][counter++] = "s";

                        } else {
                            positionValid = true; // Die Position ist nicht null
                            System.out.println("Fehlermeldung: Position ist nicht null. Bitte andere Position auswählen.");
                            break; // Beenden die Schleife
                        }
                    } else if (aussehen == 'v') {
                        if (spieler[counter][col - 1] == null) {
                            spieler[counter++][col - 1] = "s";
                        } else {
                            positionValid = true; // Die Position ist nicht null
                            System.out.println("Fehlermeldung: Position ist nicht null. Bitte andere Position auswählen.");
                            break; // Beenden die Schleife

                        }
                    }
                }

              if (positionValid) { // wenn der user falsche pos die nicht null ist eingegeben
                  schiefeAuswahl(spieler, spielerPc);
              }else { // wenn der user Richtige eingegeben start Random PC
                  // RandomPC :)
                  int rowPC;

                  rowPC = random.nextInt(1, 10);
                  // Vor der Schleife initialisieren




                  for (int shipCount = 0; shipCount < counterSchiffeAnzahl; shipCount++) {
                      if (spielerPc[rowPC][counterPC] == null) {
                          spielerPc[rowPC][counterPC++] ="s";

                      } else if (spielerPc[rowPC][counterPC] != null) {
                          spielerPc[rowPC][counterPC++] = "s";

                      } else if (spielerPc[counterPC][rowPC] != null) {
                          spielerPc[rowPC][counterPC++] = "s";

                      }

                  }



                  // Random PC :)
                  printBothMatrices(spieler, spielerPc);
                  schiefeAuswahl(spieler, spielerPc);
              }

            } else {
                System.out.println("Fehlermeldung: row und col dürfen nicht mehr als 10 sein. Bitte andere Position auswählen.");
            }
        }


        else { // Wenn entweder alle Schiffe platziert wurden oder der Fall 4 eingetreten ist
            printBothMatrices(spieler, spielerPc);
            System.out.println("\t\u001B[32mDas Spiel startet jetzt...\u001B[0m");
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;

            while (schiffeSchiessen(spieler, spielerPc) && istEndeSpiel(spieler, spielerPc)) {
                System.out.println("Das Spiel läuft..."); // Fortschrittsanzeige
                elapsedTime = System.currentTimeMillis() - startTime;

                if (elapsedTime >= 500) { // Verzögerung von 500 Millisekunden
                    startTime = System.currentTimeMillis(); // Setze die Startzeit neu
                    printBothMatrices(spieler, spielerPc);
                }
                }
            }
        }
    public static boolean schiffeSchiessen(String[][] spieler, String[][] spielerPc) {
        boolean wiederholen = false;

        System.out.println("Bitte wählen Sie eine Position auf dem PC-Spielfeld zum Abschuss aus, z.B. 1.2: ");
        int row = sc.nextInt() - 1; // Um die Benutzereingabe an den Array-Index anzupassen
        int col = sc.nextInt() - 1;

        String reset = "\u001B[0m";
        String redColor = "\u001B[31m";
        String greenColor = "\u001B[32m";
        String blueColor = "\u001B[34m";

        if (row >= 0 && row < spielerPc.length && col >= 0 && col < spielerPc[0].length) {
            /// schiessen User
            if (spielerPc[row][col] == null) {
                spielerPc[row][col] = "f";
                System.out.println(blueColor + "Es tut uns leid, aber das Wasser ist leer. Es wurden keine Schiffe getroffen." + reset);
            } else if (spielerPc[row][col].equalsIgnoreCase("s")) {
                spielerPc[row][col] = "x";
                System.out.println(greenColor + "Treffer! Ein Schiff wurde getroffen." + reset);
            }

            /// schiessen PC
            int rowPc, colPc;
            int counter = 1; // wenn counter 3 f dann muss x eintreffen
            do {
                rowPc = random.nextInt(0, 10); // Achten Sie darauf, dass der obere Grenzwert exklusiv ist (0-9).
                colPc = random.nextInt(0, 10);

                if (spieler[rowPc][colPc] == null) {
                    spieler[rowPc][colPc] = "\u001B[41m\u001B[30mf\u001B[0m";

                    if (counter >= 3) {
                        boolean xPlaced = false;
                        while (!xPlaced) {
                            rowPc = random.nextInt(0, 10);
                            colPc = random.nextInt(0, 10);

                            if (Objects.equals(spieler[rowPc][colPc], "\u001B[44m\u001B[34m[s]\u001B[0m")) {
                                spieler[rowPc][colPc] = "\u001B[42m\u001B[30mx\u001B[0m";
                                xPlaced = true;
                            }
                        }
                        counter = 0; // Reset des Zählers, da das "x" platziert wurde
                    }

                    counter++;
                }
            } while (spieler[rowPc][colPc] == null && !Objects.equals(spieler[rowPc][colPc], "f"));
            // Die Schleife muss ordnungsgemäß geschlossen werden

            return true;
        } else {
            System.out.println("Ungültige Eingabe. Row und col müssen zwischen 1 und " + spielerPc.length + " liegen.");
            wiederholen = true;
            schiffeSchiessen(spieler, spielerPc);
        }

        return wiederholen;
    }


    public static boolean istEndeSpiel(String[][] spieler, String[][] spielerPc) {
        int counterBuch_S_Spieler = 0;
        int counterBuch_S_SpielerPC = 0;
        boolean ende = true;

        for (String[] strings : spieler) {
            for (String string : strings) {
                if (Objects.equals(string, "s")) {
                    counterBuch_S_Spieler++;
                }
            }
        }

        for (String[] strings : spielerPc) {
            for (String string : strings) {
                if (Objects.equals(string, "s")) {
                    counterBuch_S_SpielerPC++;
                }
            }

        }
        if (counterBuch_S_SpielerPC == 0) {
            ende = false;
            Spieler s = spielerverwaltung.getSpieler(spielerName);
            //System.out.println(Arrays.toString(s.getStatistikSchiffeVersenken()));
            int[] stats = s.getStatistikSchiffeVersenken();
            for(int i = 0; i < stats.length - 1; i++) {
                stats[i]++;
            }
            //System.out.println(Arrays.toString(s.getStatistikSchiffeVersenken()));
            s.setStatistikSchiffeVersenken(stats);
            //System.out.println(Arrays.toString(s.getStatistikSchiffeVersenken()));
            System.out.println("Gratuliere " + spielerName + " hat gewonnen");
        }
        if (counterBuch_S_Spieler == 0) {
            ende = false;
            Spieler s = spielerverwaltung.getSpieler(spielerName);
            //System.out.println(Arrays.toString(s.getStatistikSchiffeVersenken()));
            int[] stats = s.getStatistikSchiffeVersenken();
            stats[2]++;
            //System.out.println(Arrays.toString(s.getStatistikSchiffeVersenken()));
            s.setStatistikSchiffeVersenken(stats);
            System.out.println("PC hat gewonnen");

        }

        return ende;
    }



    public static void printBothMatrices(String[][] playerMatrix, String[][] pcMatrix) {
        int rows = playerMatrix.length;
        int cols = playerMatrix[0].length;
        System.out.print("    ");
        for (int j = 1; j <= cols; j++) {
            System.out.print(j+"  ");
        }
        System.out.print("\t\t\t\t\t");
        for (int j = 1; j <= cols; j++) {
            System.out.print(j+"  ");
        }
        System.out.print("\n");

        char rowLetter = 'A';

        for (int i = 0; i < rows; i++) {
            System.out.print(rowLetter + "  ");
            for (int j = 0; j < cols; j++) {
                if (playerMatrix[i][j] != null) {
                    System.out.print("[" + playerMatrix[i][j] + "]");
                }else {
                    System.out.print("[ ]");
                }
            }

            System.out.print("\t\t\t\t");

            System.out.print(rowLetter + "  ");
            for (int j = 0; j < cols; j++) {
                if (pcMatrix[i][j] != null && pcMatrix[i][j].equalsIgnoreCase("s")) {
                    System.out.print("\u001B[44m\u001B[34m[" + pcMatrix[i][j] + "]\u001B[0m");
                } else if (pcMatrix[i][j] != null && pcMatrix[i][j].equalsIgnoreCase("x")) {
                    System.out.print("\u001B[42m\u001B[30m[" + pcMatrix[i][j] + "]\u001B[0m");
                } else if (pcMatrix[i][j] != null && pcMatrix[i][j].equalsIgnoreCase("f")) {
                    System.out.print("\u001B[41m\u001B[30m[" + pcMatrix[i][j] + "]\u001B[0m");
                } else {
                    System.out.print("\u001B[44m\u001B[34m[ ]\u001B[0m");
                }
            }

            System.out.println();
            rowLetter++;
        }
    }






}