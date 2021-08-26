package Entity.Equipable.Weapon;
import Entity.Equipable.genericEquipable;
import lombok.Data;


@Data
public class Weapon extends genericEquipable {
    protected int damage;
    protected int range;
    protected double hitChance;
    protected Enums.weaponType weaponType;



}
