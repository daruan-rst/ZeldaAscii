package Entity.Equipable.Weapon;
import Entity.Equipable.genericEquipable;
import Entity.LivingThings.Attacker.Champion.Champion;
import lombok.Data;


@Data
public class Weapon extends genericEquipable {

    Champion champion;

    protected int damage;
    protected int range;
    protected double hitChance;
    protected Enums.weaponType weaponType;

    public void breakEquip(){
        if(this.durability == 0){
            champion.getWeaponInventary().remove(
                    champion.getEquipedWeapon());
        }



    }
}
