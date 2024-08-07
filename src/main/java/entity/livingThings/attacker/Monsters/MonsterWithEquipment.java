package entity.livingThings.attacker.Monsters;

import entity.equipable.shield.Shield;
import entity.equipable.weapon.Weapon;
import entity.equipable.weapon.combatWeapon.sword.Sword;
import lombok.Getter;

@Getter
public class MonsterWithEquipment extends Monster {

    private Weapon weapon;

    private Shield shield;

    private void grabWeapon(Weapon weapon){
        if (this.weapon == null){
            this.weapon = weapon;
        }
    }

    private void grabShield(Shield shield){
        if (this.shield == null && this.weapon instanceof Sword){
            this.shield = shield;
        }
    }
}
