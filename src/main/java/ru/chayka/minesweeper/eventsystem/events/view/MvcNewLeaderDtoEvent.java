package ru.chayka.minesweeper.eventsystem.events.view;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;

public final class MvcNewLeaderDtoEvent
        implements MvcEvent {
    private final String name;

    public MvcNewLeaderDtoEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
