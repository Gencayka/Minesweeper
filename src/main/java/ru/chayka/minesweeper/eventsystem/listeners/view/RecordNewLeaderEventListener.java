package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.RecordNewLeaderEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface RecordNewLeaderEventListener
        extends MinesweeperEventListener {
    void acceptEvent(RecordNewLeaderEvent event);
}
