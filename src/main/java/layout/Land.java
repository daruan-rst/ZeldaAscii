package layout;

import enums.HyruleRegion;
import enums.Weather;
import world.Tile;
import world.WorldBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Land {

    // Removed the static MAP_FILE_PATH and the readMapFile() method

    static final int HEIGHT = 22;
    static final int WIDTH = 150;

    static final int NAME_BOX_HEIGHT = 2;
    static final int NAME_BOX_WIDTH = 20;

    static private final String PATH = "src/main/java/layout/mapTitle/";

    public static void mapBorders(HyruleRegion region, Weather weather) {
        // Derive the string name from the enum to load the correct ASCII title art (e.g., eldin.txt)
        String areaName = region.name().toLowerCase();
        List<String> lines = getFileAsListOfStrings(PATH + areaName + ".txt");
        int name_width =  lines.isEmpty() ? 0 : lines.get(0).length();

        // Dynamically build our region map!
        WorldBuilder worldBuilder = new WorldBuilder();
        Tile[][] generatedMap = worldBuilder.buildRegion(region);

        for (int i = 0; i <= HEIGHT; i++) {
            for (int j = 0; j <= WIDTH; j++) {

                if (i <= NAME_BOX_HEIGHT && j <= NAME_BOX_WIDTH) {
                    if (j < name_width ){
                        System.out.print(getCurrentFileChar(i, j, lines));
                    }else{
                        // I changed this from "" to " " to keep the grid aligned properly
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
                    if ((i == HEIGHT && (j == WIDTH -5)) || // weather box
                            (i == HEIGHT && (j == WIDTH -7))){
                        System.out.print("╩");
                    }else {
                        System.out.print("═");
                    }
                } else if ((i == NAME_BOX_HEIGHT + 1 && (j == 0)) ||
                        (i == HEIGHT-2 && (j == WIDTH -5))) { // weather box
                    System.out.print("╦");
                } else if ((i == NAME_BOX_HEIGHT + 1 && (j == 0)) ||
                        (i == HEIGHT-2 && (j == WIDTH -7))) { // stealth box
                    System.out.print("╔");
                }else if (i == 0 && j == WIDTH) {
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
                        (i == HEIGHT-1 && (j == WIDTH -5) )|| // weather box
                        (i == HEIGHT-1 && (j == WIDTH - 7)))  { // stealth box
                    System.out.print("║");
                } else if (j < NAME_BOX_WIDTH + 1 && (i == NAME_BOX_HEIGHT + 1)) {
                    System.out.print("═");
                } else if (
                        ((j > WIDTH - 5) && (j < WIDTH - 3))
                                &&
                                ((i == HEIGHT - 1))

                )
                {
                    System.out.print(weather.getSymbol()); // weather box
                }
                else {
                    // Pass our dynamic map array to fetch the correct symbol
                    System.out.print(getCharacterFromMap(i, j, generatedMap));
                }
            }
            System.out.print("\n");
        }
    }

    private static String getCharacterFromMap(int i, int j, Tile[][] map) {
        // If it's the center, draw the character. Otherwise, get the terrain symbol.
        return i == HEIGHT / 2 && j == WIDTH / 2 ?
                "\u1FFA" :
                Character.toString(map[i][j].getTerrainType().getSymbol());
    }

    private static String getCurrentFileChar(int i, int j, List<String> lines){
        return lines.isEmpty() ? " " : Character.toString(lines.get(i).charAt(j));
    }

    private static List<String> getFileAsListOfStrings(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Could not read map title art: " + fileName);
        }
        return lines;
    }
}