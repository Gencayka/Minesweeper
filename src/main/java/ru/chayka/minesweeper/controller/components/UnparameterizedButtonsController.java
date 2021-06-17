package ru.chayka.minesweeper.controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.UnparameterizedButtonPressedEventListener;
import ru.chayka.minesweeper.model.MinesweeperModel;

public class UnparameterizedButtonsController
        implements UnparameterizedButtonPressedEventListener {
    private static final Logger log = LoggerFactory.getLogger(UnparameterizedButtonsController.class.getName());

    private final MinesweeperModel model;

    public UnparameterizedButtonsController(MinesweeperModel model) {
        this.model = model;
    }

    @Override
    public void acceptEvent(UnparameterizedButtonPressedEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);

        switch (event.getButton()) {
            case SMILE, NEW_GAME -> model.createNewMinefield(model.getMinefieldCreator().getLastDifficultyMode());
            case HIGH_SCORES -> model.getLeaderboard().sendLeaderboardEntriesToView();
            case ABOUT -> model.getAboutMinesweeper().sendAboutTextToView();
            case EXIT -> System.exit(0);
            case RESET_HIGH_SCORES -> model.getLeaderboard().resetLeaderboard();
        }
    }
}
