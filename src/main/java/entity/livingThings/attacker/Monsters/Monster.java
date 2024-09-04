package entity.livingThings.attacker.Monsters;

import entity.equipable.weapon.Weapon;
import entity.livingThings.attacker.Attacker;
import entity.loot.MonsterPart;
import enums.MonsterClass;
import enums.MonsterRace;


public class Monster extends Attacker {
    private MonsterClass monsterClass;

    private MonsterRace race;

    private MonsterPart droppableLoot;

    @Override
    public void attack(Attacker opponent) {
        //TODO: CODE ME LATER
    }

    @Override
    public void dodge(Attacker opponent) {
        //TODO: CODE ME LATER
    }

    @Override
    public void defend(Attacker opponent) {
        //TODO: CODE ME LATER
    }
}
