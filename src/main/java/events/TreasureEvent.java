package events;

import entity.loot.Loot;
import enums.EventType;

public class TreasureEvent extends Event {

    protected void onStart(){
        type = EventType.TREASURE;
    }

    private Loot loot;


}
