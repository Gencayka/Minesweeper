package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.GameTimeDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.GameTimeDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class GameTimeDtoEventSender
        extends MinesweeperEventSender<GameTimeDtoEventListener, GameTimeDtoEvent> {
    @Override
    protected void notifyOneListener(GameTimeDtoEventListener listener, GameTimeDtoEvent event) {
        listener.acceptEvent(event);
    }
}
