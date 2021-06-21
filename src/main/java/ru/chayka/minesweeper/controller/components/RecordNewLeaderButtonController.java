package ru.chayka.minesweeper.controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.view.MvcNewLeaderDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.MvcNewLeaderDtoEventListener;
import ru.chayka.minesweeper.model.MinesweeperModel;
import ru.chayka.minesweeper.model.leaderboard.Leaderboard;

public class RecordNewLeaderButtonController
        implements MvcNewLeaderDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(RecordNewLeaderButtonController.class.getName());

    private final Leaderboard model;

    public RecordNewLeaderButtonController(MinesweeperModel model) {
        this.model = model.getLeaderboard();
    }

    @Override
    public void acceptEvent(MvcNewLeaderDtoEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);
        model.recordNewLeader(event.getName());
    }
}
