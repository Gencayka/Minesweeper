package ru.chayka.minesweeper.observerInterfaces.observables.view;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.LeaderboardActionsObserver;

public interface HighScoresButtonObservable
        extends MinesweeperObservable<LeaderboardActionsObserver> {
    void notifyObservers();
}
