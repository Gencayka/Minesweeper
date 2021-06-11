package ru.chayka.minesweeper.controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.controller.MVCLogger;
import ru.chayka.minesweeper.model.leaderboard.Leaderboard;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.LeaderboardActionsObserver;

public class LeaderboardController
        implements LeaderboardActionsObserver {
    private static final Logger log = LoggerFactory.getLogger(LeaderboardController.class.getName());

    private Leaderboard leaderboardModel;

    public void setLeaderboardModel(Leaderboard leaderboardModel) {
        this.leaderboardModel = leaderboardModel;
    }

    @Override
    public void transferShowLeaderboardCommandToModel() {
        MVCLogger.logCommandTransferring(log, this, leaderboardModel);
        leaderboardModel.sendLeaderboardInfoToView();
    }

    @Override
    public void transferLeaderNameToModel(String leaderName) {
        MVCLogger.logCommandTransferring(log, this, leaderboardModel);
        leaderboardModel.recordNewLeader(leaderName);
    }

    @Override
    public void transferResetLeaderboardCommandToModel() {
        MVCLogger.logCommandTransferring(log, this, leaderboardModel);
        leaderboardModel.resetLeaderboard();
    }
}
