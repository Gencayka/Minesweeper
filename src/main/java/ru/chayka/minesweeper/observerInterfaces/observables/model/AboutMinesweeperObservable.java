package ru.chayka.minesweeper.observerInterfaces.observables.model;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.AboutMinesweeperObserver;

public interface AboutMinesweeperObservable
        extends MinesweeperObservable<AboutMinesweeperObserver> {
    void notifyObservers();
}
