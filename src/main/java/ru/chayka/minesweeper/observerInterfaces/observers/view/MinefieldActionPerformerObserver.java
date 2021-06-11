package ru.chayka.minesweeper.observerInterfaces.observers.view;

import ru.chayka.minesweeper.dto.MinefieldCellDto;
import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface MinefieldActionPerformerObserver extends MinesweeperObserver {
    void acceptMinefieldCellDto(MinefieldCellDto dto);
}
