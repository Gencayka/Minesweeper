package ru.chayka.minesweeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.controller.MinesweeperController;
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
        MinesweeperController controller = new MinesweeperController();

        //создание меню Game
        view.getViewComponent()
                .registerAllViewObserversForObservable(
                        model.getDifficultyModesDtoSender());

        //новая игра
        view.getViewComponent()
                .registerObserverForAllViewObservables(
                        controller.getMinefieldCreatorController());

        controller.getMinefieldCreatorController()
                .setMinesweeperModel(model);

        view.getViewComponent()
                .registerAllViewObserversForObservable(
                        model.getMinefieldCreator());

        //таймер
        view.getViewComponent()
                .registerAllViewObserversForObservable(
                        model.getGameTimer());

        //счетчик флагов
        view.getViewComponent()
                .registerAllViewObserversForObservable(
                        model.getFlagCounter());

        //игровое поле
        view.getViewComponent()
                .registerObserverForAllViewObservables(
                        controller.getMinefieldActionPerformerController());

        controller.getMinefieldActionPerformerController()
                .setMinefieldActionPerformerModel(
                        model.getMinefieldActionPerformer());

        view.getViewComponent()
                .registerAllViewObserversForObservable(
                        model.getMinefieldActionPerformer());

        //конец игры
        view.getViewComponent()
                .registerAllViewObserversForObservable(
                        model.getMinefieldActionPerformer().getGameOverNotificator());

        //кнопка About
        view.getViewComponent()
                .registerObserverForAllViewObservables(
                        controller.getAboutMinesweeperController());

        controller.getAboutMinesweeperController()
                .setAboutMinesweeperModel(
                        model.getAboutMinesweeper());

        view.getViewComponent()
                .registerAllViewObserversForObservable(
                        model.getAboutMinesweeper());

        //таблица рекордов
        view.getViewComponent()
                .registerObserverForAllViewObservables(
                        controller.getLeaderboardController());

        controller.getLeaderboardController()
                .setLeaderboardModel(
                        model.getLeaderboard());

        view.getViewComponent()
                .registerAllViewObserversForObservable(
                        model.getLeaderboard());

        view.getViewComponent()
                .registerAllViewObserversForObservable(
                        model.getLeaderboard().getRecordNewLeaderNotificator());

        model.createInitMinefield();

        //смена сложности игры
        //идет после инициализации игрового поля в модели, т.к.
        //кнопки для переключения сложности создаются только после инициализации
        view.getViewComponent()
                .registerObserverForAllViewObservables(
                        controller.getMinefieldCreatorController());

        view.showMainFrame();

        log.debug("Application built successfully");
    }
}
