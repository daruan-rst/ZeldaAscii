package world;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tile {
    private boolean walkable;
    private boolean visible;
    private boolean swimmable;
    private boolean climbable;

    public Tile(boolean walkable) {
        this.walkable = walkable;
        this.visible = false;
    }

}

