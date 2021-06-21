package ru.chayka.minesweeper.model.eventsystem.senders;

import ru.chayka.minesweeper.model.eventsystem.events.MinefieldDtoEvent;
import ru.chayka.minesweeper.model.eventsystem.listeners.MinefieldDtoEventListener;

public final class MinefieldDtoEventSender
        extends MinesweeperModelEventSender<MinefieldDtoEventListener, MinefieldDtoEvent> {
    @Override
    protected void notifyOneListener(MinefieldDtoEventListener listener, MinefieldDtoEvent event) {
        listener.acceptEvent(event);
    }
}
