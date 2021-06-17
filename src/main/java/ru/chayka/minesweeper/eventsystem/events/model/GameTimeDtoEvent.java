package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;

public final class GameTimeDtoEvent
        implements MinesweeperEvent {
    private final int gameTime;

    public GameTimeDtoEvent(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getGameTime() {
        return gameTime;
    }
}
