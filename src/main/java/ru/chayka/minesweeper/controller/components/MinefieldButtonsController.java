package ru.chayka.minesweeper.controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.view.MinefieldButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.MinefieldButtonPressedEventListener;
import ru.chayka.minesweeper.model.MinesweeperModel;
import ru.chayka.minesweeper.model.minefield.MinefieldActionPerformer;
import ru.chayka.minesweeper.view.mainframe.minefield.minefieldbutton.MinefieldButtonState;

public class MinefieldButtonsController
        implements MinefieldButtonPressedEventListener {
    private static final Logger log = LoggerFactory.getLogger(MinefieldButtonsController.class.getName());

    private final MinefieldActionPerformer model;

    public MinefieldButtonsController(MinesweeperModel model) {
        this.model = model.getMinefieldActionPerformer();
    }

    @Override
    public void acceptEvent(MinefieldButtonPressedEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);

        switch (event.getMouseButton()) {
            case RMB:
                if (event.getState() == MinefieldButtonState.CLOSED) {
                    model.openTheCell(event.getRow(), event.getColumn());
                }
                break;
            case LMB:
                if (event.getState() == MinefieldButtonState.CLOSED) {
                    model.flagTheCell(event.getRow(), event.getColumn());
                } else if (event.getState() == MinefieldButtonState.FLAG) {
                    model.unflagTheCell(event.getRow(), event.getColumn());
                }
                break;
            case MMB:
                if (event.getState().checkIfStateIsNumbered()) {
                    model.tryToOpenAdjacentCellsWithFlagsAround(event.getRow(), event.getColumn());
                }
                break;
        }
    }
}
