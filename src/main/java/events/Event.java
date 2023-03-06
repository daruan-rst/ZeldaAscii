package events;

import enums.EventType;

import java.util.List;
 public abstract class Event {

    protected EventType type;

    protected List<String> dialog;

    abstract protected void onStart();
}
