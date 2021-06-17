package ru.chayka.minesweeper.eventsystem.senders;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

import java.util.HashSet;
import java.util.Set;

public abstract class MinesweeperEventSender<L extends MinesweeperEventListener, E extends MinesweeperEvent> {
    private final Set<L> listeners = new HashSet<>();

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
