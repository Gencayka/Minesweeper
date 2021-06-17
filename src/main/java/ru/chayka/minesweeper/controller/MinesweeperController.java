package ru.chayka.minesweeper.controller;

import ru.chayka.minesweeper.controller.components.DifficultyModeButtonsController;
import ru.chayka.minesweeper.controller.components.MinefieldButtonsController;
import ru.chayka.minesweeper.controller.components.RecordNewLeaderButtonController;
import ru.chayka.minesweeper.controller.components.UnparameterizedButtonsController;
import ru.chayka.minesweeper.model.MinesweeperModel;

public class MinesweeperController {
    private final UnparameterizedButtonsController unparameterizedButtonsController;
    private final MinefieldButtonsController minefieldButtonsController;
    private final DifficultyModeButtonsController difficultyModeButtonsController;
    private final RecordNewLeaderButtonController recordNewLeaderButtonController;

    public MinesweeperController(MinesweeperModel model) {
        unparameterizedButtonsController = new UnparameterizedButtonsController(model);
        minefieldButtonsController = new MinefieldButtonsController(model);
        difficultyModeButtonsController = new DifficultyModeButtonsController(model);
        recordNewLeaderButtonController = new RecordNewLeaderButtonController(model);
    }

    public UnparameterizedButtonsController getUnparameterizedButtonsController() {
        return unparameterizedButtonsController;
    }

    public MinefieldButtonsController getMinefieldButtonsController() {
        return minefieldButtonsController;
    }

    public DifficultyModeButtonsController getDifficultyModeButtonsController() {
        return difficultyModeButtonsController;
    }

    public RecordNewLeaderButtonController getRecordNewLeaderButtonController() {
        return recordNewLeaderButtonController;
    }
}
