package entity.armor;

import entity.livingThings.CharacterEnhancmentEffect;
import entity.loot.Loot;
import enums.ArmorType;

public abstract class GenericArmor extends Loot {
    int armorDefense;
    ArmorType armorType;

    CharacterEnhancmentEffect effect;
}
