package events;

import enums.EventType;

public class BattleEvent extends Event {

    protected void onStart(){
        type = EventType.BATTLE;
    }
}
