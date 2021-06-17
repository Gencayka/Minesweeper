package ru.chayka.minesweeper.eventsystem.senders.view;

import ru.chayka.minesweeper.eventsystem.events.view.NewLeaderDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.NewLeaderDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class NewLeaderDtoEventSender
        extends MinesweeperEventSender<NewLeaderDtoEventListener, NewLeaderDtoEvent> {
    @Override
    protected void notifyOneListener(NewLeaderDtoEventListener listener, NewLeaderDtoEvent event) {
        listener.acceptEvent(event);
    }
}
