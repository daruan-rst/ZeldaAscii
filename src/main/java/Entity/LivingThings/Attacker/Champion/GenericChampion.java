package Entity.LivingThings.Attacker.Champion;

import Entity.LivingThings.Attacker.GenericEquipableAttacker;
import Entity.Equipable.Shield.Shield;
import Entity.Equipable.Weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class GenericChampion extends GenericEquipableAttacker {

    protected Enums.Races race;
    int weaponInventarySize;
    List<Weapon> weaponInventary = new ArrayList<>(weaponInventarySize);
    int shieldInventarySize;
    List<Shield> shieldInventary = new ArrayList<>(shieldInventarySize);
    Weapon equipedWeapon = weaponInventary.get(0);
    Shield equipedShield = shieldInventary.get(0);

}

