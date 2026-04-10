package layout;

import config.GlobalConfig;
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

    static private final String PATH = "src/main/java/layout/mapTitle/";

    // ANSI Color Codes
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void mapBorders(HyruleRegion region, Weather weather) {
        WorldBuilder worldBuilder = new WorldBuilder();
        Tile[][] generatedMap = worldBuilder.buildRegion(region);

        String[][] screenBuffer = new String[GlobalConfig.MAP_HEIGHT][GlobalConfig.MAP_WIDTH];

        // 1. Fill Map
        for (int y = 0; y < GlobalConfig.MAP_HEIGHT; y++) {
            for (int x = 0; x < GlobalConfig.MAP_WIDTH; x++) {
                screenBuffer[y][x] = String.valueOf(generatedMap[y][x].getTerrainType().getSymbol());
            }
        }

        // 2. Overlays
        drawOuterBorders(screenBuffer);
        drawRegionNameBox(screenBuffer, region);
        drawWeatherAndTempBoxes(screenBuffer, region, weather);

        // 3. Draw Player
        screenBuffer[GlobalConfig.MAP_HEIGHT / 2][GlobalConfig.MAP_WIDTH / 2] = "\u1FFA";

        // 4. Print Map
        for (int y = 0; y < GlobalConfig.MAP_HEIGHT; y++) {
            for (int x = 0; x < GlobalConfig.MAP_WIDTH; x++) {
                System.out.print(screenBuffer[y][x]);
            }
            System.out.println();
        }
    }

    private static void drawOuterBorders(String[][] buffer) {
        for (int x = 0; x < GlobalConfig.MAP_WIDTH; x++) {
            buffer[0][x] = "═";
            buffer[GlobalConfig.MAP_HEIGHT - 1][x] = "═";
        }
        for (int y = 0; y < GlobalConfig.MAP_HEIGHT; y++) {
            buffer[y][0] = "║";
            buffer[y][GlobalConfig.MAP_WIDTH - 1] = "║";
        }
        buffer[0][0] = "╔";
        buffer[0][GlobalConfig.MAP_WIDTH - 1] = "╗";
        buffer[GlobalConfig.MAP_HEIGHT - 1][0] = "╚";
        buffer[GlobalConfig.MAP_HEIGHT - 1][GlobalConfig.MAP_WIDTH - 1] = "╝";
    }

    private static void drawRegionNameBox(String[][] buffer, HyruleRegion region) {
        String areaName = region.name().toLowerCase();
        List<String> artLines = getFileAsListOfStrings(PATH + areaName + ".txt");

        if (artLines.isEmpty()) return;

        int artHeight = artLines.size();
        int artWidth = 0;
        for (String line : artLines) {
            artWidth = Math.max(artWidth, line.length());
        }

        int boxBottom = artHeight + 1;
        int boxRight = artWidth + 2;

        for (int y = 1; y < boxBottom; y++) {
            for (int x = 1; x < boxRight; x++) {
                buffer[y][x] = " ";
            }
        }

        for (int y = 0; y < artHeight; y++) {
            String line = artLines.get(y);
            for (int x = 0; x < line.length(); x++) {
                buffer[y + 1][x + 1] = String.valueOf(line.charAt(x));
            }
        }

        for (int x = 1; x < boxRight; x++) buffer[boxBottom][x] = "═";
        for (int y = 1; y < boxBottom; y++) buffer[y][boxRight] = "║";

        buffer[0][boxRight] = "╦";
        buffer[boxBottom][0] = "╠";
        buffer[boxBottom][boxRight] = "╝";
    }

    private static void drawWeatherAndTempBoxes(String[][] buffer, HyruleRegion region, Weather weather) {
        int boxTop = GlobalConfig.MAP_HEIGHT - 3;
        int tempLeftEdge = GlobalConfig.MAP_WIDTH - 12; // Adjusted sizes for just symbols
        int middleEdge = GlobalConfig.MAP_WIDTH - 6;
        int rightEdge = GlobalConfig.MAP_WIDTH - 1;

        for (int y = boxTop + 1; y < GlobalConfig.MAP_HEIGHT - 1; y++) {
            for (int x = tempLeftEdge + 1; x < rightEdge; x++) {
                if (x != middleEdge) buffer[y][x] = " ";
            }
        }

        for (int x = tempLeftEdge + 1; x < rightEdge; x++) buffer[boxTop][x] = "═";

        for (int y = boxTop + 1; y < GlobalConfig.MAP_HEIGHT - 1; y++) {
            buffer[y][tempLeftEdge] = "║";
            buffer[y][middleEdge] = "║";
        }

        buffer[boxTop][tempLeftEdge] = "╔";
        buffer[boxTop][middleEdge] = "╦";
        buffer[boxTop][rightEdge] = "╣";
        buffer[GlobalConfig.MAP_HEIGHT - 1][tempLeftEdge] = "╩";
        buffer[GlobalConfig.MAP_HEIGHT - 1][middleEdge] = "╩";

        // Inject Dynamic Colored Temperature Symbol
        String color = getTemperatureColor(region, weather);
        buffer[boxTop + 1][tempLeftEdge + 3] = color + "●" + ANSI_RESET;

        // Inject Weather Symbol
        buffer[boxTop + 1][middleEdge + 2] = weather.getSymbol();
    }

    private static String getTemperatureColor(HyruleRegion region, Weather weather) {
        if (region == HyruleRegion.ELDIN || weather == Weather.SCORCHING_CLIMATE) return ANSI_RED;
        if (region == HyruleRegion.GERUDO || weather == Weather.HEAT) return ANSI_YELLOW; // Acts as pale yellow/orange
        if (region == HyruleRegion.HEBRA || weather == Weather.SNOW) return ANSI_BLUE;
        if (weather == Weather.THUNDERSTORM || weather == Weather.RAIN) return ANSI_CYAN; // Light Blue
        return ANSI_WHITE;
    }

    private static List<String> getFileAsListOfStrings(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Could not load map title art from: " + fileName);
        }
        return lines;
    }
}