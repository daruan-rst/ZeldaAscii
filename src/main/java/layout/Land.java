package layout;

import enums.Weather;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Land {

    static final String MAP_FILE_PATH = "src/main/java/layout/maps/map.txt";
    static final List<String[]> MAP = readMapFile();

    static final int HEIGHT = 22;
    static final int WIDTH = 150;

    static final int NAME_BOX_HEIGHT = 2;
    static final int NAME_BOX_WIDTH = 20;

    static private final String PATH = "src/main/java/layout/mapTitle/";


    public static void mapBorders(String areaName, Weather weather) {
        List<String> lines = getFileAsListOfStrings(PATH + areaName + ".txt");
        int name_height = lines.size();
        int name_width =  lines.isEmpty() ? 0 : lines.get(0).length();
        for (int i = 0; i <= HEIGHT; i++) {
            for (int j = 0; j <= WIDTH; j++) {
                if (i <= NAME_BOX_HEIGHT && j <= NAME_BOX_WIDTH) {
                    if (j < name_width ){
                        System.out.print(getCurrentFileChar(i, j, lines));
                    }else{
                        System.out.print(" ");
                    }
                } else if ((j == WIDTH && (i < HEIGHT) && i > 0) ||
                        (j == 0 && (i < HEIGHT) && i > NAME_BOX_HEIGHT + 1)) {
                    if (i == HEIGHT -2 && j == WIDTH){
                        System.out.print("╣");
                    }else {
                        System.out.print("║");
                    }
                } else if ((i == HEIGHT && (j < WIDTH) && j > 0) ||
                        (i == 0 && (j < WIDTH) && j > NAME_BOX_WIDTH + 1) ||
                        (i == HEIGHT-2 && (j == WIDTH -1))) { // weather box
                    if ((i == HEIGHT && (j == WIDTH -2))){ // weather box
                        System.out.print("╩");
                    }else {
                        System.out.print("═");
                    }
                } else if ((i == NAME_BOX_HEIGHT + 1 && (j == 0)) ||
                    (i == HEIGHT-2 && (j == WIDTH -2))) { // weather box
                    System.out.print("╔");
                } else if (i == 0 && j == WIDTH) {
                    System.out.print("╗");
                } else if (i == HEIGHT && j == 0) {
                    System.out.print("╚");
                } else if (i == HEIGHT && j == WIDTH) {
                    System.out.print("╝");
                } else if (i == NAME_BOX_HEIGHT + 1 && j == NAME_BOX_WIDTH + 1) {
                    System.out.print("╝");
                } else if (i == 0 && j == NAME_BOX_WIDTH + 1) {
                    System.out.print("╔");
                } else if (i < NAME_BOX_HEIGHT + 1 && (j == NAME_BOX_WIDTH + 1) ||
                    (i == HEIGHT-1 && (j == WIDTH -2) )) { // weather box
                    System.out.print("║");
                } else if (j < NAME_BOX_WIDTH + 1 && (i == NAME_BOX_HEIGHT + 1)) {
                    System.out.print("═");
                } else if (j == WIDTH - 1 && (i == HEIGHT - 1)) {
                    System.out.print(weather.getSymbol()); // weather box
                }
                else {
//                    System.out.print("░");
                    System.out.print(getCharacterFromMap(i, j));
                }
            }
            System.out.print("\n");
        }
    }

    private static String getCharacterFromMap(int i, int j) {
        return MAP.get(i)[j];

    }

    private static List<String[]> readMapFile() {

        List<String[]> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(MAP_FILE_PATH))){
            String line = "";
            while ((line = br.readLine()) != null){
                String[] elements = line.split("");
                lines.add(elements);
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return lines;
    }

    private static String getCurrentFileChar(int i, int j, List<String> lines){
        return lines.isEmpty() ? " " : Character.toString(lines.get(i).charAt(j));
    }

    private static List<String> getFileAsListOfStrings(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
