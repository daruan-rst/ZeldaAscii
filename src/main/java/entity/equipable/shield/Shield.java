package entity.equipable.shield;
import entity.livingThings.attacker.Champion.Champion;
import enums.WeaponType;
import entity.equipable.genericEquipable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shield extends genericEquipable {
    Champion champion;
    String name;
    int defenseFactor;
    WeaponType tipoDeItem = WeaponType.NONE;

    public void breakEquip(){
        if(this.durability == 0){
            champion.getShieldInventary().remove(
                    champion.getEquipedShield());
        }
    }

}

