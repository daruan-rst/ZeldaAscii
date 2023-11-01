package world;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tile {
    private boolean walkable;
    private boolean visible;
    private boolean swimable;
    private boolean climbable;

    public Tile(boolean walkable) {
        this.walkable = walkable;
        this.visible = false;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

