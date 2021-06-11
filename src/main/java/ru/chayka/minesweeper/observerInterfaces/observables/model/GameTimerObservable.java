package ru.chayka.minesweeper.observerInterfaces.observables.model;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.GameTimerObserver;

public interface GameTimerObservable
        extends MinesweeperObservable<GameTimerObserver> {
    void notifyObservers();
}
