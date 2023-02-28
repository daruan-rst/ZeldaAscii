package layout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Land {

    static final int HEIGHT = 50;
    static final int WIDTH = 150;

    static final int NAME_BOX_HEIGHT = 2;
    static final int NAME_BOX_WIDTH = 20;

    static private final String PATH = "src/main/java/layout/mapTitle/";


    public static void mapBorders(String areaName) {
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
                    System.out.print("║");
                } else if ((i == HEIGHT && (j < WIDTH) && j > 0) ||
                        (i == 0 && (j < WIDTH) && j > NAME_BOX_WIDTH + 1)) {
                    System.out.print("═");
                } else if ((i == NAME_BOX_HEIGHT + 1 && (j == 0))) {
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
                } else if (i < NAME_BOX_HEIGHT + 1 && (j == NAME_BOX_WIDTH + 1)) {
                    System.out.print("║");
                } else if (j < NAME_BOX_WIDTH + 1 && (i == NAME_BOX_HEIGHT + 1)) {
                    System.out.print("═");
                } else {
                    System.out.print("░");
                }
            }
            System.out.print("\n");
        }
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
