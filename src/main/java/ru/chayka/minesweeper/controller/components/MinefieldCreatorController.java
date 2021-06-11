package ru.chayka.minesweeper.controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.controller.MVCLogger;
import ru.chayka.minesweeper.model.DifficultyMode;
import ru.chayka.minesweeper.model.MinesweeperModel;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.StartNewGameButtonObserver;

import java.util.Optional;

public class MinefieldCreatorController
        implements StartNewGameButtonObserver {
    private static final Logger log = LoggerFactory.getLogger(MinefieldCreatorController.class.getName());

    private MinesweeperModel minesweeperModel;

    public void setMinesweeperModel(MinesweeperModel minesweeperModel) {
        this.minesweeperModel = minesweeperModel;
    }

    @Override
    public void transferToModel() {
        MVCLogger.logCommandTransferring(log, this, minesweeperModel);
        minesweeperModel.createNewMinefield(minesweeperModel.getMinefieldCreator().getLastDifficultyMode());
    }

    @Override
    public void transferToModel(String strDifficultyMode) {
        Optional<DifficultyMode> difficultyMode = DifficultyMode.stringToDifficultyMode(strDifficultyMode);
        if (difficultyMode.isPresent()) {
            MVCLogger.logCommandTransferring(log, this, minesweeperModel);
            minesweeperModel.createNewMinefield(difficultyMode.get());
        }
    }
}
