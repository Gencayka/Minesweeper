package ru.chayka.minesweeper.model.eventsystem.events;

import ru.chayka.minesweeper.model.minefield.Minefield;

public final class MinefieldDtoEvent
        implements MinesweeperModelEvent {
    private final Minefield minefield;

    public MinefieldDtoEvent(Minefield minefield) {
        this.minefield = minefield;
    }

    public Minefield getMinefield() {
        return minefield;
    }
}
