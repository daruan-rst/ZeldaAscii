package events;

import entity.livingThings.attacker.Monsters.Monster;
import enums.EventType;

public class BattleEvent extends Event {

    protected void onStart(){
        type = EventType.BATTLE;
    }

    private Monster monster;
}
