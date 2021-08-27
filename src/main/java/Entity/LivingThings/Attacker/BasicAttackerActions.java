package Entity.LivingThings.Attacker;

public interface BasicAttackerActions {

    void attack(Attacker opponent);
    void dodge(Attacker opponent);
    void defend(Attacker opponent);
}
