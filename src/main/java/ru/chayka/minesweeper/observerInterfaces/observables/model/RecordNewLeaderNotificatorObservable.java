package ru.chayka.minesweeper.observerInterfaces.observables.model;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.RecordNewLeaderNotificatorObserver;

public interface RecordNewLeaderNotificatorObservable
        extends MinesweeperObservable<RecordNewLeaderNotificatorObserver> {
    void notifyObservers(String strDifficultyMode);
}
