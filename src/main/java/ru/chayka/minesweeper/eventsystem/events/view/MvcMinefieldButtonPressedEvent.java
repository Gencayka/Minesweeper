package ru.chayka.minesweeper.eventsystem.events.view;

import ru.chayka.minesweeper.eventsystem.events.MvcEvent;
import ru.chayka.minesweeper.view.MouseButton;
import ru.chayka.minesweeper.view.mainframe.minefield.minefieldbutton.MinefieldButtonState;

public final class MvcMinefieldButtonPressedEvent
        implements MvcEvent {
    private final int row;
    private final int column;
    private final MouseButton mouseButton;
    private final MinefieldButtonState state;

    public MvcMinefieldButtonPressedEvent(int row, int column, MouseButton mouseButton, MinefieldButtonState state) {
        this.row = row;
        this.column = column;
        this.mouseButton = mouseButton;
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    public MinefieldButtonState getState() {
        return state;
    }
}
