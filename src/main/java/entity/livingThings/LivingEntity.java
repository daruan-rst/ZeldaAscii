package entity.livingThings;

import lombok.Data;

@Data
public abstract class LivingEntity implements LivingEntityBasicActions {
    protected int x;
    protected int y;

    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}
