package entity.livingThings.attacker.Champion;

import entity.equipable.shield.Shield;
import entity.equipable.weapon.bow.Bow;
import entity.equipable.weapon.combatWeapon.CombatWeapon;
import entity.livingThings.attacker.EquipableAttacker;
import enums.Races;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class Champion extends EquipableAttacker {

    final String name;

    final private Races race;

    int weaponInventarySize;

    @Setter
    List<CombatWeapon> weaponInventary = new ArrayList<>(this.weaponInventarySize);

    CombatWeapon equipedCombatWeapon;

    @NonNull
    int bowInventarySize;

    @Setter
    List<Bow> bowInventary = new ArrayList<>(this.bowInventarySize);

    Bow equipedBow;

    @NonNull
    int shieldInventarySize;

    List<Shield> shieldInventary = new ArrayList<>(this.shieldInventarySize);

    Shield equipedShield;

    public void createInventary(List<CombatWeapon> weaponList, List<Shield> shieldList){
        this.shieldInventary = shieldList;
        this.weaponInventary = weaponList;
        equipWeapon(0);
        equipShield(0);
    }

    public void equipWeapon(int i){
        if (i < weaponInventarySize){
            this.equipedCombatWeapon = weaponInventary.get(i);
        }
    }

    public void dropWeapon() {
        this.weaponInventary.remove(equipedCombatWeapon);
        this.equipedCombatWeapon = null;
    }

    public void equipShield(int i){
        if (i<=shieldInventarySize){
            this.equipedShield = shieldInventary.get(i);
        }
    }

    @Override
    public void dropShield() {
        shieldInventary.remove(this.equipedShield);
        this.equipedShield = null;
    }

    private void useKorokSeedOnWeaponInventary(List<CombatWeapon> weaponList){
        this.weaponInventarySize++;
        this.weaponInventary = new ArrayList<>(this.weaponInventarySize);
        this.weaponInventary = List.copyOf(weaponList);
    }

    private void useKorokSeedOnShieldInventary(List<Shield> shieldList){
        this.shieldInventarySize++;
        this.shieldInventary = new ArrayList<>(this.shieldInventarySize);
        this.shieldInventary = List.copyOf(shieldList);
    }

}

