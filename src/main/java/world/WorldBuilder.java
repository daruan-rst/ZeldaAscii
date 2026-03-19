package world;

import enums.HyruleRegion;

import enums.HyruleRegion;
import enums.TerrainType;

import java.util.Random;

public class WorldBuilder {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 22;
    private final Random random = new Random();

    public Tile[][] buildRegion(HyruleRegion region) {
        Tile[][] map = new Tile[HEIGHT][WIDTH];
        RegionConfig config = getRegionConfig(region);

        // 1. Initialize map with the region's base terrain
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                map[y][x] = new Tile(config.baseTerrain);
            }
        }

        // 2. Iterate map to seed and recursively expand geographic features
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                // Try to spawn each possible terrain feature
                for (TerrainType type : TerrainType.values()) {
                    if (type == config.baseTerrain) continue; // Skip base terrain

                    double spawnChance = config.getSpawnChance(type);

                    if (spawnChance > 0 && random.nextDouble() <= spawnChance) {
                        double spreadChance = config.getSpreadChance(type);
                        // Call the recursive mapping method
                        expandFeatureRecursively(map, x, y, type, spreadChance);
                    }
                }
            }
        }

        return map;
    }

    /**
     * The recursive function that generates lakes, mountains, or deserts.
     *
     * @param map The grid being generated
     * @param x Current X coordinate
     * @param y Current Y coordinate
     * @param type The terrain being generated
     * @param spreadProbability The chance this tile becomes the terrain (decreases per call to avoid infinite loops)
     */
    private void expandFeatureRecursively(Tile[][] map, int x, int y, TerrainType type, double spreadProbability) {
        // Base case 1: Out of bounds
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return;
        }

        // Base case 2: Tile is already of this type
        if (map[y][x].getTerrainType() == type) {
            return;
        }

        // Base case 3: The probability roll fails, stopping the recursion in this direction
        if (random.nextDouble() > spreadProbability) {
            return;
        }

        // Apply the terrain to the current tile
        map[y][x] = new Tile(type);

        // Decrease the probability so the feature doesn't cover the entire map
        // A multiplier of 0.8 means the next tile has 20% less chance to turn into this feature.
        // Adjust this float to change how "blobby" or large your lakes/mountains get!
        double nextSpreadProbability = spreadProbability * 0.80;

        // Recursively attempt to spread in all 4 cardinal directions
        expandFeatureRecursively(map, x + 1, y, type, nextSpreadProbability); // East
        expandFeatureRecursively(map, x - 1, y, type, nextSpreadProbability); // West
        expandFeatureRecursively(map, x, y + 1, type, nextSpreadProbability); // South
        expandFeatureRecursively(map, x, y - 1, type, nextSpreadProbability); // North
    }

    /**
     * Configures the probabilities based on the specific Zelda region.
     */
    private RegionConfig getRegionConfig(HyruleRegion region) {
        RegionConfig config;

        switch (region) {
            case HEBRA:
                config = new RegionConfig(TerrainType.GRASS); // Base is mostly snow/grass
                // High chance of ice mountains, decent size
                config.addFeature(TerrainType.ICE_MOUNTAIN, 0.05, 0.95);
                // Zero desert
                config.addFeature(TerrainType.SAND, 0.0, 0.0);
                // Small frozen lakes
                config.addFeature(TerrainType.WATER, 0.01, 0.60);
                break;

            case GERUDO:
                config = new RegionConfig(TerrainType.SAND); // Base is desert sand
                // Very high sand dunes (mapped as hills/mountains)
                config.addFeature(TerrainType.MOUNTAIN, 0.03, 0.85);
                // Zero lakes
                config.addFeature(TerrainType.WATER, 0.0, 0.0);
                // Zero ice
                config.addFeature(TerrainType.ICE_MOUNTAIN, 0.0, 0.0);
                break;

            case LANAYRU:
                config = new RegionConfig(TerrainType.GRASS);
                // Very high probability for large lakes/rivers (spread is close to 1.0)
                config.addFeature(TerrainType.WATER, 0.02, 0.98);
                config.addFeature(TerrainType.MOUNTAIN, 0.01, 0.70);
                break;

            case ELDIN:
                config = new RegionConfig(TerrainType.MOUNTAIN);
                // Lots of lava for Death Mountain
                config.addFeature(TerrainType.LAVA, 0.04, 0.90);
                config.addFeature(TerrainType.WATER, 0.0, 0.0); // Boiling hot, no water
                break;

            default: // CENTRAL, AKKALA, FARON
                config = new RegionConfig(TerrainType.GRASS);
                config.addFeature(TerrainType.WATER, 0.01, 0.85);
                config.addFeature(TerrainType.FOREST, 0.04, 0.90);
                config.addFeature(TerrainType.MOUNTAIN, 0.01, 0.80);
                break;
        }
        return config;
    }
}
