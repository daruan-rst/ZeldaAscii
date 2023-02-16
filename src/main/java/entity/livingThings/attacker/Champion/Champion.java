package entity.livingThings.attacker.Champion;

import entity.livingThings.attacker.EquipableAttacker;
import entity.equipable.shield.Shield;
import entity.equipable.weapon.Weapon;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class Champion extends EquipableAttacker {

    final String name;
    final private enums.Races race;

    @NonNull
    int weaponInventarySize;
    @Setter
    List<Weapon> weaponInventary = new ArrayList<>(this.weaponInventarySize);
    Weapon equipedWeapon;

    @NonNull
    int shieldInventarySize;
    List<Shield> shieldInventary = new ArrayList<>(this.shieldInventarySize);
    Shield equipedShield;

    public void createInventary(List<Weapon> weaponList, List<Shield> shieldList){
        this.shieldInventary = shieldList;
        this.weaponInventary = weaponList;
        equipWeapon();
        equipShield();
    }

    public void equipWeapon(){
        this.equipedWeapon = weaponInventary.get(0);
    }

    @Override
    public void dropWeapon() {
        this.equipedWeapon = null;
    }

    public void equipShield(){
        this.equipedShield = shieldInventary.get(0);
    }

    @Override
    public void dropShield() {
        this.equipedShield = null;
    }

}

