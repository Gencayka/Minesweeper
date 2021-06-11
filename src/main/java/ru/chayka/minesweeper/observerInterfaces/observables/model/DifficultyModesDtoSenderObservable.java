package ru.chayka.minesweeper.observerInterfaces.observables.model;

import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.DifficultyModesDtoSenderObserver;

public interface DifficultyModesDtoSenderObservable
        extends MinesweeperObservable<DifficultyModesDtoSenderObserver> {
    void sendDifficultyModesDto();
}
