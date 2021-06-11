package ru.chayka.minesweeper.observerInterfaces.observers.view;

import ru.chayka.minesweeper.dto.DifficultyModesDto;
import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface DifficultyModesDtoSenderObserver extends MinesweeperObserver {
    void acceptDifficultyModesDto(DifficultyModesDto dto);
}
