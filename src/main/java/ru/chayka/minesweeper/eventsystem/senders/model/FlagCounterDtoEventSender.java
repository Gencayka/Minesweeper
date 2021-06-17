package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.FlagCounterDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.FlagCounterDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class FlagCounterDtoEventSender
        extends MinesweeperEventSender<FlagCounterDtoEventListener, FlagCounterDtoEvent> {
    @Override
    protected void notifyOneListener(FlagCounterDtoEventListener listener, FlagCounterDtoEvent event) {
        listener.acceptEvent(event);
    }
}
