package ru.chayka.minesweeper.eventsystem.events.view;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;

public final class NewLeaderDtoEvent
        implements MinesweeperEvent {
    private final String name;

    public NewLeaderDtoEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
