package ru.chayka.minesweeper.observerInterfaces.observers.view;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface GameTimerObserver extends MinesweeperObserver {
    void update(int gameTime);
}
