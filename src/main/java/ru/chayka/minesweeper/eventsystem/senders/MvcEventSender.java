package ru.chayka.minesweeper.eventsystem.senders;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class MvcEventSender
        <L extends MvcEventListener, E extends MvcEvent> {
    private final Set<L> listeners = new LinkedHashSet<>();

    public final boolean addListener(L listener) {
        return listeners.add(listener);
    }

    public final boolean removeListener(L listener) {
        return listeners.remove(listener);
    }

    public final void notifyAllListeners(E event) {
        for (L listener : listeners) {
            notifyOneListener(listener, event);
        }
    }

    protected abstract void notifyOneListener(L listener, E event);
}
