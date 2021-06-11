package ru.chayka.minesweeper.observerInterfaces.observables.model;

import ru.chayka.minesweeper.dto.MinefieldCellDto;
import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.MinefieldActionPerformerObserver;

public interface MinefieldActionPerformerObservable
        extends MinesweeperObservable<MinefieldActionPerformerObserver> {
    void sendMinefieldCellDto(MinefieldCellDto dto);
}
