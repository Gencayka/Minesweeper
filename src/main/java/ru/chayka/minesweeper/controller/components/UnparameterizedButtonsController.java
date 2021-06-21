package ru.chayka.minesweeper.controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.view.MvcUnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.MvcUnparameterizedButtonPressedEventListener;
import ru.chayka.minesweeper.model.MinesweeperModel;

public class UnparameterizedButtonsController
        implements MvcUnparameterizedButtonPressedEventListener {
    private static final Logger log = LoggerFactory.getLogger(UnparameterizedButtonsController.class.getName());

    private final MinesweeperModel model;

    public UnparameterizedButtonsController(MinesweeperModel model) {
        this.model = model;
    }

    @Override
    public void acceptEvent(MvcUnparameterizedButtonPressedEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);

        switch (event.getButton()) {
            case SMILE, NEW_GAME -> model.getMinefieldCreator().
                    createNewMinefield(model.getMinefieldCreator().getLastDifficultyMode());
            case HIGH_SCORES -> model.getLeaderboard().sendLeaderboardEntriesToView();
            case ABOUT -> model.getAboutMinesweeper().sendAboutTextToView();
            case EXIT -> model.closeApp();
            case RESET_HIGH_SCORES -> model.getLeaderboard().resetLeaderboard();
        }
    }
}
