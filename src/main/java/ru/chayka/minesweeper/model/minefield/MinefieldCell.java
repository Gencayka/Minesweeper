package ru.chayka.minesweeper.model.minefield;

import java.util.ArrayList;

public class MinefieldCell {
    private final int row;
    private final int column;

    private int numOfAdjacentMines;
    private boolean isOpened;
    private boolean isMined;
    private boolean isFlagged;

    final ArrayList<MinefieldCell> adjacentCells;

    public MinefieldCell(int row, int column) {
        this.row = row;
        this.column = column;

        numOfAdjacentMines = 0;
        isOpened = false;
        isMined = false;
        isFlagged = false;

        adjacentCells = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
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

    public int getNumOfAdjacentMines() {
        return numOfAdjacentMines;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public void setMined(boolean mined) {
        isMined = mined;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public void increaseNumOfAdjacentMines() {
        this.numOfAdjacentMines++;
    }
}
