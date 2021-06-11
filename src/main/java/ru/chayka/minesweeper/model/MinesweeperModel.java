package ru.chayka.minesweeper.model;

import ru.chayka.minesweeper.model.about.AboutMinesweeper;
import ru.chayka.minesweeper.model.difficultyModesDtoSender.DifficultyModesDtoSender;
import ru.chayka.minesweeper.model.leaderboard.Leaderboard;
import ru.chayka.minesweeper.model.minefield.FlagCounter;
import ru.chayka.minesweeper.model.minefield.Minefield;
import ru.chayka.minesweeper.model.minefield.MinefieldActionPerformer;
import ru.chayka.minesweeper.model.minefield.MinefieldCreator;
import ru.chayka.minesweeper.model.timer.GameTimer;

public class MinesweeperModel {
    private final MinefieldCreator minefieldCreator;
    private final MinefieldActionPerformer minefieldActionPerformer;
    private final GameTimer gameTimer;
    private final FlagCounter flagCounter;
    private final Leaderboard leaderboard;
    private final AboutMinesweeper aboutMinesweeper;
    private final DifficultyModesDtoSender difficultyModesDtoSender;

    public MinesweeperModel() {
        gameTimer = new GameTimer();
        flagCounter = new FlagCounter();
        minefieldCreator = new MinefieldCreator();
        leaderboard = new Leaderboard();
        aboutMinesweeper = new AboutMinesweeper();
        difficultyModesDtoSender = new DifficultyModesDtoSender();

        minefieldActionPerformer = new MinefieldActionPerformer(gameTimer, flagCounter, leaderboard);
    }


    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public FlagCounter getFlagCounter() {
        return flagCounter;
    }

    public MinefieldCreator getMinefieldCreator() {
        return minefieldCreator;
    }

    public MinefieldActionPerformer getMinefieldActionPerformer() {
        return minefieldActionPerformer;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public AboutMinesweeper getAboutMinesweeper() {
        return aboutMinesweeper;
    }

    public void createNewMinefield(DifficultyMode difficultyMode) {
        Minefield minefield = minefieldCreator.createNewMinefield(difficultyMode);
        minefieldActionPerformer.setMinefield(minefield);
        flagCounter.setNumOfRemainingMines(difficultyMode);

        gameTimer.stopGameTimer();
        gameTimer.clearGameTimer();
    }

    public DifficultyModesDtoSender getDifficultyModesDtoSender() {
        return difficultyModesDtoSender;
    }

    public void createInitMinefield() {
        difficultyModesDtoSender.sendDifficultyModesDto();
        createNewMinefield(DifficultyMode.INITIAL_DIFFICULTY_MODE);
    }
}
