package ru.chayka.minesweeper.observerInterfaces.observers.view;

import ru.chayka.minesweeper.dto.LeaderboardDto;
import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface LeaderboardObserver extends MinesweeperObserver {
    void acceptLeaderboardDto(LeaderboardDto leaderboardDto);
}
