package ru.chayka.minesweeper.eventsystem.events.model;

import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;
import ru.chayka.minesweeper.model.minefield.MinefieldCell;

public final class MinefieldCellDtoEvent
        implements MinesweeperEvent {
    private final int row;
    private final int column;

    private final int numOfAdjacentMines;
    private final boolean isOpened;
    private final boolean isMined;
    private final boolean isFlagged;

    private final boolean isOpenedWithWrongMove;

    public MinefieldCellDtoEvent(MinefieldCell minefieldCell, boolean isOpenedWithWrongMove) {
        this.row = minefieldCell.getRow();
        this.column = minefieldCell.getColumn();
        this.numOfAdjacentMines = minefieldCell.getNumOfAdjacentMines();
        this.isOpened = minefieldCell.isOpened();
        this.isMined = minefieldCell.isMined();
        this.isFlagged = minefieldCell.isFlagged();

        this.isOpenedWithWrongMove = isOpenedWithWrongMove;
    }

    public MinefieldCellDtoEvent(MinefieldCell minefieldCell) {
        this(minefieldCell, false);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getNumOfAdjacentMines() {
        return numOfAdjacentMines;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean isMined() {
        return isMined;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public boolean isOpenedWithWrongMove() {
        return isOpenedWithWrongMove;
    }
}
