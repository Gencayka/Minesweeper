package ru.chayka.minesweeper.eventsystem.listeners.view;

import ru.chayka.minesweeper.eventsystem.events.model.LeaderboardDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface LeaderboardDtoEventListener
        extends MinesweeperEventListener {
    void acceptEvent(LeaderboardDtoEvent event);
}
