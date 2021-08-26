package Entity.LivingThings.Attacker;

public interface BasicAttackerActions {

    void attack(GenericAttacker attacker, GenericAttacker receiver);
    void dodge(GenericAttacker attacker, GenericAttacker receiver);
    void defend(GenericAttacker attacker, GenericAttacker receiver);
}
