package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;

public final class MvcGameTimeDtoEvent
        implements MvcEvent {
    private final int gameTime;

    public MvcGameTimeDtoEvent(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getGameTime() {
        return gameTime;
    }
}
