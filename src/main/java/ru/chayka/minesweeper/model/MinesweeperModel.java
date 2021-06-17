package ru.chayka.minesweeper.model;

import ru.chayka.minesweeper.eventsystem.events.model.DifficultyModesDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.DifficultyModesDtoEventSender;
import ru.chayka.minesweeper.model.about.AboutMinesweeper;
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
    private final DifficultyModesDtoEventSender difficultyModesDtoEventSender;

    public MinesweeperModel() {
        gameTimer = new GameTimer();
        flagCounter = new FlagCounter();
        minefieldCreator = new MinefieldCreator();
        leaderboard = new Leaderboard();
        aboutMinesweeper = new AboutMinesweeper();
        difficultyModesDtoEventSender = new DifficultyModesDtoEventSender();

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

    public DifficultyModesDtoEventSender getDifficultyModesDtoEventSender() {
        return difficultyModesDtoEventSender;
    }

    public void createInitMinefield() {
        difficultyModesDtoEventSender.notifyAllListeners(new DifficultyModesDtoEvent());
        createNewMinefield(DifficultyMode.INITIAL_DIFFICULTY_MODE);
    }
}
