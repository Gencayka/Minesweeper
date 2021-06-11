package ru.chayka.minesweeper.model.minefield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.MVCLogger;
import ru.chayka.minesweeper.observerInterfaces.observables.model.MinefieldCreatorObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.MinefieldCreatorObserver;

import java.util.ArrayList;

public class MinefieldCreator
        implements MinefieldCreatorObservable {
    private static final Logger log = LoggerFactory.getLogger(MinefieldCreator.class.getName());

    private final ArrayList<MinefieldCreatorObserver> observers = new ArrayList<>();

    private DifficultyMode lastDifficultyMode;

    public DifficultyMode getLastDifficultyMode() {
        return lastDifficultyMode;
    }

    public Minefield createNewMinefield(DifficultyMode difficultyMode) {
        lastDifficultyMode = difficultyMode;
        Minefield minefield = new Minefield(difficultyMode, generateClearField(difficultyMode.numOfRows, difficultyMode.numOfColumns));
        setAdjacentCells(minefield, difficultyMode.numOfRows, difficultyMode.numOfColumns);

        notifyObservers(difficultyMode.numOfRows, difficultyMode.numOfColumns);
        return minefield;
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

    @Override
    public void registerObserver(MinefieldCreatorObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(MinefieldCreatorObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void notifyObservers(int numOfRows, int numOfColumns) {
        for (MinefieldCreatorObserver observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.createNewMinefield(numOfRows, numOfColumns);
        }
    }
}
