package ru.chayka.minesweeper.observerInterfaces.observables.model;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.MinefieldCreatorObserver;

public interface MinefieldCreatorObservable
        extends MinesweeperObservable<MinefieldCreatorObserver> {
    void notifyObservers(int numOfColumns, int numOfRows);
}
