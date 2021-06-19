package ru.chayka.minesweeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.controller.MinesweeperController;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;
import ru.chayka.minesweeper.model.MinesweeperModel;
import ru.chayka.minesweeper.view.MinesweeperView;

public class MVCBuilder {
    private static final Logger log = LoggerFactory.getLogger(MVCBuilder.class.getName());

    public static void main(String[] args) {
        buildApplication();
    }

    public static void buildApplication() {
        MinesweeperModel model = new MinesweeperModel();
        MinesweeperView view = new MinesweeperView();
        MinesweeperController controller = new MinesweeperController(model);

        //создание меню Game
        addListenerForSender(
                model.getDifficultyModesDtoEventSender(),
                view.getMainFrame().getGameMenuBar().getGameMenu());

        //новая игра
        addListenerForSender(
                view.getMainFrame().getGameMenuBar().getGameMenu().getUnparameterizedButtonPressedEventSender(),
                controller.getUnparameterizedButtonsController());
        addListenerForSender(
                view.getMainFrame().getOtherElementsPanel().getSmileButton().
                        getUnparameterizedButtonPressedEventSender(),
                controller.getUnparameterizedButtonsController());

        addListenerForSender(
                model.getMinefieldCreator().getMinefieldDtoEventSender(),
                view.getMainFrame().getMinefieldPanel());

        //таймер
        addListenerForSender(
                model.getGameTimer().getGameTimeDtoEventSender(),
                view.getMainFrame().getOtherElementsPanel().getGameTimer());

        //счетчик флагов
        addListenerForSender(
                model.getFlagCounter().getFlagCounterDtoEventSender(),
                view.getMainFrame().getOtherElementsPanel().getFlagsCounter());

        //игровое поле
        addListenerForSender(
                view.getMainFrame().getMinefieldPanel().getMinefieldButtonPressedEventSender(),
                controller.getMinefieldButtonsController());

        addListenerForSender(
                model.getMinefieldActionPerformer().getMinefieldCellDtoEventSender(),
                view.getMainFrame().getMinefieldPanel());

        //конец игры
        addListenerForSender(
                model.getMinefieldActionPerformer().getGameOverEventSender(),
                view.getMainFrame().getMinefieldPanel());
        addListenerForSender(
                model.getMinefieldActionPerformer().getGameOverEventSender(),
                view.getMainFrame().getOtherElementsPanel().getSmileButton());

        //кнопка About
        addListenerForSender(
                view.getMainFrame().getGameMenuBar().getHelpMenu().getUnparameterizedButtonPressedEventSender(),
                controller.getUnparameterizedButtonsController());

        addListenerForSender(
                model.getAboutMinesweeper().getAboutDtoEventSender(),
                view.getAboutFrame());

        //таблица рекордов
        addListenerForSender(
                view.getRecordNewLeaderFrame().getNewLeaderDtoEventSender(),
                controller.getRecordNewLeaderButtonController());
        addListenerForSender(
                view.getLeaderboardFrame().getUnparameterizedButtonPressedEventSender(),
                controller.getUnparameterizedButtonsController());

        addListenerForSender(
                model.getLeaderboard().getLeaderboardDtoEventSender(),
                view.getLeaderboardFrame());
        addListenerForSender(
                model.getLeaderboard().getRecordNewLeaderEventSender(),
                view.getRecordNewLeaderFrame());

        model.createInitMinefield();

        //смена сложности игры
        //идет после инициализации игрового поля в модели, т.к.
        //кнопки для переключения сложности создаются только после инициализации
        for (var setDifficultyMenuButton :
                view.getMainFrame().getGameMenuBar().getGameMenu().getSetDifficultyMenuButtons()) {
            addListenerForSender(
                    setDifficultyMenuButton.getDifficultyModeButtonPressedEventSender(),
                    controller.getDifficultyModeButtonsController());
        }

        view.showMainFrame();

        log.debug("Application built successfully" + System.lineSeparator());
    }

    private static <
            S extends MinesweeperEventSender<L, E>,
            L extends MinesweeperEventListener,
            E extends MinesweeperEvent>
    void addListenerForSender(S sender, L listener) {
        boolean listenerAddingResult = sender.addListener(listener);
        EventSystemLogger.logListenerAddingResult(log, listenerAddingResult, sender, listener);
    }
}
