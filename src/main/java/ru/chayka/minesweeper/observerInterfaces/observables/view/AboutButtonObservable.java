package ru.chayka.minesweeper.observerInterfaces.observables.view;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.AboutButtonObserver;

public interface AboutButtonObservable
        extends MinesweeperObservable<AboutButtonObserver> {
    void notifyObservers();
}
