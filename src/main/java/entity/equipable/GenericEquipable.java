package entity.equipable;

import entity.loot.Loot;
import enums.Material;

public abstract class GenericEquipable extends Loot implements EquipableBehavior{
    protected int durability;
    Material craftedMaterial;


}
