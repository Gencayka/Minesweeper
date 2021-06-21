package ru.chayka.minesweeper.model.eventsystem.senders;

import ru.chayka.minesweeper.model.eventsystem.events.GameOverEvent;
import ru.chayka.minesweeper.model.eventsystem.listeners.GameOverEventListener;

public final class GameOverEventSender
        extends MinesweeperModelEventSender<GameOverEventListener, GameOverEvent> {
    @Override
    protected void notifyOneListener(GameOverEventListener listener, GameOverEvent event) {
        listener.acceptEvent(event);
    }
}
