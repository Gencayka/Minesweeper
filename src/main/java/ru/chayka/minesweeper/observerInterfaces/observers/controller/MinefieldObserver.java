package ru.chayka.minesweeper.observerInterfaces.observers.controller;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;
import ru.chayka.minesweeper.view.MouseButton;
import ru.chayka.minesweeper.view.minesweeperFrame.minefield.minefieldButton.MinefieldButtonState;

public interface MinefieldObserver extends MinesweeperObserver {
    void transferToModel(int rowCoordinate,
                         int columnCoordinate,
                         MouseButton mouseButton,
                         MinefieldButtonState minefieldButtonState);
}
