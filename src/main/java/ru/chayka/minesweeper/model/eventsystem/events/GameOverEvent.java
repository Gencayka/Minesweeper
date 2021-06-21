package ru.chayka.minesweeper.model.eventsystem.events;

import ru.chayka.minesweeper.model.DifficultyMode;

public final class GameOverEvent
        implements MinesweeperModelEvent {
    private final boolean isWon;
    private final DifficultyMode difficultyMode;

    public GameOverEvent(boolean isWon, DifficultyMode difficultyMode) {
        this.isWon = isWon;
        this.difficultyMode = difficultyMode;
    }

    public boolean isWon() {
        return isWon;
    }

    public DifficultyMode getDifficultyMode() {
        return difficultyMode;
    }
}
