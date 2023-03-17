package events;

import entity.Loot;
import entity.equipable.GenericEquipable;
import enums.EventType;

public class TreasureEvent extends Event {

    protected void onStart(){
        type = EventType.TREASURE;
    }

    private Loot loot;


}
