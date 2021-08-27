package Entity.Equipable.Shield;
import Entity.LivingThings.Attacker.Attacker;
import Entity.LivingThings.Attacker.Champion.Champion;
import Entity.LivingThings.Attacker.EquipableAttacker;
import Enums.weaponType;
import Entity.Equipable.genericEquipable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shield extends genericEquipable {

    Champion champion;

    String name;
    int defenseFactor;
    weaponType tipoDeItem = weaponType.NONE;

    public void breakEquip(){
        if(this.durability == 0){
            champion.getShieldInventary().remove(
                    champion.getEquipedShield());
        }
    }

}

