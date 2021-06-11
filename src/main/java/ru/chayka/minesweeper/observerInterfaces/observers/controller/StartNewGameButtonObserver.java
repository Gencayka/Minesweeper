package ru.chayka.minesweeper.observerInterfaces.observers.controller;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface StartNewGameButtonObserver extends MinesweeperObserver {
    void transferToModel();

    void transferToModel(String strDifficultyMode);
}
