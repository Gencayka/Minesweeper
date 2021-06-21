package ru.chayka.minesweeper.model.minefield;

import ru.chayka.minesweeper.eventsystem.events.model.MvcMinefieldDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcMinefieldDtoEventSender;
import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.eventsystem.events.MinefieldDtoEvent;
import ru.chayka.minesweeper.model.eventsystem.senders.MinefieldDtoEventSender;

public class MinefieldCreator {
    private DifficultyMode lastDifficultyMode;

    private final MinefieldDtoEventSender minefieldDtoEventSender;

    private final MvcMinefieldDtoEventSender mvcMinefieldDtoEventSender;

    public MinefieldCreator() {
        minefieldDtoEventSender = new MinefieldDtoEventSender();

        mvcMinefieldDtoEventSender = new MvcMinefieldDtoEventSender();
    }

    public DifficultyMode getLastDifficultyMode() {
        return lastDifficultyMode;
    }

    public MinefieldDtoEventSender getMinefieldDtoEventSender() {
        return minefieldDtoEventSender;
    }

    public MvcMinefieldDtoEventSender getMvcMinefieldDtoEventSender() {
        return mvcMinefieldDtoEventSender;
    }

    public void createNewMinefield(DifficultyMode difficultyMode) {
        lastDifficultyMode = difficultyMode;
        Minefield minefield = new Minefield(difficultyMode, generateClearField(difficultyMode.numOfRows, difficultyMode.numOfColumns));
        setAdjacentCells(minefield, difficultyMode.numOfRows, difficultyMode.numOfColumns);

        minefieldDtoEventSender.notifyAllListeners(
                new MinefieldDtoEvent(minefield));
        mvcMinefieldDtoEventSender.notifyAllListeners(
                new MvcMinefieldDtoEvent(difficultyMode.numOfRows, difficultyMode.numOfColumns));
    }

    private MinefieldCell[][] generateClearField(int numOfRows, int numOfColumns) {
        MinefieldCell[][] cells = new MinefieldCell[numOfRows][numOfColumns];
        for (int currentRow = 0; currentRow < numOfRows; currentRow++) {
            for (int currentColumn = 0; currentColumn < numOfColumns; currentColumn++) {
                cells[currentRow][currentColumn] = new MinefieldCell(currentRow, currentColumn);
            }
        }
        return cells;
    }

    private void setAdjacentCells(Minefield minefield, int numOfRows, int numOfColumns) {
        MinefieldCell[][] cells = minefield.getFieldCells();
        for (int currentRow = 0; currentRow < numOfRows; currentRow++) {
            for (int currentColumn = 0; currentColumn < numOfColumns; currentColumn++) {
                MinefieldCell currentCell = cells[currentRow][currentColumn];
                if (currentRow > 0) {
                    currentCell.adjacentCells.add(cells[currentRow - 1][currentColumn]);
                }
                if (currentRow > 0 && currentColumn < numOfColumns - 1) {
                    currentCell.adjacentCells.add(cells[currentRow - 1][currentColumn + 1]);
                }
                if (currentColumn < numOfColumns - 1) {
                    currentCell.adjacentCells.add(cells[currentRow][currentColumn + 1]);
                }
                if (currentRow < numOfRows - 1 && currentColumn < numOfColumns - 1) {
                    currentCell.adjacentCells.add(cells[currentRow + 1][currentColumn + 1]);
                }
                if (currentRow < numOfRows - 1) {
                    currentCell.adjacentCells.add(cells[currentRow + 1][currentColumn]);
                }
                if (currentRow < numOfRows - 1 && currentColumn > 0) {
                    currentCell.adjacentCells.add(cells[currentRow + 1][currentColumn - 1]);
                }
                if (currentColumn > 0) {
                    currentCell.adjacentCells.add(cells[currentRow][currentColumn - 1]);
                }
                if (currentRow > 0 && currentColumn > 0) {
                    currentCell.adjacentCells.add(cells[currentRow - 1][currentColumn - 1]);
                }
            }
        }
    }
}
