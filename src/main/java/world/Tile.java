package world;


import enums.TerrainType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tile {
    private boolean walkable;
    private boolean visible;
    private boolean swimmable;
    private boolean climbable;
    private TerrainType terrainType;

    public Tile(boolean walkable) {
        this.walkable = walkable;
        this.visible = false;
        this.terrainType = TerrainType.GRASS; // Default Constructor
    }

    public Tile(TerrainType terrainType) {
        this.terrainType = terrainType;
        this.visible = false;
        this.walkable = (terrainType != TerrainType.WATER && terrainType != TerrainType.LAVA);
        this.swimmable = (terrainType == TerrainType.WATER);
        this.climbable = (terrainType == TerrainType.MOUNTAIN || terrainType == TerrainType.ICE_MOUNTAIN);
    }

}

