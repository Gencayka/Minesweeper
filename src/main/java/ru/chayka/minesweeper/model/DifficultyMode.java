package ru.chayka.minesweeper.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public enum DifficultyMode {
    BEGINNER(9, 9, 10),
    INTERMEDIATE(16, 16, 40),
    EXPERT(16, 30, 99);

    private final Logger log = LoggerFactory.getLogger(DifficultyMode.class.getName());
    private static final Logger staticLog = LoggerFactory.getLogger(DifficultyMode.class.getName());

    public final int numOfColumns;
    public final int numOfRows;
    public final int numOfMines;
    public final static DifficultyMode INITIAL_DIFFICULTY_MODE = BEGINNER;

    DifficultyMode(int numOfRows, int numOfColumns, int numOfMines) {
        if (numOfRows <= 0 || numOfColumns <= 0 || numOfMines >= numOfRows * numOfColumns) {
            log.error("Attempt to create Difficulty Mode with invalid parameters");
            throw new ExceptionInInitializerError();
        }
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.numOfMines = numOfMines;
    }

    public static Optional<DifficultyMode> stringToDifficultyMode(String str) {
        for (var difficultyMode : DifficultyMode.values()) {
            if (str.toUpperCase().equals(difficultyMode.name())) {
                return Optional.of(difficultyMode);
            }
        }
        staticLog.error("Attempt to get invalid difficulty mode");
        return Optional.empty();
    }

    @Override
    public String toString() {
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}
