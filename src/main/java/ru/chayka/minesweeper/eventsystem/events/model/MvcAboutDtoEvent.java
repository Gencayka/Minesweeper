package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;

public final class MvcAboutDtoEvent
        implements MvcEvent {
    private final String text;

    public MvcAboutDtoEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
