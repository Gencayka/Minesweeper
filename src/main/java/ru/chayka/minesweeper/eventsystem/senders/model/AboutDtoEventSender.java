package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.AboutDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.AboutDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class AboutDtoEventSender
        extends MinesweeperEventSender<AboutDtoEventListener, AboutDtoEvent> {
    @Override
    protected void notifyOneListener(AboutDtoEventListener listener, AboutDtoEvent event) {
        listener.acceptEvent(event);
    }
}
