package ru.chayka.minesweeper.observerInterfaces.observers.view;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface GameOverNotificatorObserver extends MinesweeperObserver {
    void loseTheGame();

    void winTheGame();
}
