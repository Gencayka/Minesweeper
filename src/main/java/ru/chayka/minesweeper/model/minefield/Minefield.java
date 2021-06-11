package ru.chayka.minesweeper.model.minefield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.exceptions.MinefieldConstructorException;

public class Minefield {
    private static final Logger log = LoggerFactory.getLogger(Minefield.class.getName());

    private final MinefieldCell[][] fieldCells;
    private final DifficultyMode difficultyMode;
    private boolean isMined = false;
    private int numOfClosedUnminedCells;

    public Minefield(DifficultyMode difficultyMode, MinefieldCell[][] fieldCells) {
        if (fieldCells.length != difficultyMode.numOfRows || fieldCells[0].length != difficultyMode.numOfColumns) {
            String exceptionMessage = "Failed to create new Minefield object - " +
                    "DifficultyMode do not correspond to sizes of MinefieldCell array";
            log.error(exceptionMessage);
            throw new MinefieldConstructorException(exceptionMessage);
        } else {
            this.difficultyMode = difficultyMode;
            this.fieldCells = fieldCells;
            this.numOfClosedUnminedCells = difficultyMode.numOfRows * difficultyMode.numOfColumns - difficultyMode.numOfMines;
        }
    }

    public DifficultyMode getDifficultyMode() {
        return difficultyMode;
    }

    public int getNumOfRows() {
        return difficultyMode.numOfRows;
    }

    public int getNumOfColumns() {
        return difficultyMode.numOfColumns;
    }

    public int getNumOfMines() {
        return difficultyMode.numOfMines;
    }

    public MinefieldCell[][] getFieldCells() {
        return fieldCells;
    }

    public MinefieldCell getFieldCell(int row, int column) {
        return fieldCells[row][column];
    }

    public int getNumOfClosedUnminedCells() {
        return numOfClosedUnminedCells;
    }

    public boolean isMined() {
        return isMined;
    }

    public void setMined(boolean mined) {
        isMined = mined;
    }

    public void decreaseNumOfClosedUnminedCells() {
        numOfClosedUnminedCells--;
    }
}
