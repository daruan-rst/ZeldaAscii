package entity.livingThings.attacker;

import entity.equipable.shield.Shield;
import entity.equipable.weapon.Weapon;

abstract public class EquipableAttacker extends Attacker implements EquipableAttackerActions{
    Weapon equipedWeapon;
    Shield equipedShield;
}
