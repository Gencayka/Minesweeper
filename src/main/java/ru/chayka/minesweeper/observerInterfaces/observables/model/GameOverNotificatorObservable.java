package ru.chayka.minesweeper.observerInterfaces.observables.model;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.GameOverNotificatorObserver;

public interface GameOverNotificatorObservable
        extends MinesweeperObservable<GameOverNotificatorObserver> {
    void notifyObserversToWinTheGame();

    void notifyObserversToLoseTheGame();
}
