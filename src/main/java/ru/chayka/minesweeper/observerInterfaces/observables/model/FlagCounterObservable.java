package ru.chayka.minesweeper.observerInterfaces.observables.model;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.FlagCounterObserver;

public interface FlagCounterObservable
        extends MinesweeperObservable<FlagCounterObserver> {
    void notifyObservers();
}
