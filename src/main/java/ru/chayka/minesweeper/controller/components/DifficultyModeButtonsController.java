package ru.chayka.minesweeper.controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.view.MvcDifficultyModeButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.MvcDifficultyModeButtonPressedEventListener;
import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.MinesweeperModel;

import java.util.Optional;

public class DifficultyModeButtonsController
        implements MvcDifficultyModeButtonPressedEventListener {
    private static final Logger log = LoggerFactory.getLogger(DifficultyModeButtonsController.class.getName());

    private final MinesweeperModel model;

    public DifficultyModeButtonsController(MinesweeperModel model) {
        this.model = model;
    }

    @Override
    public void acceptEvent(MvcDifficultyModeButtonPressedEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);

        Optional<DifficultyMode> difficultyMode =
                DifficultyMode.stringToDifficultyMode(event.getStrDifficultyMode());
        difficultyMode.ifPresent(mode -> model.getMinefieldCreator().createNewMinefield(mode));
    }
}
