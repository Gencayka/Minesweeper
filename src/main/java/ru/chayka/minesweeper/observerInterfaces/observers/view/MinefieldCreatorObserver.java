package ru.chayka.minesweeper.observerInterfaces.observers.view;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface MinefieldCreatorObserver extends MinesweeperObserver {
    void createNewMinefield(int numOfRows, int numOfColumns);
}
