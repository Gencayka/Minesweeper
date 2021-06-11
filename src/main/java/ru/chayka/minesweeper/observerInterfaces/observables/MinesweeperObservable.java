package ru.chayka.minesweeper.observerInterfaces.observables;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface MinesweeperObservable<T extends MinesweeperObserver> {
    void registerObserver(T observer);

    void removeObserver(T observer);
}
