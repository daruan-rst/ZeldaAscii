package entity.livingThings.attacker;

public interface BasicAttackerActions {

    void attack(Attacker opponent);
    void dodge(Attacker opponent);
    void defend(Attacker opponent);
}
