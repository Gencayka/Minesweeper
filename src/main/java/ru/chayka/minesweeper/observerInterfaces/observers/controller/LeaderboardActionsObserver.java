package ru.chayka.minesweeper.observerInterfaces.observers.controller;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface LeaderboardActionsObserver extends MinesweeperObserver {
    void transferShowLeaderboardCommandToModel();

    void transferLeaderNameToModel(String leaderName);

    void transferResetLeaderboardCommandToModel();
}
