package ru.chayka.minesweeper.controller;

import ru.chayka.minesweeper.controller.components.AboutMinesweeperController;
import ru.chayka.minesweeper.controller.components.LeaderboardController;
import ru.chayka.minesweeper.controller.components.MinefieldActionPerformerController;
import ru.chayka.minesweeper.controller.components.MinefieldCreatorController;

public class MinesweeperController {
    private final MinefieldActionPerformerController minefieldActionPerformerController;
    private final MinefieldCreatorController minefieldCreatorController;
    private final AboutMinesweeperController aboutMinesweeperController;
    private final LeaderboardController leaderboardController;

    public MinesweeperController() {
        minefieldActionPerformerController = new MinefieldActionPerformerController();
        minefieldCreatorController = new MinefieldCreatorController();
        aboutMinesweeperController = new AboutMinesweeperController();
        leaderboardController = new LeaderboardController();
    }

    public MinefieldActionPerformerController getMinefieldActionPerformerController() {
        return minefieldActionPerformerController;
    }

    public MinefieldCreatorController getMinefieldCreatorController() {
        return minefieldCreatorController;
    }

    public AboutMinesweeperController getAboutMinesweeperController() {
        return aboutMinesweeperController;
    }

    public LeaderboardController getLeaderboardController() {
        return leaderboardController;
    }
}
