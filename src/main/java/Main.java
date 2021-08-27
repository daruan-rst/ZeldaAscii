import Entity.Equipable.Shield.Shield;
import Entity.Equipable.Weapon.Sword.Sword;
import Entity.Equipable.Weapon.Weapon;
import Entity.Equipable.genericEquipable;
import Entity.LivingThings.Attacker.Champion.Champion;
import Entity.LivingThings.Attacker.Attacker;
import Entity.LivingThings.Attacker.EquipableAttacker;
import Enums.Races;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Shield hylianShield = new Shield();
        hylianShield.setName("Hylian Shield");
        Weapon masterSword = new Sword();
        Shield mirrorShield = new Shield();
        mirrorShield.setName("Mirror Shield");
        Weapon lokomoSword = new Sword();
        Champion link = new Champion("Link", Races.HYLIAN, 3,3);

        link.setX(0);
        link.setY(12);


        List<Weapon> foundWeapons = new ArrayList<>();
        List<Shield> foundShields = new ArrayList<>();

        foundWeapons.add(masterSword);
        foundWeapons.add(lokomoSword);

        foundShields.add(mirrorShield);
        foundShields.add(hylianShield);

        link.createInventary(foundWeapons, foundShields);

        System.out.println(link.getEquipedShield().getName());
    }
}

