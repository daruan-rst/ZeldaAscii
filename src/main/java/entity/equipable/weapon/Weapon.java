package entity.equipable.weapon;
import entity.equipable.GenericEquipable;
import entity.livingThings.attacker.Champion.Champion;
import enums.WeaponType;
import lombok.Data;


@Data
public abstract class Weapon extends GenericEquipable {

    Champion champion;

    protected int damage;
    protected int range;
    protected double hitChance;
    protected WeaponType weaponType;

    public void breakEquip(){
        if(this.durability == 0){
            champion.getWeaponInventary().remove(
                    champion.getEquipedWeapon());
        }



    }
}
