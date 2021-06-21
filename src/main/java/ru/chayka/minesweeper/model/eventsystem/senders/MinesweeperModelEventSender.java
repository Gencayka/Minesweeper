package ru.chayka.minesweeper.model.eventsystem.senders;

import ru.chayka.minesweeper.model.eventsystem.events.MinesweeperModelEvent;
import ru.chayka.minesweeper.model.eventsystem.listeners.MinesweeperModelEventListener;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class MinesweeperModelEventSender
        <L extends MinesweeperModelEventListener, E extends MinesweeperModelEvent> {
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
