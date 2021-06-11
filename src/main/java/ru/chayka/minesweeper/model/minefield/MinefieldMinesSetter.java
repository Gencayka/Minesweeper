package ru.chayka.minesweeper.model.minefield;

import java.util.Random;

public class MinefieldMinesSetter {
    private final Minefield minefield;

    public MinefieldMinesSetter(Minefield minefield) {
        this.minefield = minefield;
    }

    public void generateMines(int firstUserMoveRow, int firstUserMoveColumn) {
        setMinesOnField(generateMinesLocation(firstUserMoveRow, firstUserMoveColumn));
        countAmountOfMinesInAdjacentCells();
        minefield.setMined(true);
    }

    public void setMinesOnField(Coordinates[] minesCoordinates, MinefieldCell[][] fieldCells) {
        for (Coordinates mineCoordinates : minesCoordinates) {
            fieldCells[mineCoordinates.row][mineCoordinates.column].setMined(true);
        }
    }

    public void setMinesOnField(Coordinates[] minesCoordinates) {
        setMinesOnField(minesCoordinates, minefield.getFieldCells());
    }

    private Coordinates[] generateMinesLocation(int firstUserMoveRow, int firstUserMoveColumn,
                                                int numOfRows, int numOfColumns, int numOfMines) {
        int[] minesLocationIndex = new Random()
                .ints(0, numOfColumns * numOfRows - 2)
                .distinct()
                .limit(numOfMines)
                .toArray();

        int firstUserMoveIndex = coordinatesToIndex(firstUserMoveRow, firstUserMoveColumn);
        for (int i = 0; i < minesLocationIndex.length; i++) {
            if (minesLocationIndex[i] >= firstUserMoveIndex) {
                minesLocationIndex[i]++;
            }
        }

        Coordinates[] minesCoordinates = new Coordinates[numOfMines];
        for (int i = 0; i < numOfMines; i++) {
            minesCoordinates[i] = indexToCoordinates(minesLocationIndex[i]);
        }
        return minesCoordinates;
    }

    private Coordinates[] generateMinesLocation(int firstUserMoveRow, int firstUserMoveColumn) {
        return generateMinesLocation(firstUserMoveRow,
                firstUserMoveColumn,
                minefield.getDifficultyMode().numOfRows,
                minefield.getDifficultyMode().numOfColumns,
                minefield.getDifficultyMode().numOfMines);
    }

    private void countAmountOfMinesInAdjacentCells(MinefieldCell[][] fieldCells, int numOfRows, int numOfColumns) {
        for (int currentRow = 0; currentRow < numOfRows; currentRow++) {
            for (int currentColumn = 0; currentColumn < numOfColumns; currentColumn++) {
                if (fieldCells[currentRow][currentColumn].isMined()) {
                    MinefieldCell currentCell = fieldCells[currentRow][currentColumn];
                    for (int currentAdjacentCell = 0; currentAdjacentCell < currentCell.adjacentCells.size(); currentAdjacentCell++) {
                        currentCell.adjacentCells.get(currentAdjacentCell).increaseNumOfAdjacentMines();
                    }
                }
            }
        }
    }

    private void countAmountOfMinesInAdjacentCells() {
        countAmountOfMinesInAdjacentCells(
                minefield.getFieldCells(),
                minefield.getDifficultyMode().numOfRows,
                minefield.getDifficultyMode().numOfColumns);
    }

    private int coordinatesToIndex(int rowCoordinate, int columnCoordinate, int numOfColumns) {
        return rowCoordinate * numOfColumns + columnCoordinate;
    }

    private int coordinatesToIndex(int rowCoordinate, int columnCoordinate) {
        return coordinatesToIndex(rowCoordinate, columnCoordinate, minefield.getDifficultyMode().numOfColumns);
    }

    private Coordinates indexToCoordinates(int index, int numOfColumns) {
        return new Coordinates(index / numOfColumns, index % numOfColumns);
    }

    private Coordinates indexToCoordinates(int index) {
        return indexToCoordinates(index, minefield.getDifficultyMode().numOfColumns);
    }
}
