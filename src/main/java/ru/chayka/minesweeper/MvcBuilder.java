package ru.chayka.minesweeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.controller.MinesweeperController;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.MvcEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MvcEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;
import ru.chayka.minesweeper.model.MinesweeperModel;
import ru.chayka.minesweeper.view.MinesweeperView;

public class MvcBuilder {
    private static final Logger log = LoggerFactory.getLogger(MvcBuilder.class.getName());

    public static void main(String[] args) {
        buildApplication();
    }

    private static void buildApplication() {
        MinesweeperModel model = new MinesweeperModel();
        MinesweeperView view = new MinesweeperView();
        MinesweeperController controller = new MinesweeperController(model);

        //создание Game меню
        addListenerForSender(
                model.getMvcDifficultyModesDtoEventSender(),
                view.getMainFrame().getGameMenuBar().getGameMenu());

        //выход из игры
        addListenerForSender(
                view.getMainFrame().getMvcUnparameterizedButtonPressedEventSender(),
                controller.getUnparameterizedButtonsController());

        //кнопки Game меню (кроме кнопок смены уровня сложности)
        addListenerForSender(
                view.getMainFrame().getGameMenuBar().getGameMenu().
                        getMvcUnparameterizedButtonPressedEventSender(),
                controller.getUnparameterizedButtonsController());

        //новая игра
        addListenerForSender(
                view.getMainFrame().getOtherElementsPanel().getSmileButton().
                        getMvcUnparameterizedButtonPressedEventSender(),
                controller.getUnparameterizedButtonsController());

        addListenerForSender(
                model.getMinefieldCreator().getMvcMinefieldDtoEventSender(),
                view.getMainFrame().getMinefieldPanel());

        //таймер
        addListenerForSender(
                model.getGameTimer().getMvcGameTimeDtoEventSender(),
                view.getMainFrame().getOtherElementsPanel().getGameTimer());

        //счетчик флагов
        addListenerForSender(
                model.getMinefieldActionPerformer().getMvcFlagCounterDtoEventSender(),
                view.getMainFrame().getOtherElementsPanel().getFlagsCounter());

        //игровое поле
        addListenerForSender(
                view.getMainFrame().getMinefieldPanel().getMvcMinefieldButtonPressedEventSender(),
                controller.getMinefieldButtonsController());

        addListenerForSender(
                model.getMinefieldActionPerformer().getMvcMinefieldCellDtoEventSender(),
                view.getMainFrame().getMinefieldPanel());

        //конец игры
        addListenerForSender(
                model.getMinefieldActionPerformer().getMvcGameOverEventSender(),
                view.getMainFrame().getMinefieldPanel());
        addListenerForSender(
                model.getMinefieldActionPerformer().getMvcGameOverEventSender(),
                view.getMainFrame().getOtherElementsPanel().getSmileButton());

        //кнопки Help меню
        addListenerForSender(
                view.getMainFrame().getGameMenuBar().getHelpMenu().
                        getMvcUnparameterizedButtonPressedEventSender(),
                controller.getUnparameterizedButtonsController());

        addListenerForSender(
                model.getAboutMinesweeper().getMvcAboutDtoEventSender(),
                view.getAboutFrame());

        //таблица рекордов
        addListenerForSender(
                view.getRecordNewLeaderFrame().getMvcNewLeaderDtoEventSender(),
                controller.getRecordNewLeaderButtonController());
        addListenerForSender(
                view.getLeaderboardFrame().getMvcUnparameterizedButtonPressedEventSender(),
                controller.getUnparameterizedButtonsController());

        addListenerForSender(
                model.getLeaderboard().getMvcLeaderboardDtoEventSender(),
                view.getLeaderboardFrame());
        addListenerForSender(
                model.getLeaderboard().getMvcRecordNewLeaderEventSender(),
                view.getRecordNewLeaderFrame());

        model.initializeModel();

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
            S extends MvcEventSender<L, E>,
            L extends MvcEventListener,
            E extends MvcEvent>
    void addListenerForSender(S sender, L listener) {
        boolean listenerAddingResult = sender.addListener(listener);
        MvcEventSystemLogger.logListenerAddingResult(log, listenerAddingResult, sender, listener);
    }
}
