package Entity.LivingThings.Attacker;

import Entity.Equipable.Shield.Shield;
import Entity.Equipable.Weapon.Weapon;

abstract public class EquipableAttacker extends Attacker implements EquipableAttackerActions{
    Weapon equipedWeapon;
    Shield equipedShield;
}
