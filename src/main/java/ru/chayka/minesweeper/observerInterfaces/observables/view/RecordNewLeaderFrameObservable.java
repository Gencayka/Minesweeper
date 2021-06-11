package ru.chayka.minesweeper.observerInterfaces.observables.view;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.LeaderboardActionsObserver;

public interface RecordNewLeaderFrameObservable
        extends MinesweeperObservable<LeaderboardActionsObserver> {
    void notifyObservers();
}
