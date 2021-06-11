package ru.chayka.minesweeper.observerInterfaces.observers.view;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface AboutMinesweeperObserver extends MinesweeperObserver {
    void acceptAboutData(String text);
}
