package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.GameOverEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.GameOverEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class GameOverEventSender
        extends MinesweeperEventSender<GameOverEventListener, GameOverEvent> {
    @Override
    protected void notifyOneListener(GameOverEventListener listener, GameOverEvent event) {
        listener.acceptEvent(event);
    }
}
