package entity.livingThings.attacker;

import entity.equipable.shield.Shield;
import entity.equipable.weapon.Weapon;

import java.util.List;

abstract public class EquipableAttacker extends Attacker implements EquipableAttackerActions{
    Weapon equipedWeapon;
    Shield equipedShield;

    void equipWeapon(Weapon weapon){
        this.equipedWeapon = weapon;
    }

    void equipShield(Shield shield){
        this.equipedShield = shield;
    }


}
