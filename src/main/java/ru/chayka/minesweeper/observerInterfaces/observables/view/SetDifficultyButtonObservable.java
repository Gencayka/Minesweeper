package ru.chayka.minesweeper.observerInterfaces.observables.view;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.StartNewGameButtonObserver;

public interface SetDifficultyButtonObservable
        extends MinesweeperObservable<StartNewGameButtonObserver> {
    void notifyObservers(String strDifficultyMode);
}
