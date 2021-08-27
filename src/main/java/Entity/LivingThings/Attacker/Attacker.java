package Entity.LivingThings.Attacker;

import Entity.LivingThings.LivingEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Attacker extends LivingEntity {
    protected int life;
    int baseDefense;
    int baseDamage;
}
