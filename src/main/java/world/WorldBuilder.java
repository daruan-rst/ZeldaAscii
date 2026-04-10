package world;

import config.GlobalConfig;
import enums.HyruleRegion;
import enums.TerrainType;

import java.util.Random;

public class WorldBuilder {

    private final Random random = new Random();

    public Tile[][] buildRegion(HyruleRegion region) {
        Tile[][] map = new Tile[GlobalConfig.MAP_HEIGHT][GlobalConfig.MAP_WIDTH];
        RegionConfig config = getRegionConfig(region);

        // 1. Initialize map with the region's base terrain
        for (int y = 0; y < GlobalConfig.MAP_HEIGHT; y++) {
            for (int x = 0; x < GlobalConfig.MAP_WIDTH; x++) {
                map[y][x] = new Tile(config.baseTerrain);
            }
        }

        // 2. Iterate map to seed and recursively expand geographic features
        for (int y = 0; y < GlobalConfig.MAP_HEIGHT; y++) {
            for (int x = 0; x < GlobalConfig.MAP_WIDTH; x++) {

                for (TerrainType type : TerrainType.values()) {
                    if (type == config.baseTerrain) continue;

                    double spawnChance = config.getSpawnChance(type);

                    if (spawnChance > 0 && random.nextDouble() <= spawnChance) {
                        double spreadChance = config.getSpreadChance(type);
                        expandFeatureRecursively(map, x, y, type, spreadChance);
                    }
                }
            }
        }

        return map;
    }

    private void expandFeatureRecursively(Tile[][] map, int x, int y, TerrainType type, double spreadProbability) {
        // Safe Bounds check using Global Config!
        if (x < 0 || x >= GlobalConfig.MAP_WIDTH || y < 0 || y >= GlobalConfig.MAP_HEIGHT) {
            return;
        }

        if (map[y][x].getTerrainType() == type) return;
        if (random.nextDouble() > spreadProbability) return;

        map[y][x] = new Tile(type);
        double nextSpreadProbability = spreadProbability * 0.80;

        expandFeatureRecursively(map, x + 1, y, type, nextSpreadProbability);
        expandFeatureRecursively(map, x - 1, y, type, nextSpreadProbability);
        expandFeatureRecursively(map, x, y + 1, type, nextSpreadProbability);
        expandFeatureRecursively(map, x, y - 1, type, nextSpreadProbability);
    }

    private RegionConfig getRegionConfig(HyruleRegion region) {
        RegionConfig config;
        switch (region) {
            case HEBRA:
                config = new RegionConfig(TerrainType.GRASS);
                config.addFeature(TerrainType.ICE_MOUNTAIN, 0.05, 0.95);
                config.addFeature(TerrainType.SAND, 0.0, 0.0);
                config.addFeature(TerrainType.WATER, 0.01, 0.60);
                break;
            case GERUDO:
                config = new RegionConfig(TerrainType.SAND);
                config.addFeature(TerrainType.MOUNTAIN, 0.03, 0.85);
                config.addFeature(TerrainType.WATER, 0.0, 0.0);
                config.addFeature(TerrainType.ICE_MOUNTAIN, 0.0, 0.0);
                break;
            case LANAYRU:
                config = new RegionConfig(TerrainType.GRASS);
                config.addFeature(TerrainType.WATER, 0.02, 0.98);
                config.addFeature(TerrainType.MOUNTAIN, 0.01, 0.70);
                break;
            case ELDIN:
                config = new RegionConfig(TerrainType.MOUNTAIN);
                config.addFeature(TerrainType.LAVA, 0.04, 0.90);
                config.addFeature(TerrainType.WATER, 0.0, 0.0);
                break;
            default:
                config = new RegionConfig(TerrainType.GRASS);
                config.addFeature(TerrainType.WATER, 0.01, 0.85);
                config.addFeature(TerrainType.FOREST, 0.04, 0.90);
                config.addFeature(TerrainType.MOUNTAIN, 0.01, 0.80);
                break;
        }
        return config;
    }
}