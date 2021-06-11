package ru.chayka.minesweeper.observerInterfaces.observers.view;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface FlagCounterObserver extends MinesweeperObserver {
    void update(int numOfRemainingMines);
}
