package events;

import enums.EventType;

public class TreasureEvent extends Event {

    protected void onStart(){
        type = EventType.TREASURE;
    }
}
