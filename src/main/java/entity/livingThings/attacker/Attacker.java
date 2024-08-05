package entity.livingThings.attacker;

import entity.livingThings.LivingEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Attacker extends LivingEntity implements BasicAttackerActions {
    protected int life;
    int baseDefense;
    int baseDamage;
}
