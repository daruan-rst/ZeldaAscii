package world;

import enums.TerrainType;
import java.util.HashMap;
import java.util.Map;

public class RegionConfig {
    TerrainType baseTerrain;

    // Chances to spawn a single seed of a terrain type (0.0 to 1.0)
    Map<TerrainType, Double> spawnChances = new HashMap<>();

    // Chances for that seed to recursively spread to a neighbor (0.0 to 1.0)
    Map<TerrainType, Double> spreadChances = new HashMap<>();

    public RegionConfig(TerrainType baseTerrain) {
        this.baseTerrain = baseTerrain;
    }

    public void addFeature(TerrainType type, double spawnChance, double spreadChance) {
        spawnChances.put(type, spawnChance);
        spreadChances.put(type, spreadChance);
    }

    public double getSpawnChance(TerrainType type) {
        return spawnChances.getOrDefault(type, 0.0);
    }

    public double getSpreadChance(TerrainType type) {
        return spreadChances.getOrDefault(type, 0.0);
    }
}
