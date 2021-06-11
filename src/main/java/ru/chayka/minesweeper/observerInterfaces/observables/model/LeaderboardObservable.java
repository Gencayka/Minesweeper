package ru.chayka.minesweeper.observerInterfaces.observables.model;

import ru.chayka.minesweeper.dto.LeaderboardDto;
import ru.chayka.minesweeper.observerInterfaces.observables.MinesweeperObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.LeaderboardObserver;

public interface LeaderboardObservable
        extends MinesweeperObservable<LeaderboardObserver> {
    void sendLeaderboardDto(LeaderboardDto dto);
}
