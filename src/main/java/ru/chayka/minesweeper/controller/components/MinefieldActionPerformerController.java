package ru.chayka.minesweeper.controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.controller.MVCLogger;
import ru.chayka.minesweeper.model.minefield.MinefieldActionPerformer;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.MinefieldObserver;
import ru.chayka.minesweeper.view.MouseButton;
import ru.chayka.minesweeper.view.minesweeperFrame.minefield.minefieldButton.MinefieldButtonState;

public class MinefieldActionPerformerController
        implements MinefieldObserver {
    private static final Logger log = LoggerFactory.getLogger(MinefieldActionPerformerController.class.getName());

    private MinefieldActionPerformer minefieldActionPerformerModel;

    public void setMinefieldActionPerformerModel(MinefieldActionPerformer minefieldActionPerformerModel) {
        this.minefieldActionPerformerModel = minefieldActionPerformerModel;
    }

    @Override
    public void transferToModel(int row, int column, MouseButton mouseButton, MinefieldButtonState minefieldButtonState) {
        log.debug("Controller registered minefield button pressing ({}, {}/{}, {})",
                mouseButton.name(), row, column, minefieldButtonState.name());

        switch (mouseButton) {
            case RMB:
                if (minefieldButtonState == MinefieldButtonState.CLOSED) {
                    MVCLogger.logCommandTransferring(log, this, minefieldActionPerformerModel);
                    minefieldActionPerformerModel.openTheCell(row, column);
                }
                break;
            case LMB:
                if (minefieldButtonState == MinefieldButtonState.CLOSED) {
                    MVCLogger.logCommandTransferring(log, this, minefieldActionPerformerModel);
                    minefieldActionPerformerModel.flagTheCell(row, column);
                } else if (minefieldButtonState == MinefieldButtonState.FLAG) {
                    MVCLogger.logCommandTransferring(log, this, minefieldActionPerformerModel);
                    minefieldActionPerformerModel.unflagTheCell(row, column);
                }
                break;
            case MMB:
                if (minefieldButtonState.checkIfStateIsNumbered()) {
                    MVCLogger.logCommandTransferring(log, this, minefieldActionPerformerModel);
                    minefieldActionPerformerModel.tryToOpenAdjacentCellsWithFlagsAround(row, column);
                }
                break;
        }
    }
}
