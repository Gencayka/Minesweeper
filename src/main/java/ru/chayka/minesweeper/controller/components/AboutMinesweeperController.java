package ru.chayka.minesweeper.controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.controller.MVCLogger;
import ru.chayka.minesweeper.model.about.AboutMinesweeper;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.AboutButtonObserver;

public class AboutMinesweeperController
        implements AboutButtonObserver {
    private static final Logger log = LoggerFactory.getLogger(AboutMinesweeperController.class.getName());

    private AboutMinesweeper aboutMinesweeperModel;

    public void setAboutMinesweeperModel(AboutMinesweeper aboutMinesweeperModel) {
        this.aboutMinesweeperModel = aboutMinesweeperModel;
    }

    @Override
    public void transferToModel() {
        MVCLogger.logCommandTransferring(log, this, aboutMinesweeperModel);
        aboutMinesweeperModel.notifyObservers();
    }
}
