import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class FileInAnOut {
    File file = new File("spielstaende.txt");
    FileWriter fw = new FileWriter(file, true);
    BufferedWriter writer = new BufferedWriter(fw);

    public FileInAnOut() throws IOException {
    }

    public void writeToFile(Spieler[] spieler) throws IOException {
        Files.writeString(file.toPath(), "", StandardOpenOption.TRUNCATE_EXISTING);
        for (Spieler s : spieler) {
            if (s != null) {
                writer.write(s.getName() + "\n");
                getData(s.getStatistik4Gewinnt());
                getData(s.getStatistikSchiffeVersenken());
                getData(s.getStatistikTicTacToe());
            }
        }
        writer.close();
    }

    private void getData(int[] data) throws IOException {
        for (int i : data) {
            writer.write(i + ";");
        }
        writer.newLine();
    }

    public Spieler[] readFromFile() throws IOException {
        Spieler[] spieler = new Spieler[100];
        Spieler s = null;
        String line = "";
        int i = 0;
        int playerCount = 0;

        String[] splitedLine;
        Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
        while (scanner.hasNextLine()) {
            if (i == 0) {
                line = scanner.nextLine();
                s = new Spieler(line);
                i++;
            } else if (i == 1) {
                splitedLine = scanner.nextLine().split(";");
                s.setStatistik4Gewinnt(new int[]{Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1]), Integer.parseInt(splitedLine[2])});
                i++;
            } else if (i == 2) {
                splitedLine = scanner.nextLine().split(";");
                s.setStatistikSchiffeVersenken(new int[]{Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1]), Integer.parseInt(splitedLine[2])});
                i++;
            } else if (i == 3) {
                splitedLine = scanner.nextLine().split(";");
                s.setStatistikTicTacToe(new int[]{Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1]), Integer.parseInt(splitedLine[2])});
                spieler[playerCount] = s;
                i = 0;
                playerCount++;
            }
        }
        scanner.close();

        return spieler;
    }
}