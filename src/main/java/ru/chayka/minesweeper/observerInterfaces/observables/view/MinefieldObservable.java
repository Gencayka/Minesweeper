package ru.chayka.minesweeper.observerInterfaces.observables.view;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.MinefieldObserver;
import ru.chayka.minesweeper.view.MouseButton;
import ru.chayka.minesweeper.view.minesweeperFrame.minefield.minefieldButton.MinefieldButtonState;

public interface MinefieldObservable
        extends MinesweeperObservable<MinefieldObserver> {
    void notifyObservers(int rowCoordinate,
                         int columnCoordinate,
                         MouseButton mouseButton,
                         MinefieldButtonState minefieldButtonState);
}
