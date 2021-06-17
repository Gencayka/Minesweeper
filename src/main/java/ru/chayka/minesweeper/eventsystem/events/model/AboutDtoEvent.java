package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;

public final class AboutDtoEvent
        implements MinesweeperEvent {
    private final String text;

    public AboutDtoEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
