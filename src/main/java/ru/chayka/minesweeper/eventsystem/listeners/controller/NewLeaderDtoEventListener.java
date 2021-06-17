package ru.chayka.minesweeper.eventsystem.listeners.controller;

import ru.chayka.minesweeper.eventsystem.events.view.NewLeaderDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public interface NewLeaderDtoEventListener
        extends MinesweeperEventListener {
    void acceptEvent(NewLeaderDtoEvent event);
}
