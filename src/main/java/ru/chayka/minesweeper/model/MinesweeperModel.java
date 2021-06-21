package ru.chayka.minesweeper.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.model.MvcDifficultyModesDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcDifficultyModesDtoEventSender;
import ru.chayka.minesweeper.model.about.AboutMinesweeper;
import ru.chayka.minesweeper.model.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.model.eventsystem.events.MinesweeperModelEvent;
import ru.chayka.minesweeper.model.eventsystem.listeners.MinesweeperModelEventListener;
import ru.chayka.minesweeper.model.eventsystem.senders.MinesweeperModelEventSender;
import ru.chayka.minesweeper.model.leaderboard.Leaderboard;
import ru.chayka.minesweeper.model.minefield.MinefieldActionPerformer;
import ru.chayka.minesweeper.model.minefield.MinefieldCreator;
import ru.chayka.minesweeper.model.timer.GameTimer;

public class MinesweeperModel {
    private static final Logger log = LoggerFactory.getLogger(MinesweeperModel.class.getName());

    private final MinefieldCreator minefieldCreator;
    private final MinefieldActionPerformer minefieldActionPerformer;
    private final GameTimer gameTimer;
    private final Leaderboard leaderboard;
    private final AboutMinesweeper aboutMinesweeper;
    private final MvcDifficultyModesDtoEventSender mvcDifficultyModesDtoEventSender;

    public MinesweeperModel() {
        minefieldCreator = new MinefieldCreator();
        minefieldActionPerformer = new MinefieldActionPerformer();
        gameTimer = new GameTimer();
        leaderboard = new Leaderboard(gameTimer);
        aboutMinesweeper = new AboutMinesweeper();
        mvcDifficultyModesDtoEventSender = new MvcDifficultyModesDtoEventSender();

        setUpEventSystem();
    }

    public GameTimer getGameTimer() {
        return gameTimer;
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

    private static <
            S extends MinesweeperModelEventSender<L, E>,
            L extends MinesweeperModelEventListener,
            E extends MinesweeperModelEvent>
    void addListenerForSender(S sender, L listener) {
        boolean listenerAddingResult = sender.addListener(listener);
        EventSystemLogger.logListenerAddingResult(log, listenerAddingResult, sender, listener);
    }

    public MvcDifficultyModesDtoEventSender getMvcDifficultyModesDtoEventSender() {
        return mvcDifficultyModesDtoEventSender;
    }

    public void initializeModel() {
        mvcDifficultyModesDtoEventSender.notifyAllListeners(new MvcDifficultyModesDtoEvent());
        minefieldCreator.createNewMinefield(DifficultyMode.INITIAL_DIFFICULTY_MODE);
    }

    public void closeApp() {
        log.debug("Application was closed by user");
        System.exit(0);
    }

    private void setUpEventSystem() {
        addListenerForSender(
                minefieldCreator.getMinefieldDtoEventSender(),
                minefieldActionPerformer);
        addListenerForSender(
                minefieldCreator.getMinefieldDtoEventSender(),
                gameTimer);

        addListenerForSender(
                minefieldActionPerformer.getFirstCellOpeningEventSender(),
                gameTimer);

        addListenerForSender(
                minefieldActionPerformer.getGameOverEventSender(),
                gameTimer);
        addListenerForSender(
                minefieldActionPerformer.getGameOverEventSender(),
                leaderboard);
    }
}
