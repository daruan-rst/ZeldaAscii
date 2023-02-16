package entity.equipable;

import enums.Material;

public abstract class genericEquipable implements EquipableBehavior{
    protected int durability;
    Material craftedMaterial;
}
